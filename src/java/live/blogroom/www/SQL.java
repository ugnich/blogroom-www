package live.blogroom.www;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ugnich
 */
public class SQL {

    public static int getUserIdFromCookies(HttpServletRequest request, HttpServletResponse response) {
        int user_id = 0;

        String hash = Utils.getCookie(request, "hash");
        if (hash == null || hash.length() != 32) {
            hash = Utils.generateHash(32);
            Utils.setCookie(response, "hash", hash, 60 * 60 * 24 * 365);
        }

        // To be continued...

        return user_id;
    }
}
