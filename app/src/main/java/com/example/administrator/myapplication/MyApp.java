package com.example.administrator.myapplication;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.example.administrator.myapplication.utils.AssetsFileUtil;
import com.example.administrator.myapplication.utils.ClassLoaderHookHelper;

import java.io.File;


public class MyApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        Log.d("BugTag2", "" + getClassLoader());//PathClassLoader
        Log.d("BugTag2", "" + getClassLoader().getParent());//BootClassLoader

        String fixPath = "fix.dex";
        try {
            String path = AssetsFileUtil.copyAssetToCache(this, fixPath);
            File fixFile = new File(path);
            if (Build.VERSION.SDK_INT >= 23) {
                ClassLoaderHookHelper.hookV23(getClassLoader(), fixFile, getCacheDir());
            } else if (Build.VERSION.SDK_INT >= 19) {
                ClassLoaderHookHelper.hookV19(getClassLoader(), fixFile, getCacheDir());
            } else if (Build.VERSION.SDK_INT >= 14) {
                ClassLoaderHookHelper.hookV14(getClassLoader(), fixFile, getCacheDir());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
