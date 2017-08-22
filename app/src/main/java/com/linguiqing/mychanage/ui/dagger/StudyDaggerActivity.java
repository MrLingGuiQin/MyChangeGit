package com.linguiqing.mychanage.ui.dagger;

import android.os.Bundle;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.util.LogUtil;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;


/**
 * ***************************************
 * statement: 学习 Dagger2 注解
 * auther: lingguiqin
 * date created : 2017/3/3 0003
 * ***************************************
 */

public class StudyDaggerActivity extends BaseActivity {

    @Named("dev")
    @Inject
    ApiServer mApiServerDev;

    @Named("release")
    @Inject
    ApiServer mApiServerRelease;
    @Inject
    OkHttpClient mOkHttpClient1;
    @Inject
    OkHttpClient mOkHttpClient2;

    @Inject
    UserManager mUserManager;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_dagger;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        DaggerUserComponent.builder().userModule(new UserModule(this))
                .httpModule(new HttpModule())
                .build().inject(this);
        mApiServerDev.register();
        mApiServerRelease.register();
        mUserManager.register();

        // @Singleton修饰 同一个Component 获取的是单列对象
        // mOkHttpClient1 =  okhttp3.OkHttpClient@bc8fdee
        // mOkHttpClient2 =  okhttp3.OkHttpClient@bc8fdee
        LogUtil.e("mOkHttpClient1 =  " + mOkHttpClient1);
        LogUtil.e("mOkHttpClient2 =  " + mOkHttpClient2);
    }
}
