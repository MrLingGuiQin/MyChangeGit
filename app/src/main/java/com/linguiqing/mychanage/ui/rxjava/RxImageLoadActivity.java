package com.linguiqing.mychanage.ui.rxjava;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.util.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

public class RxImageLoadActivity extends BaseActivity {


    @BindView(R.id.btn_rx_image_load)
    Button mBtnRxImageLoad;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_rx_image_load;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        Observable<String> memoryObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("memory");
                e.onComplete();
            }
        });

        Observable<String> diskObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("disk");
                e.onComplete();
            }
        });


        Observable<String> netWorkObservable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("netWork");
                e.onComplete();
            }
        });


        RxView.clicks(mBtnRxImageLoad).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {

                Observable.concat(memoryObservable, diskObservable, netWorkObservable)
                        .filter(new Predicate<String>() {
                            @Override
                            public boolean test(@NonNull String s) throws Exception {
                                return !TextUtils.isEmpty(s);
                            }
                        }).firstElement().toObservable()
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(@NonNull String s) throws Exception {
                                LogUtil.d(s);
                            }
                        });


            }
        });
    }
}
