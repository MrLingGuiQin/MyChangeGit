package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.content.Context;
import android.widget.ImageView;

import com.linguiqing.mychanage.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Predicate;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public class RxImageLoad {

    public static RxImageLoad mSingleton;
    private String mUrl;
    private ImageView mImageView;
    private final RequestCreator mRequestCreator;

    private RxImageLoad(Builder builder) {
        mRequestCreator = new RequestCreator(builder.mContext);
    }

    public static RxImageLoad with(Context context) {
        if (mSingleton == null) {
            synchronized (RxImageLoad.class) {
                if (mSingleton == null) {
                    mSingleton = new Builder(context).build();
                }
            }
        }
        return mSingleton;
    }

    public RxImageLoad load(String url) {
        mUrl = url;
        return mSingleton;
    }

    public RxImageLoad into(ImageView imageView) {
        mImageView = imageView;

        Observable.concat(mRequestCreator.getImagFromMemory(mUrl),
                mRequestCreator.getImageFromDisk(mUrl),
                mRequestCreator.getImageFromNetWork(mUrl))
                .filter(new Predicate<Image>() {
                    @Override
                    public boolean test(@NonNull Image image) throws Exception {
                        return image.getBitmap() != null;
                    }
                })
                .firstElement().toObservable()
                .subscribe(new Observer<Image>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Image image) {
                        mImageView.setImageBitmap(image.getBitmap());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtil.e(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("RxImageLoad load onComplete");
                    }
                });


        return mSingleton;
    }

    public static class Builder {
        public Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        private RxImageLoad build() {
            return new RxImageLoad(this);
        }

    }

}
