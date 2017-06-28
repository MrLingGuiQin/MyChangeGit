package com.linguiqing.mychanage.ui.photoView;

import android.os.Bundle;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;

import butterknife.BindView;

/**
 * ***************************************
 * statement: photoView的使用
 * auther: lingguiqin
 * date created : 2017/3/19 0019
 * ***************************************
 */
public class StudyPhotoViewActivity extends BaseActivity {
    @BindView(R.id.photo_view_view_pager)
    HackyViewPager mHackyViewPager;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_photo_view;
    }


    @Override
    public void initView(Bundle savedInstanceState) {
        PhotoPageAdapter adapter = new PhotoPageAdapter();
        mHackyViewPager.setAdapter(adapter);
    }

}
