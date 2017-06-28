package com.linguiqing.mychanage.ui.WebView;

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
        // 第一种url不携带参数
//        String url = "http://m.gxyj.com:83/active/ningxiahongjiuapp/index.html";
        // 第二种 url携带参数get请求
//        String url = "http://10.1.102.166:8680/homepage/getGoodShop.jhtml?category=18&positionName=00000000";
        // 第三种 url携带参数post请求
        String url = "http://10.1.102.166:8680/homepage/getGoodShop.jhtml";
        String params = "category=18&positionName=00000000";
//        mWebView.loadUrl(url);
        mWebView.postUrl(url, params.getBytes());
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
