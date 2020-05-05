package com.linguiqing.mychanage.util;

import com.linguiqing.mychanage.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/23 0023
 * ***************************************
 */

public class LogUtil {

    public static void init(String tag) {

    }

    public static void d(String msg) {
        if (BuildConfig.LOG_OPEN) {
            Logger.d(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (BuildConfig.LOG_OPEN) {
            Logger.t(tag).d(msg);
        }
    }

    public static void e(String msg) {
        if (BuildConfig.LOG_OPEN) {
            Logger.e(msg);
        }
    }

    public static void v(String msg) {
        if (BuildConfig.LOG_OPEN) {
            Logger.e(msg);
        }
    }

    public static void json(String msg) {
        if (BuildConfig.LOG_OPEN) {
            Logger.json(msg);
        }
    }
}
