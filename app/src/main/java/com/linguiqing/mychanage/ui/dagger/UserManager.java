package com.linguiqing.mychanage.ui.dagger;

import com.linguiqing.mychanage.util.LogUtil;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/20 0020
 * ***************************************
 */

public class UserManager {
    private ApiServer mApiServer;

    public UserManager(ApiServer apiServer) {
        LogUtil.e("UserManager 初始化----");
        mApiServer = apiServer;
    }

    public void login() {
        mApiServer.login();
    }

    public void register() {
        mApiServer.register();
    }
}
