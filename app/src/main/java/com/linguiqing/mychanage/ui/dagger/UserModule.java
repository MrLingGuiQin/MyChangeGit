package com.linguiqing.mychanage.ui.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * ***************************************
 * statement: 管理依赖的分组
 * auther: lingguiqin
 * date created : 2017/8/18 0018
 * ***************************************
 */

// 第一种引入HttpModule的方式  @Module(includes = HttpModule.class)
@Module(includes = HttpModule.class)
public class UserModule {

    private Context mContext;

    public UserModule(Context context) {
        mContext = context;
    }


    @Provides
    public ApiServer providApiServer(OkHttpClient client) {
        return new ApiServer(client);
    }

    @Provides
    public UserStore provideUserStore() {
        return new UserStore(mContext);
//        return new UserStore();
    }

    @Provides
    public UserManager providUserManager(ApiServer apiServer, UserStore userStore) {
        return new UserManager(apiServer, userStore);
    }
}
