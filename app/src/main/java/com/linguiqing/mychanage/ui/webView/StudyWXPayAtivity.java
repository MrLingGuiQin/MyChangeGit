package com.linguiqing.mychanage.ui.webView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.util.LogUtil;

import butterknife.BindView;

/**
 * ***************************************
 * statement: 微信H5支付测试类
 * auther: lingguiqin
 * date created : 2017/6/28 0028
 * ***************************************
 */

public class StudyWXPayAtivity extends BaseActivity {

    @BindView(R.id.wb_study_web_view)
    BaseWebView2 mWebView;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_study_web_view;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mWebView.loadUrl("http://wxpay.wxutil.com/mch/pay/h5.v2.php");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView wv, String url) {
                LogUtil.e(url);
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    return false;
                }

                try {
                    //其他自定义的scheme
                    if (url.startsWith("weixin://")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    // 这里提示没有安装微信
                    showToast("未检测到微信客户端", Toast.LENGTH_LONG);
                    return false;
                }

                //处理http和https开头的url
                wv.loadUrl(url);
                return true;
            }
        });
    }


    /**
     * 捕获物理返回键，判断webView是否有goback
     *
     * @param keyCode
     * @param event
     * @return
     */
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


