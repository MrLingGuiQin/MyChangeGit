package com.linguiqing.mychanage.ui.usedDataBinging;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.databinding.ActivityStudyDataBindingToRecylerViewBinding;
import com.linguiqing.mychanage.interfaces.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class StudyDataBindingToRecylerViewActivity extends BaseActivity {


    private ActivityStudyDataBindingToRecylerViewBinding mDataBinding;
    private List<UserBean> mList;

    @Override
    public int getLayoutResId() {
        return 0;
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mList.add(new UserBean("冰淇淋" + i, "   android开发工程师", "   22岁"));
        }
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_study_data_binding_to_recyler_view);
        DataBindingAdapter<UserBean> mAdapter = new DataBindingAdapter<UserBean>(mList, R.layout.item_study_data_binding_recylerview, com.android.databinding.library.baseAdapters.BR.user2);
        mDataBinding.rcyDataBinding.setLayoutManager(new LinearLayoutManager(this));
        mDataBinding.rcyDataBinding.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                showToast("您点击了第" + position + "个条目", Toast.LENGTH_SHORT);
            }
        });
        mDataBinding.imgTitleBarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
