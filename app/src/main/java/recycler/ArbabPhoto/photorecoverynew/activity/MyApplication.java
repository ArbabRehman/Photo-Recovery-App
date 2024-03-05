package recycler.ArbabPhoto.photorecoverynew.activity;

import android.app.Application;

import recycler.ArbabPhoto.photorecoverynew.ads.AppOpen_Manager;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    AppOpen_Manager appOpenManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

//        appOpenManager = new AppOpen_Manager(MyApplication.this, getString(R.string.admob_open_ads));

    }

    public static MyApplication getInstance() {
        return mInstance;
    }


}