package com.linguiqing.mychanage.util;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/23 0023
 * ***************************************
 */

public class LogUtil {
    public static boolean isPrintLog = true;

    public static void init(String tag, boolean isPrintLog) {
        Logger.init(tag)
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.FULL)        // FULL 打印输出  、 NONE 不打印
                .methodOffset(2);            // default 0
        //   .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter
        LogUtil.isPrintLog = isPrintLog;
    }

    public static void d(String msg) {
        if (isPrintLog) {
            Logger.d(msg);
        }
    }

    public static void d(String tag, String msg) {
        if (isPrintLog) {
            Logger.t(tag).d(msg);
        }
    }

    public static void e(String msg) {
        if (isPrintLog) {
            Logger.e(msg);
        }
    }

    public static void v(String msg) {
        if (isPrintLog) {
            Logger.e(msg);
        }
    }

    public static void json(String msg) {
        if (isPrintLog) {
            Logger.json(msg);
        }
    }


}
