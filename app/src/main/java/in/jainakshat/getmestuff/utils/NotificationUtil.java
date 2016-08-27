package in.jainakshat.getmestuff.utils;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by jainakshat9595 on 27/8/16.
 */
public class NotificationUtil {

    public boolean generateHTTPRequest() {
        boolean result = false;
        HttpClient hc = new DefaultHttpClient();
        String message;

        HttpPost p = new HttpPost(url);
        JSONObject object = new JSONObject();
        try {

            object.put("updates", updates);
            object.put("mobile", mobile);
            object.put("last_name", lastname);
            object.put("first_name", firstname);
            object.put("email", email);

        } catch (Exception ex) {

        }

        try {
            message = object.toString();


            p.setEntity(new StringEntity(message, "UTF8"));
            p.setHeader("Content-type", "application/json");
            HttpResponse resp = hc.execute(p);
            if (resp != null) {
                if (resp.getStatusLine().getStatusCode() == 204)
                    result = true;
            }

            Log.d("Status line", "" + resp.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }

}
