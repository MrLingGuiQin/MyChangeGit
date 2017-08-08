package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public class DiskCacheObservable extends CacheObservable {
    private Context mContext;
    private long maxSize = 20 * 1024 * 1024; // 缓存的最大值容量
    private DiskLruCache mDiskLruCache;

    public DiskCacheObservable(Context context) {
        mContext = context;
        initDiskLruCache();
    }

    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = getDataFromDiskLruCache(url);
        if (bitmap != null) {
            return new Image(url, bitmap);
        }
        return null;
    }

    @Override
    public void putDataToCache(Image image) {

    }


    /**
     * 初始化 DiskLruCache
     */
    public void initDiskLruCache() {

        try {
            File cacheDir = DiskUtil.getDiskCacheDir(mContext, "image_cache");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            int appVersion = DiskUtil.getAppVersion(mContext);

            mDiskLruCache = DiskLruCache.open(cacheDir, appVersion, 1, maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 从缓存目录下读取图片
     *
     * @param url
     * @return
     */
    public Bitmap getDataFromDiskLruCache(String url) {
        Bitmap bitmap = null;
        FileInputStream fileInputStream = null;
        DiskLruCache.Snapshot snapshot = null;
        FileDescriptor fileDescriptor = null;
        try {
            snapshot = mDiskLruCache.get(DiskUtil.hashKeyForDisk(url));

            if (snapshot != null) {
                fileInputStream = (FileInputStream) snapshot.getInputStream(0);
                fileDescriptor = fileInputStream.getFD();
            }

            if (fileDescriptor != null) {
                bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
