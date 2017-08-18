package com.linguiqing.mychanage.ui.dagger;

import android.os.Bundle;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;

//import javax.inject.Inject;


/**
 * ***************************************
 * statement: 学习 Dagger2 注解
 * auther: lingguiqin
 * date created : 2017/3/3 0003
 * ***************************************
 */

public class StudyDaggerActivity extends BaseActivity {

//    @Inject
    ApiServer mApiServer; // 用 @Inject，表示使用注解的方式进行实例化

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_dagger;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mApiServer.register();
    }
}

