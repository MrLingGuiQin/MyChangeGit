package com.linguiqing.mychanage.ui.dagger;

import dagger.Component;
import dagger.Module;

/**
 * ***************************************
 * statement: 注解的桥梁， 注射器
 * auther: lingguiqin
 * date created : 2017/8/18 0018
 * ***************************************
 */

// 第二种方式 @Component(modules = {UserModule.class,HttpModule.class})
@Component(modules = UserModule.class)
public interface UserComponent {

    void inject(StudyDaggerActivity activity);
}
