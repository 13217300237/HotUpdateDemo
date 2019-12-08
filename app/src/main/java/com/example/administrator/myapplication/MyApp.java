package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.example.administrator.myapplication.hot.fix.core.HotfixHelper;


public class MyApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d("hoxFixHelperTag", "app start");
        HotfixHelper.fix(this, "fix.dex", "hack.dex");
    }
}
