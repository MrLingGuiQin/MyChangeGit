package com.linguiqing.mychanage.ui.dagger;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.app.MyAppLication;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.LogUtil;

import javax.inject.Inject;
import javax.inject.Named;

import okhttp3.OkHttpClient;


/**
 * ***************************************
 * statement: 用户信息
 * auther: lingguiqin
 * date created : 2017/8/25 0018
 * ***************************************
 */

public class DaggerUserActivity extends BaseActivity {

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
        return R.layout.activity_dagger_user;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "用户信息", "", null);
        return titlebar;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

//        DaggerUserComponent.builder()
//                .userModule(new UserModule(this))
//                .httpModule(new HttpModule())
//                .build().inject(this);

        DaggerUserComponent.builder()
                .userModule(new UserModule(mContext))
                .appComponent(((MyAppLication) getApplication()).getAppComponent())
                .build()
                .inject(this);

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
