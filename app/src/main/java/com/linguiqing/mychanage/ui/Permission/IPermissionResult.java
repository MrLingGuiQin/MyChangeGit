package com.linguiqing.mychanage.ui.Permission;

import java.util.List;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/6/12 0012
 * ***************************************
 */

public interface IPermissionResult {
    void OnSucceed(int requestCode, List<String> grantPermissions);
}
