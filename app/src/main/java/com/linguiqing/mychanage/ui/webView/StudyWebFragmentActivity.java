package com.linguiqing.mychanage.ui.webView;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;

/**
 * Created by Administrator on 2017/7/3.
 */

public class StudyWebFragmentActivity extends BaseActivity {

    private WebFragment mWebFragment;

    @Override
    public int getLayoutResId() {
        return R.layout.common_framelayout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        buildWebFragment();
        changeShowFragment();
    }

    private void buildWebFragment() {
        if (mWebFragment == null) {
            mWebFragment = new WebFragment();
        }
    }

    private void changeShowFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.fl_content, mWebFragment, "webFragment");
        transaction.show(mWebFragment);
        transaction.commit();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebFragment != null && mWebFragment.mWebView != null) {
                if (mWebFragment.mWebView.canGoBack()) {
                    mWebFragment.mWebView.goBack();
                    return true;
                }
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
