package com.linguiqing.mychanage.ui.Permission;

import android.app.Activity;

import com.yanzhenjie.permission.AndPermission;

/**
 * ***************************************
 * statement: android6.0 权限管理工具类
 * auther: lingguiqin
 * date created : 2017/6/12 0012
 * ***************************************
 */

public class PermissionUtil {
    public static final int REQUEST_PHONE_CODE = 0x1001; // 获取拨打电话权限请求码
    public static final int REQUEST_CONTACTS_CODE = 0x1002; // 获取联系人权限请求码
    public static final int REQUEST_LOCATION_CODE = 0x1003; // 获取定位权限请求码
    public static final int REQUEST_MICROP_CODE = 0x1004; //获取录音、麦克风权限请求码
    public static final int REQUEST_STORAGE_CODE = 0x1005; // 获取内存卡读写权限请求码
    public static final int REQUEST_SMS_CODE = 0x1006; // 获取短信权限请求码
    public static final int REQUEST_CAMERA_CODE = 0x1007; // 获取照相机权限请求码
    public static final int REQUEST_CALENDAR_CODE = 0x1008; // 获取日历权限请求码


    public static void request(Activity activity,
                               int requestCode,
                               IPermissionResult result,
                               String... permission) {

        AndPermission.with(activity)
                .requestCode(requestCode)
                .permission(permission)
                .callback(new MyPermissionListener(activity, result))
                .rationale(new MyRationaleListener(activity))
                .start();
    }
}



