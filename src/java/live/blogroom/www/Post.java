package live.blogroom.www;

import com.ugnich.onelinesql.OneLineSQL;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ugnich
 */
public class Post {

    public static void doGet(Connection sql, HttpServletRequest request, HttpServletResponse response, int post_id) throws ServletException, IOException {
        int user_id = SQL.getUserIdFromCookies(request, response);

        String post_txt = OneLineSQL.getString(sql, "SELECT txt FROM posts WHERE post_id=?", post_id);
        if (post_txt == null) {
            response.sendError(404);
            return;
        }

        Utils.noCache(response);
        Utils.thisIsHtml(response);
        PrintWriter out = response.getWriter();
        try {
            PageTemplates.printHead(out, "BlogRoom");

            out.println("<div class=\"navbar fixed-top navbar-light bg-light\">");
            out.println("  <div class=\"navbar-brand\"><a href=\"/\" class=\"btn btn-default btn-sm\">&#9001;</a> Comments</div>");
            out.println("  <div class=\"navbar-text\" id=\"onlinecnt\"></div>");
            out.println("</div>");

            out.println("<div class=\"container mb-3\">");
            out.println("  <div class=\"card bg-light\">");
            out.println("    <div class=\"card-body\">" + Utils.encodeHTML(post_txt).replace("\n", "<br/>") + "</div>");
            out.println("  </div>");
            out.println("</div>");

            out.println("<div id=\"comments\" class=\"container\">");
            printComments(sql, out, post_id);
            out.println("</div>");

            out.println("<div class=\"container bg-light p-3\">");
            out.println("  <div class=\"row\">");
            out.println("    <div class=\"col-8 col-sm-10\">");
            out.println("      <input type=\"text\" class=\"form-control\" id=\"newmessage\" placeholder=\"Type your message here\" onkeypress=\"return inputkeypress(event)\"/>");
            out.println("    </div>");
            out.println("    <div class=\"col-4 col-sm-2\">");
            out.println("      <button class=\"btn btn-default w-100\" onclick=\"send(document.getElementById('newmessage'))\">Send</button>");
            out.println("    </div>");
            out.println("  </div>");
            out.println("</div>");

            out.println("<script>");
            out.println("document.addEventListener('DOMContentLoaded', function() {");
            out.println("  initWS(" + post_id + ");");
            out.println("});");
            out.println("</script>");

            PageTemplates.printEnd(out);
        } catch (Exception e) {
        }
    }

    private static void printComments(Connection sql, PrintWriter out, int post_id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = sql.prepareStatement("SELECT comment_id,txt FROM comments WHERE post_id=? ORDER BY comment_id ASC");
            stmt.setInt(1, post_id);
            rs = stmt.executeQuery();
            rs.beforeFirst();
            while (rs.next()) {
                String comment_id = rs.getString(1);
                String text = rs.getString(2);

                out.println("<div class=\"card mb-2\" data-comment-id=\"" + comment_id + "\">");
                out.println("<div class=\"card-body p-2\">");
                out.println("<p class=\"card-text\">" + Utils.encodeHTML(text).replace("\n", "<br/>") + "</p>");
                out.println("</div>");
                out.println("</div>");
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            OneLineSQL.finishSQL(rs, stmt);
        }
    }
}
