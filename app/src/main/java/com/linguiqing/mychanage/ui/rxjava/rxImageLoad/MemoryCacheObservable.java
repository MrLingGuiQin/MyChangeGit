package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.linguiqing.mychanage.util.LogUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * ***************************************
 * statement: 内存获取缓存图片
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public class MemoryCacheObservable extends CacheObservable {

    int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    int cacheSize = maxMemory / 8;

    LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap value) {
            return value.getRowBytes() * value.getHeight() / 1024;
        }
    };

    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = mLruCache.get(url);
        return new Image(url, bitmap);

    }

    @Override
    public void putDataToCache(Image image) {
        // 指定在io子线程执行耗时操作
        Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Image> e) throws Exception {
                LogUtil.e("putDataTo  Cache");
                mLruCache.put(image.getUrl(), image.getBitmap());
            }
        }).subscribeOn(Schedulers.io()).subscribe();
    }

}
