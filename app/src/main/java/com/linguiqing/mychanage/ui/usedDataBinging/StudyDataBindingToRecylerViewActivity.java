package com.linguiqing.mychanage.ui.usedDataBinging;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.databinding.ActivityStudyDataBindingToRecylerViewBinding;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

public class StudyDataBindingToRecylerViewActivity extends BaseActivity {


    private ActivityStudyDataBindingToRecylerViewBinding mDataBinding;

    @Override
    public int getLayoutResId() {
        return 0;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_study_data_binding_to_recyler_view);
        mDataBinding.tbTitleBar.initTitlebar(true, "高级绑定--动态变量", "", null);
//        mDataBinding.rcyDataBinding.setAdapter();

    }
}
