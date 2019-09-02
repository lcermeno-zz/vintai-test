package com.qiubo.vintai;

import android.app.Application;

public class Vintai extends Application {
    private static Vintai sInstance;
    public static String sDefaultErrorMessage;

    public static Vintai getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }
}
