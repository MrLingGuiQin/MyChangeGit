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
    private UserStore mUserStore;

    public UserManager(ApiServer apiServer, UserStore userStore) {
        LogUtil.e("UserManager 初始化----");
        mUserStore = userStore;
        mApiServer = apiServer;
    }

    public void register() {
        mApiServer.register();
        mUserStore.register();
    }
}
