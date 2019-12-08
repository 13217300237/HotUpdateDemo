package com.example.administrator.myapplication.hot.fix.core;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.example.administrator.myapplication.utils.AssetsFileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 热修复核心类
 */
public class HotfixHelper {

    public static void fix(Context myApp, final String fixPath, final String hackPath) {
        try {
            String fixAbsPath = AssetsFileUtil.copyAssetToCache(myApp, fixPath);
            String hackAbsPath = AssetsFileUtil.copyAssetToCache(myApp, hackPath);
            Log.d("hoxFixHelperTag", "fixPath:" + fixAbsPath + ";hackPath:" + hackAbsPath);
            File fixFile = new File(fixAbsPath);
            File hackFile = new File(hackAbsPath);
            List<File> fileList = new ArrayList<>();
            fileList.add(hackFile);
            fileList.add(fixFile);

            if (Build.VERSION.SDK_INT >= 23) {
                ClassLoaderHookHelper.hookV23(myApp.getClassLoader(), fileList, myApp.getCacheDir());
            } else if (Build.VERSION.SDK_INT >= 19) {
                ClassLoaderHookHelper.hookV19(myApp.getClassLoader(), fileList, myApp.getCacheDir());
            } else if (Build.VERSION.SDK_INT >= 14) {
                ClassLoaderHookHelper.hookV14(myApp.getClassLoader(), fileList, myApp.getCacheDir());
            }
        } catch (Exception e) {
            Log.d("hoxFixHelperTag", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
