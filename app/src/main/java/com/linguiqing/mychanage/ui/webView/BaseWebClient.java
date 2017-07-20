package com.linguiqing.mychanage.ui.webView;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.util.LogUtil;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/6/28 0028
 * ***************************************
 */

public class BaseWebClient extends WebViewClient {
    BaseActivity mActivity;

    public BaseWebClient(BaseActivity activity) {
        mActivity = activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        LogUtil.e("shouldOverrideUrlLoading = " + url);
//        if (url.contains("native://goBack")) {
//            if (view.canGoBack()) {
//                view.goBack();
//            } else {
//                view.stopLoading();
//                LogUtil.e("需要finish界面啦 " + url);
//            }
//            return false;
//        }

        return false;
    }

//    @Override
//    public boolean shouldOverrideUrlLoading(WebView view, String url) {
//        return super.shouldOverrideUrlLoading(view, url);
//    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        LogUtil.e("onPageStarted = " + url);
        mActivity.showProgress();
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        LogUtil.e("onPageFinished = " + url);
        mActivity.dismisProgress();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        LogUtil.e("onReceivedError =  网页加载出错啦 " + error.toString() + "\n"
                + request.getUrl().toString());
    }
}
