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
public class Home {

    public static void doGet(Connection sql, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = SQL.getUserIdFromCookies(request, response);

        Utils.noCache(response);
        Utils.thisIsHtml(response);
        PrintWriter out = response.getWriter();
        try {
            PageTemplates.printHead(out, "BlogRoom");

            out.println("<div class=\"navbar fixed-top navbar-light bg-light\">");
            out.println("  <div class=\"navbar-brand\">Posts</div>");
            out.println("</div>");

            out.println("<div id=\"newpost\" class=\"container bg-light pt-3 pb-1 mb-3\">");
            out.println("  <div class=\"form-group\">");
            out.println("    <textarea id=\"newposttext\" class=\"form-control\" rows=\"3\" placeholder=\"What's on your mind?\"></textarea>");
            out.println("  </div>");
            out.println("  <div class=\"form-group text-right\">");
            out.println("    <button class=\"btn btn-success\" onclick=\"newPost(document.getElementById('newposttext'),this)\">Post</button>");
            out.println("  </div>");
            out.println("</div>");

            out.println("<div id=\"posts\" class=\"container\">");
            PreparedStatement stmt = null;
            ResultSet rs = null;
            try {
                stmt = sql.prepareStatement("SELECT post_id,txt FROM posts ORDER BY post_id DESC LIMIT 20");
                rs = stmt.executeQuery();
                rs.beforeFirst();
                while (rs.next()) {
                    int post_id = rs.getInt(1);
                    String post_txt = rs.getString(2);

                    out.println("<div class=\"card mb-2\" data-post-id=\"" + post_id + "\" onclick=\"openPost(this)\">");
                    out.println("<div class=\"card-body p-2\">" + Utils.encodeHTML(post_txt).replace("\n", "<br/>") + "</div>");
                    out.println("</div>");
                }
            } catch (SQLException e) {
                System.err.println(e);
            } finally {
                OneLineSQL.finishSQL(rs, stmt);
            }
            out.println("</div>");

            PageTemplates.printEnd(out);
        } catch (Exception e) {
        }
    }
}
