package com.linguiqing.mychanage.ui.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ***************************************
 * statement:  登录类注射器
 * auther: lingguiqin
 * date created : 2017/8/25 0025
 * ***************************************
 */
@ActivityScope
@Component(modules = UserModule.class,dependencies = AppComponent.class)
public interface LoginComponent {
    void inject(DaggerLoginActivity activity);
}
