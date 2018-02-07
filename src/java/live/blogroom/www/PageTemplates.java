package live.blogroom.www;

import java.io.PrintWriter;

/**
 *
 * @author ugnich
 */
public class PageTemplates {

    public static void printHead(PrintWriter out, String title) {
        out.println("<!doctype html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">");
        out.println("<script src=\"https://code.jquery.com/jquery-3.3.1.min.js\" integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\" crossorigin=\"anonymous\"></script>");
        out.println("<script type=\"text/javascript\" src=\"/js-3/blogroom.js\"></script>");
        out.println("<title>" + title + "</title>");
        out.println("</head>");
        out.println("<body style=\"padding-top: 4rem\">");
    }

    public static void printEnd(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }
}
