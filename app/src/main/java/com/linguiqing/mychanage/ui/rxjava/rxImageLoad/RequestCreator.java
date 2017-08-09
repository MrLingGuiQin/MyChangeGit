package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.linguiqing.mychanage.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public class RequestCreator {

    private MemoryCacheObservable mMemoryCacheObservable;
    private DiskCacheObservable mDiskCacheObservable;
    private NetWorkCacheObservable mNetWorkCacheObservable;

    public RequestCreator(Context context) {
        mMemoryCacheObservable = new MemoryCacheObservable();
        mDiskCacheObservable = new DiskCacheObservable(context);
        mNetWorkCacheObservable = new NetWorkCacheObservable();
    }


    public Observable<Image> getImagFromMemory(String url) {
        return mMemoryCacheObservable.getImage(url).filter(new Predicate<Image>() {
            @Override
            public boolean test(@NonNull Image image) throws Exception {
                return image.getBitmap() != null;
            }
        }).doOnNext(new Consumer<Image>() {
            @Override
            public void accept(@NonNull Image image) throws Exception {
                LogUtil.e("getImageFrom  Memory");
            }
        });
    }

    public Observable<Image> getImageFromDisk(String url) {
        return mDiskCacheObservable.getImage(url)
                .filter(new Predicate<Image>() {
                    @Override
                    public boolean test(@NonNull Image image) throws Exception {
                        return image.getBitmap() != null;
                    }
                }).doOnNext(new Consumer<Image>() {
                    @Override
                    public void accept(@NonNull Image image) throws Exception {
                        LogUtil.e("getImageFrom  Disk");
                        mMemoryCacheObservable.putDataToCache(image);
                    }
                });
    }

    /**
     * doOnNext 会在onnext之前执行
     *
     * @param url
     * @return
     */
    public Observable<Image> getImageFromNetWork(String url) {
        return mNetWorkCacheObservable.getImage(url)
                .filter(new Predicate<Image>() {
                    @Override
                    public boolean test(@NonNull Image image) throws Exception {
                        return image.getBitmap() != null;
                    }
                })
                .doOnNext(new Consumer<Image>() {
                    @Override
                    public void accept(@NonNull Image image) throws Exception {
                        LogUtil.e("getImageFrom  NetWork");
                        mDiskCacheObservable.putDataToCache(image);
                        mMemoryCacheObservable.putDataToCache(image);
                    }
                });
    }
}
