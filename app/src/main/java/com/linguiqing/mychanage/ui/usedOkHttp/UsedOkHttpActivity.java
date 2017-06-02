package com.linguiqing.mychanage.ui.usedOkHttp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.app.UrlManager;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * ***************************************
 * statement: okhttp的学习使用
 * auther: lingguiqin
 * date created : 2017/3/24 0024
 * ***************************************
 */

public class UsedOkHttpActivity extends BaseActivity {

    @BindView(R.id.btn_do_get)
    Button mBtnDoGet;
    @BindView(R.id.btn_do_post)
    Button mBtnDoPost;
    @BindView(R.id.img_imgview)
    ImageView mImageView;
    @BindView(R.id.txt_download_progress)
    TextView mTextView;
    @BindView(R.id.txt_download_progress2)
    TextView mTextView2;
    private OkHttpClient mOkHttpClient;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_used_okhttp_layout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .build();
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "OkHttp的使用", "", null);
        return titlebar;
    }

    /**
     * get请求
     *
     * @param v
     */
    public void doGet(View v) {
        // 构建一个请求
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://www.imooc.com/").get().build();
        // 创建call任务，并把request 封装到 call 中,并执行
        requestEnqueue(request);

    }

    /**
     * 执行请求
     *
     * @param request
     */
    private void requestEnqueue(Request request) {
        Call call = mOkHttpClient.newCall(request);
        // 执行任务
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e(" onFailure :  " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogUtil.d(" onResponse :  ");
                String result = response.body().string();
                LogUtil.d(result);
            }
        });
    }

    /**
     * post请求，携带参数给服务器
     *
     * @param v
     */
    public void doPost(View v) {
        Request.Builder builder = new Request.Builder();
        // 创建FormBody 对象，携带参数给服务器
        FormBody.Builder bodyBuilder = new FormBody.Builder();
        FormBody body = bodyBuilder.add("productId", "0000019621")
                .add("prodType", "0").build();

        Request request = builder.url(UrlManager.PRODUCT_DETAIL_URL)
                .post(body)
                .build();
        requestEnqueue(request);
    }


    /**
     * 下载文件，（记得要声明写入内存卡的权限）
     *
     * @param v
     */
    public void doDownLoad(View v) {
        // 构建一个请求
        Request.Builder builder = new Request.Builder();
        Request request = builder.url("http://i7.hexun.com/2017-03-23/188593484.jpg")
                .get().build();
        // 创建call任务，并把request 封装到 call 中,并执行
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("onFailure :  " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                LogUtil.e("onResponse :  ");
                // 通过文件流进行读取文件
                InputStream inputStream = response.body().byteStream();
                // 文件的总共字节数
                final long totalLength = response.body().contentLength();
                long sum = 0;
                int len = 0;
                byte[] buf = new byte[1024];
                File file = new File(Environment.getExternalStorageDirectory(), "lingguiqin.jpg");
                // if file doesnt exists, then create it
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileOutputStream fos = new FileOutputStream(file);
                while ((len = inputStream.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                    sum += len;
                    final long finalSum = sum;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextView.setText("下载进度 ： " + finalSum + " / " + totalLength);
                            NumberFormat formatter = new DecimalFormat("0.00%");
                            Double x = new Double((double) finalSum / (double) totalLength);
                            String xx = formatter.format(x);
                            mTextView2.setText("下载进度 ： " + xx);
                            LogUtil.d("下载进度 ： " + finalSum + " / " + totalLength);
                            LogUtil.d("下载进度 ： " + xx);
                        }
                    });
                }
                fos.flush();
                fos.close();
                inputStream.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("文件下载成功", Toast.LENGTH_LONG);
                    }
                });
            }
        });

    }


    // 通过url直接加载显示图片
    public void doDownLoadImage(View v) {
        Request.Builder builder = new Request.Builder();
        Request request =
                builder.get().url("http://i7.hexun.com/2017-03-23/188593484.jpg").build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtil.e("onFailure :  " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream inputStream = response.body().byteStream();
                final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mImageView.setImageBitmap(bitmap);
                    }
                });

            }
        });
    }
}
