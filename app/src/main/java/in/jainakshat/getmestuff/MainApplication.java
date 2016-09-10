package in.jainakshat.getmestuff;

import android.app.Application;

import in.jainakshat.getmestuff.database.FirebaseUtil;

/**
 * Created by jainakshat9595 on 10/9/16.
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseUtil.setOfflineEnable();
    }
}
