package com.linguiqing.mychanage.ui.permission;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

/**
 * ***************************************
 * statement: 权限请求结果监听
 * auther: lingguiqin
 * date created : 2017/6/12 0012
 * ***************************************
 */

public class MyPermissionListener implements PermissionListener {
    private Activity mActivity;
    private IPermissionSuccessCallback mSuccessCallback;
    private IPermissionResultCallback mResultCallback;

    public MyPermissionListener(Activity activity, IPermissionSuccessCallback successCallback) {
        mActivity = activity;
        mSuccessCallback = successCallback;
    }

    public MyPermissionListener(Activity activity, IPermissionResultCallback result) {
        mActivity = activity;
        mResultCallback = result;
    }


    /**
     * <p>权限全部申请成功才会回调这个方法，否则回调失败的方法。</p>
     *
     * @param grantPermissions AndPermission回调过来的申请成功的权限。
     */
    @Override
    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
        if (mSuccessCallback != null) {
            mSuccessCallback.OnSucceed(requestCode, grantPermissions);
        }
        if (mResultCallback != null) {
            mResultCallback.onSucceed(requestCode, grantPermissions);
        }
    }

    /**
     * <p>只要有一个权限申请失败就会回调这个方法，并且不会回调成功的方法。</p>
     * 当用户拒绝权限并勾选了不再提示时，此时再次申请权限时将会直接回调申请失败，
     * 因此AndPermission提供了一个供用户在系统Setting中给我们授权的能力。
     *
     * @param deniedPermissions AndPermission回调过来的申请失败的权限。
     */
    @Override
    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
        if (mResultCallback != null) {
            mResultCallback.onFailed(requestCode, deniedPermissions);
        }

        // 是否有不再提示并拒绝的权限。
        if (AndPermission.hasAlwaysDeniedPermission(mActivity, deniedPermissions)) {
            // 用AndPermission默认的提示语。
            // 可定制 https://github.com/yanzhenjie/AndPermission/blob/master/README-CN.md
            // 提示用户在系统设置中授权
            AndPermission.defaultSettingDialog(mActivity, requestCode).show();
            return;
        }

        // 部分中国厂商生产手机（例如小米、华为某型号）在申请权限时，用户点击确定授权后，
        // 还是回调我们申请失败，这个时候其实我们是拥有权限的，
        // 建议在失败的回调房中调用AppOpsManager做权限判断
        // if (AndPermission.hasPermission(mActivity, deniedPermissions)) {}
        if (AndPermission.hasPermission(mActivity, deniedPermissions)) {
            // 有权限直接操作
            if (mSuccessCallback != null) {
                mSuccessCallback.OnSucceed(requestCode, deniedPermissions);
            }
            if (mResultCallback != null) {
                mResultCallback.onSucceed(requestCode, deniedPermissions);
            }
            return;
        }
    }
}
