package com.linguiqing.mychanage.ui.review.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/12.
 * 学习启动模式
 */

public class SecondActivity extends BaseActivity {

    @BindView(R.id.tv_review_second_name)
    TextView mTvName;
    @BindView(R.id.tv_review_second_taskId)
    TextView mTvTaskId;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_review_second;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mTvName.setText(this.toString());
        mTvTaskId.setText("current task id : " + this.getTaskId());
    }

    @OnClick(R.id.btn_review_go_first)
    public void onViewClicked() {
        goToCustomActivity(FirstActivity.class);
    }
}
