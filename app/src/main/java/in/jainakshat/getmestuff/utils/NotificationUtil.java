package in.jainakshat.getmestuff.utils;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

/**
 * Created by jainakshat9595 on 27/8/16.
 */
public class NotificationUtil {

    private static final String BASE_URL = "https://fcm.googleapis.com/fcm/send";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void post(Context context, JSONObject data) throws JSONException, UnsupportedEncodingException {
        client.addHeader("Authorization", "key=AIzaSyBCrJeZ_CkxqwnTP8D_ICoX3B5nrZpYGys");
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("to", "/topics/notifs");
        jsonParams.put("data", data);
        StringEntity entity = new StringEntity(jsonParams.toString());
        client.post(context, BASE_URL, entity, "application/json",new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.d("NOTIF_UTILS", "onSuccess, statusCode: "+statusCode+", responseBody: "+responseBody);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("NOTIF_UTILS", "onFailure, statusCode: "+statusCode+", responseBody: "+responseBody);
            }
        });
    }

}
