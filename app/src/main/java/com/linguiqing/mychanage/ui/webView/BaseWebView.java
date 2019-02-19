package com.linguiqing.mychanage.ui.webView;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/6/28 0028
 * ***************************************
 */

public class BaseWebView extends WebView {
    public BaseWebView(Context context) {
        super(context);
        initWebViewSetting();
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebViewSetting();
    }

    public void initWebViewSetting() {
        WebSettings settings = getSettings();
        //设置Js开启
        settings.setJavaScriptEnabled(true);

        //User-agent设置，标示由谁请求，提交订单确认支付的时候需要指定
        settings.setUserAgentString("添加客户端的标识" + settings.getUserAgentString());

        //缓存相关
        // 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性
        settings.setAppCacheEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);

        // 设置使用webview推荐的窗口
        settings.setUseWideViewPort(true);
        //设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用
        settings.setLoadWithOverviewMode(true);
        // HTML5的地理位置服务,设置为true,启用地理定位
        settings.setGeolocationEnabled(true);
        //显示放大缩小按钮,设为false,不允许
        settings.setBuiltInZoomControls(false);

        // 设置可以访问文件
        settings.setAllowFileAccess(true);

        // 设置默认的缓存模式
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 从Android5.0 api21开始，WebView默认不支持同时加载Https和Http混合模式。设置允许
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 提高渲染的优先级
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //设置是否打开 WebView 表单数据的保存功能
        settings.setSaveFormData(true);
//        settings.setSavePassword(false);
        // 设置可以支持缩放
        settings.setSupportZoom(true);

        // 设置显示水平滚动条,就是网页右边的滚动条.设置不显示
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);

        // WebViewClient可以监听网页的跳转和资源加载等等
        setWebViewClient(new WebViewClient());
        setFocusable(true);
        requestFocus(View.FOCUS_DOWN);
    }

}
