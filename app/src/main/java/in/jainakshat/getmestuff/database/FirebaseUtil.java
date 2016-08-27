package in.jainakshat.getmestuff.database;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import in.jainakshat.getmestuff.model.Item;

/**
 * Created by jainakshat9595 on 27/8/16.
 */
public class FirebaseUtil {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static DatabaseReference getItemsref() {
        return database.getReference("item");
    }

    public static void addItem(Item item) {
        getItemsref().push().setValue(item);
    }

    public static void deleteItem(String id) {
        getItemsref().child(id).removeValue();
    }

}
