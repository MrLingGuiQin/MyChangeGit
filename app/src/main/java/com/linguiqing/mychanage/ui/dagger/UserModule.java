package com.linguiqing.mychanage.ui.dagger;

import android.content.Context;

import com.linguiqing.mychanage.util.LogUtil;

import javax.inject.Named;

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

    @Named("dev")
    @Provides
    public ApiServer providApiServerFromDev(OkHttpClient client) {
        ApiServer apiServer = new ApiServer(client);
        LogUtil.e("开发环境请求数据啦----   " + apiServer);
        return apiServer;
    }

    @Named("release")
    @Provides
    public ApiServer providApiServerFromRelease(OkHttpClient client) {
        ApiServer apiServer = new ApiServer(client);
        LogUtil.e("正式环境请求数据啦----   " + apiServer);
        return apiServer;
    }

    @Provides
    public UserStore provideUserStore() {
        return new UserStore(mContext);
//        return new UserStore();
    }

    @Provides
    public UserManager providUserManager(@Named("dev") ApiServer apiServer, UserStore userStore) {
        return new UserManager(apiServer, userStore);
    }
}
