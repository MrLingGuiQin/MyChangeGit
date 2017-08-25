package com.linguiqing.mychanage.ui.dagger;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ***************************************
 * statement: 注解的桥梁， 注射器
 * auther: lingguiqin
 * date created : 2017/8/18 0018
 * ***************************************
 */

// 第二种方式 @Component(modules = {UserModule.class,HttpModule.class})
@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {

    void inject(DaggerUserActivity activity);
}
