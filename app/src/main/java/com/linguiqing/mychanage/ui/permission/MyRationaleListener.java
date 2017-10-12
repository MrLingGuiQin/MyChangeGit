package com.linguiqing.mychanage.ui.permission;

import android.app.Activity;

import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

/**
 * ***************************************
 * statement: 用户拒绝了一次申请权限，再次申请的回调监听
 * auther: lingguiqin
 * date created : 2017/6/12 0012
 * ***************************************
 */


public class MyRationaleListener implements RationaleListener {
    private Activity activity;

    public MyRationaleListener(Activity activity) {
        this.activity = activity;
    }

    /**
     * rationale 作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
     * 这样避免用户勾选不再提示，导致以后无法申请权限。
     * 你也可以不设置。
     *
     * @param requestCode
     * @param rationale
     */

    @Override
    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
//        AndPermission.rationaleDialog(LauncherModeActivity, rationale).show();
        rationale.resume();
    }
}
