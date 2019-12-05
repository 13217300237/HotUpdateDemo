package com.example.administrator.myapplication;

        import android.app.Application;
        import android.content.Context;

        import com.example.administrator.myapplication.hot.fix.core.HotfixHelper;


public class MyApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        String fixPath = "fix.dex";
        HotfixHelper.fix(this, fixPath);
    }
}
