package com.linguiqing.mychanage.ui.Permission;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */

public interface IPermissionResultCallback {
    void onSucceed(int requestCode, List<String> grantPermissions);

    void onFailed(int requestCode, List<String> deniedPermissions);
}
