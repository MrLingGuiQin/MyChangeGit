package com.linguiqing.mychanage.ui.dagger;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

import butterknife.OnClick;


/**
 * ***************************************
 * statement: 学习 Dagger2 注解
 * auther: lingguiqin
 * date created : 2017/3/3 0003
 * ***************************************
 */

public class StudyDaggerActivity extends BaseActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_dagger;
    }


    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "Dagger2的使用", "", null);
        return titlebar;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_dagger_login, R.id.btn_dagger_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_dagger_login:
                goToCustomActivity(DaggerLoginActivity.class);
                break;
            case R.id.btn_dagger_user:
                goToCustomActivity(DaggerUserActivity.class);
                break;
        }
    }
}
