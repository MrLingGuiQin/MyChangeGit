package com.linguiqing.mychanage.app;

import android.app.Application;

import com.linguiqing.mychanage.util.LogUtil;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/26 0026
 * ***************************************
 */

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init("My Change", true);
    }
}
