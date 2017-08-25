package com.linguiqing.mychanage.ui.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/25 0025
 * ***************************************
 */
@Module
public class AppMoudle {
    @Singleton
    @Provides
    public OkHttpClient providOkHttpClient() {
        return new OkHttpClient().newBuilder().build();
    }
}
