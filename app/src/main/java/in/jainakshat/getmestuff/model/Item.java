package in.jainakshat.getmestuff.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;

import java.io.Serializable;

/**
 * Created by jainakshat9595 on 27/8/16.
 */
public class Item implements Serializable {

    @Exclude
    private String id;
    private String name;
    private String quant;
    private String desc;
    private long timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static Item parse(DataSnapshot dataSnapshot) {
        Item item = new Item();
        item.setId(dataSnapshot.getKey());
        item.setName(dataSnapshot.child("name").getValue().toString());
        item.setQuant(dataSnapshot.child("quant").getValue().toString());
        item.setDesc(dataSnapshot.child("desc").getValue().toString());
        item.setTimestamp(Long.parseLong(dataSnapshot.child("timestamp").getValue().toString()));
        return item;
    }
}
