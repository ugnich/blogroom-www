package live.blogroom.www;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ugnich
 */
public class TeleLanding {

    public static void doGet(Connection sql, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int user_id = SQL.getUserIdFromCookies(request, response);

        Utils.noCache(response);
        Utils.thisIsHtml(response);
        PrintWriter out = response.getWriter();
        try {
            PageTemplates.printHead(out, "Комментарии для Телеграма");
            request.getRequestDispatcher("/WEB-INF/index.jsp").include(request, response);
            PageTemplates.printEnd(out);
        } catch (Exception e) {
        }
    }
}
