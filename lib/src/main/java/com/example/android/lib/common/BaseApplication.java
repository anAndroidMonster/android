package com.example.android.lib.common;

import android.app.Application;

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public static BaseApplication getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
