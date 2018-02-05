package live.blogroom.www;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ugnich Anton
 */
@WebServlet(name = "Main", urlPatterns = {"/"})
public class Main extends HttpServlet {

    Connection sql;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            sql = DriverManager.getConnection("jdbc:mysql://localhost/blogroom?autoReconnect=true&user=root&password=");
        } catch (Exception e) {
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        if (sql != null) {
            try {
                sql.close();
                sql = null;
            } catch (SQLException e) {
                log(null, e);
            }
        }
    }

    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/")) {
            Home.doGet(sql, request, response);
        } else if (uri.startsWith("/p/")) {
            int post_id = 0;
            try {
                post_id = Integer.parseInt(uri.substring(3));
            } catch (Exception e) {
            }
            if (post_id > 0) {
                Post.doGet(sql, request, response, post_id);
            } else {
                response.sendError(404);
            }
        } else {
            response.sendError(404);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/api/newpost")) {
            API.doPostPost(sql, request, response);
        } else {
            response.sendError(404);
        }
    }
}
