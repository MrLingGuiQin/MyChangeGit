package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import io.reactivex.Observable;

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

    public RequestCreator() {
        mMemoryCacheObservable = new MemoryCacheObservable();
        mDiskCacheObservable = new DiskCacheObservable();
        mNetWorkCacheObservable = new NetWorkCacheObservable();
    }


    public Observable<Image> getMemoryCacheObservable(String url) {
        return mMemoryCacheObservable.getImage(url);
    }

    public Observable<Image> getDiskCacheObservable(String url) {
        return mDiskCacheObservable.getImage(url);
    }

    public Observable<Image> getNetWorkCacheObservable(String url) {
        return mNetWorkCacheObservable.getImage(url);
    }
}
