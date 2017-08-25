package com.linguiqing.mychanage.ui.dagger;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/25 0025
 * ***************************************
 */

@Singleton
@Component(modules = AppMoudle.class)
public interface AppComponent {

    OkHttpClient okHttpClient();
}
