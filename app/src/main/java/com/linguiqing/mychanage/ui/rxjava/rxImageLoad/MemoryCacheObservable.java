package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.graphics.Bitmap;
import android.util.LruCache;

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
        if (bitmap != null) {
            return new Image(url, bitmap);
        }
        return null;
    }

    @Override
    public void putDataToCache(Image image) {

    }


}
