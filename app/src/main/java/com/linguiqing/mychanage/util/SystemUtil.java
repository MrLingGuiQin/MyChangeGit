package com.linguiqing.mychanage.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

/**
 * ***************************************
 * statement: 系统工具类
 * auther: lingguiqin
 * date created : 2017/6/10 0010
 * ***************************************
 */

public class SystemUtil {

    public static void call(Activity activity, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            activity.startActivity(intent);
        }
    }

    public static void callView(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
