package com.linguiqing.mychanage.ui.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * ***************************************
 * statement: http 网络请求的 module
 * auther: lingguiqin
 * date created : 2017/8/20 0020
 * ***************************************
 */

@Module
public class HttpModule {

    @Singleton
    @Provides
    public OkHttpClient mOkHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }

}
