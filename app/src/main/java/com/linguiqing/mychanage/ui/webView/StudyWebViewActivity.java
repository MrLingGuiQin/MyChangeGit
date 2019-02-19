
package com.linguiqing.mychanage.ui.webView;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

import butterknife.BindView;

public class StudyWebViewActivity extends BaseActivity {
    @BindView(R.id.wb_study_web_view)
    BaseWebView mWebView;

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.handleGoBack = false;
        titlebar.initTitlebar(true, "WebView的使用", "刷新", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("刷新webView啦", Toast.LENGTH_SHORT);
                mWebView.reload();
            }
        });
        titlebar.mImgGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mWebView != null && mWebView.canGoBack()) {
                    mWebView.goBack();
                    return;
                }
                finish();
            }
        });
        return titlebar;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_web_view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mWebView.setWebViewClient(new BaseWebClient(this));
        mWebView.loadUrl("https://m.jd.com/");
    }

    // 判断webView是否有goback
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true; // 代表自己处理
        }
        return super.onKeyDown(keyCode, event);
    }

}