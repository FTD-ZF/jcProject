package com.example.host.jsnewmall.view.starpoints;

import android.app.Application;

/**
 * Created by host on 2017/4/17.
 */

public class Baseappview extends Application {

    private static Baseappview _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }

    public static Baseappview instance() {
        return _instance;
    }
}
