package in.jainakshat.getmestuff.database;

import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import in.jainakshat.getmestuff.model.Item;
import in.jainakshat.getmestuff.utils.NotificationUtil;

/**
 * Created by jainakshat9595 on 27/8/16.
 */
public class FirebaseUtil {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static DatabaseReference getItemsref() {
        return database.getReference("item");
    }

    public static void addItem(final Context context, final Item item) {
        getItemsref().push().setValue(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        });
    }

    public static void deleteItem(String id) {
        getItemsref().child(id).removeValue();
    }

    public static void setOfflineEnable() {
        database.setPersistenceEnabled(true);
    }

}
