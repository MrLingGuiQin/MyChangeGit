package com.linguiqing.mychanage.ui.WebView;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.linguiqing.mychanage.R;


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
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void initWebViewSetting() {
        // 视觉方面
        // 设置背景、拖动条
        setHorizontalScrollBarEnabled(false);
        setBackgroundColor(getResources().getColor(R.color.white));
        // 常用属性配置
        //设置Js开启（不开启，你玩个毛线。实际场景中一般用于定位问题）
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        //缓存相关
//        settings.setAppCacheEnabled(true);
//        settings.setDatabaseEnabled(true);
//        settings.setDomStorageEnabled(true);
        //User-agent设置，标示由谁请求
        settings.setUserAgentString("android");
    }

}
