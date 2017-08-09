package com.linguiqing.mychanage.ui.rxjava;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.jakewharton.rxbinding2.view.RxView;
import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.rxjava.rxImageLoad.RxImageLoad;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class RxImageLoadActivity extends BaseActivity {

    @BindView(R.id.btn_rx_image_load)
    Button mBtnRxImageLoad;
    @BindView(R.id.img_rx_image_load)
    ImageView mImageView;
    String mUrl = "https://qlogo3.store.qq.com/qzone/824801022/824801022/100?1379126765";

    @Override
    public int getLayoutResId() {
        return R.layout.activity_rx_image_load;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        RxView.clicks(mBtnRxImageLoad).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                RxImageLoad.with(mContext).load(mUrl).into(mImageView);
            }
        });
    }
}
