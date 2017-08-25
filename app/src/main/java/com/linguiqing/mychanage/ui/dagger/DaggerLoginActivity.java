package com.linguiqing.mychanage.ui.dagger;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.app.MyAppLication;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.LogUtil;

import javax.inject.Inject;

import okhttp3.OkHttpClient;


/**
 * ***************************************
 * statement: 登录
 * auther: lingguiqin
 * date created : 2017/8/25 0018
 * ***************************************
 */
public class DaggerLoginActivity extends BaseActivity {

    @Inject
    UserManager mUserManager;
    @Inject
    OkHttpClient mOkHttpClient;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_dagger_login;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "登录", "", null);
        return titlebar;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
//        DaggerLoginComponent.builder()
//                .httpModule(new HttpModule())
//                .userModule(new UserModule(mContext))
//                .build()
//                .inject(this);

        DaggerLoginComponent.builder()
                .userModule(new UserModule(mContext))
                .appComponent(((MyAppLication) getApplication()).getAppComponent())
                .build()
                .inject(this);
        mUserManager.login();

        //  mOkHttpClient 是单列的（跟java的单列有区别），前提是相同的 Component
        // 同一个 Component 是同一个对象，不同的Component会产生一个的对象
        LogUtil.e("mOkHttpClient =  " + mOkHttpClient);
    }
}
