package live.blogroom.www;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ugnich
 */
public class Utils {

    public static void setCookie(HttpServletResponse response, String name, String value, int maxage) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setMaxAge(maxage);
        response.addCookie(c);
    }

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(name)) {
                    return cookies[i].getValue();
                }
            }
        }
        return null;
    }

    public static void noCache(HttpServletResponse response) {
        response.addHeader("Cache-Control", "no-cache, must-revalidate");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "Sat, 26 Jul 1997 05:00:00 GMT");
    }

    public static void thisIsHtml(HttpServletResponse response) {
        response.setContentType("text/html; charset=UTF-8");
    }

    public static String encodeHTML(String str) {
        if (str != null) {
            return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("'", "&apos;").replaceAll("\"", "&quot;");
        } else {
            return "";
        }
    }

    public static void sendJSONError(HttpServletResponse response, int statusCode) {
        response.setStatus(statusCode);
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            response.getOutputStream().print("{}");
        } catch (IOException e) {
        }
    }

    public static void printJSONResponse(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        try {
            if (obj != null) {
                out.println(obj.toString());
            } else {
                out.println("{}");
            }
        } finally {
            out.close();
        }
    }
}
