package com.linguiqing.mychanage.ui.webView;

import android.os.Bundle;
import android.view.KeyEvent;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;

import butterknife.BindView;

/**
 * ***************************************
 * statement: webView测试类
 * auther: lingguiqin
 * date created : 2017/6/28 0028
 * ***************************************
 */

public class StudyWebActivity extends BaseActivity {

    @BindView(R.id.wb_study_web_view)
    BaseWebView2 mWebView;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_web_view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mWebView.loadUrl("https://m.jd.com/");
        mWebView.setWebViewClient(new BaseWebClient(this));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }
}
