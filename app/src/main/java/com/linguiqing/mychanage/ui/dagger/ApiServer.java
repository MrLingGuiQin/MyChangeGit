package com.linguiqing.mychanage.ui.dagger;

import com.linguiqing.mychanage.util.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/18 0018
 * ***************************************
 */

public class ApiServer {
    OkHttpClient mOkHttpClient;

    public static final MediaType JSON = MediaType.parse("application/json; charset = utf-8");

    public ApiServer(OkHttpClient okHttpClient) {
        mOkHttpClient = okHttpClient;
//        LogUtil.e("ApiServer 初始化----");
    }

    public void register() {
        LogUtil.e("请求网络数据拉----");
        // 模拟云端保存数据
        RequestBody body = RequestBody.create(JSON, "");
        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .post(body)
                .build();

        mOkHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        LogUtil.e("onFailure=====");
                        LogUtil.e(call.toString());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

//                        LogUtil.e("onResponse=====");
//                        LogUtil.e(response.body().toString());
                    }
                });

    }
}
