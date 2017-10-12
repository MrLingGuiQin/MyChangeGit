package com.linguiqing.mychanage.ui.review.activity;

import android.os.Bundle;
import android.view.View;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/12.
 * 学习启动模式
 */

public class LauncherModeActivity extends BaseActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_review_launcher_mode;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }



    @OnClick({R.id.btn_mode_standard, R.id.btn_mode_singleTop, R.id.btn_mode_singleTask, R.id.btn_mode_singleInstance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_mode_standard:
                break;
            case R.id.btn_mode_singleTop:
                break;
            case R.id.btn_mode_singleTask:
                break;
            case R.id.btn_mode_singleInstance:
                goToCustomActivity(FirstActivity.class);
                break;
        }
    }
}
