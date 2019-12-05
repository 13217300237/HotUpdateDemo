package com.example.administrator.myapplication.hot.fix.core;

import android.content.Context;
import android.os.Build;

import com.example.administrator.myapplication.utils.AssetsFileUtil;

import java.io.File;

/**
 * 热修复核心类
 */
public class HotfixHelper {

    public static void fix(Context myApp, String fixPath) {
        try {
            String path = AssetsFileUtil.copyAssetToCache(myApp, fixPath);
            File fixFile = new File(path);
            if (Build.VERSION.SDK_INT >= 23) {
                ClassLoaderHookHelper.hookV23(myApp.getClassLoader(), fixFile, myApp.getCacheDir());
            } else if (Build.VERSION.SDK_INT >= 19) {
                ClassLoaderHookHelper.hookV19(myApp.getClassLoader(), fixFile, myApp.getCacheDir());
            } else if (Build.VERSION.SDK_INT >= 14) {
                ClassLoaderHookHelper.hookV14(myApp.getClassLoader(), fixFile, myApp.getCacheDir());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
