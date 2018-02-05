package live.blogroom.www;

import com.ugnich.onelinesql.OneLineSQL;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ugnich
 */
public class API {

    public static void doPostPost(Connection sql, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hash = request.getParameter("hash");
        String text = request.getParameter("text");
        if (hash == null || hash.length() != 32 || text == null || text.isEmpty() || text.length() > 1024) {
            Utils.sendJSONError(response, 400);
            return;
        }

        int post_id = OneLineSQL.insertAutoIncrement(sql, "INSERT INTO posts(user_hash,txt,ts) VALUES (?,?,NOW())", hash, text);

        if (post_id > 0) {
            JSONObject ret = new JSONObject();
            try {
                JSONObject post = new JSONObject();
                post.put("text", text);
                post.put("post_id", post_id);
                ret.put("post", post);
            } catch (JSONException e) {
            }
            Utils.printJSONResponse(response, ret);
        } else {
            Utils.sendJSONError(response, 500);
        }
    }
}
