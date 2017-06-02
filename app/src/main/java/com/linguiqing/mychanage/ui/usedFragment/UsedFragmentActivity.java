package com.linguiqing.mychanage.ui.usedFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * ***************************************
 * statement: 测试使用切换fragment 的 使用
 * auther: lingguiqin
 * date created : 2017/3/19 0019
 * ***************************************
 */

public class UsedFragmentActivity extends BaseActivity {
    @BindView(R.id.rb_home)
    RadioButton mRbHome;
    @BindView(R.id.rb_category)
    RadioButton mRbCategory;
    @BindView(R.id.rb_shopcar)
    RadioButton mRbShopcar;
    @BindView(R.id.rb_mine)
    RadioButton mRbMine;
    @BindView(R.id.rg_group)
    RadioGroup mRgGroup;
    private ContentFragment mHomeFragment;
    private ContentFragment mCategoryFragment;
    private ContentFragment mShopCarFragment;
    private ContentFragment mMineFragment;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_used_fragment_layout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mRbHome.setChecked(true);
        setDefaultFragment();
    }


    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "fragment的使用", "", null);
        return titlebar;
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        mHomeFragment = new ContentFragment();
        mHomeFragment.mContent = "我是首页";
        transaction.add(R.id.fl_fragment_content_view, mHomeFragment);
        hideFragment(transaction);
        transaction.show(mHomeFragment);
        transaction.addToBackStack(null);
        transaction.commit();
//        mTransaction.replace(R.id.fl_fragment_content_view, mHomeFragment).commit();

    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null && mHomeFragment.isAdded()) {
            transaction.hide(mHomeFragment);
        }
        if (mCategoryFragment != null && mCategoryFragment.isAdded()) {
            transaction.hide(mCategoryFragment);
        }
        if (mShopCarFragment != null && mShopCarFragment.isAdded()) {
            transaction.hide(mShopCarFragment);
        }
        if (mMineFragment != null && mMineFragment.isAdded()) {
            transaction.hide(mMineFragment);
        }
    }


    @OnClick({R.id.rb_home, R.id.rb_category, R.id.rb_shopcar, R.id.rb_mine})
    public void onClick(View view) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.rb_home:

                if (mHomeFragment == null) {
                    mHomeFragment = new ContentFragment();
                    mHomeFragment.mContent = "我是首页";
                    transaction.add(R.id.fl_fragment_content_view, mHomeFragment);
                }
                hideFragment(transaction);
                transaction.show(mHomeFragment);
                transaction.addToBackStack(null);
                transaction.commit();
//                transaction.replace(R.id.fl_fragment_content_view, mHomeFragment).commit();


                break;

            case R.id.rb_category:

                if (mCategoryFragment == null) {
                    mCategoryFragment = new ContentFragment();
                    mCategoryFragment.mContent = "我是分类";
                    transaction.add(R.id.fl_fragment_content_view, mCategoryFragment);
                }

                hideFragment(transaction);
                transaction.show(mCategoryFragment);
                transaction.addToBackStack(null);
                transaction.commit();
//                transaction.replace(R.id.fl_fragment_content_view, mCategoryFragment).commit();

                break;

            case R.id.rb_shopcar:

                if (mShopCarFragment == null) {
                    mShopCarFragment = new ContentFragment();
                    mShopCarFragment.mContent = "我是购物车";
                    transaction.add(R.id.fl_fragment_content_view, mShopCarFragment);
                }
                hideFragment(transaction);
                transaction.show(mShopCarFragment);
                transaction.addToBackStack(null);
                transaction.commit();

//                transaction.replace(R.id.fl_fragment_content_view, mShopCarFragment).commit();

                break;

            case R.id.rb_mine:

                if (mMineFragment == null) {
                    mMineFragment = new ContentFragment();
                    mMineFragment.mContent = "我是我的";
                    transaction.add(R.id.fl_fragment_content_view, mMineFragment);
                }
                hideFragment(transaction);
                transaction.show(mMineFragment);
                transaction.addToBackStack(null);
                transaction.commit();
//                transaction.replace(R.id.fl_fragment_content_view, mMineFragment).commit();
                break;
        }
    }

}
