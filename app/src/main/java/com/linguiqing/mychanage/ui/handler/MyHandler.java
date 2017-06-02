package com.linguiqing.mychanage.ui.handler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/3 0003
 * ***************************************
 */

public class MyHandler extends Handler {

    public MyHandler() {
    }

    public MyHandler(Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg) {
        Log.d("myHandler", "handleMessage...");
        super.handleMessage(msg);
        // 在这更新UI操作
    }

}
