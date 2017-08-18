package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.linguiqing.mychanage.util.LogUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

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
        return new Image(url, bitmap);
    }

    @Override
    public void putDataToCache(final Image image) {
        // 指定在io子线程执行耗时操作
        Observable.create(new ObservableOnSubscribe<Image>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Image> e) throws Exception {
                LogUtil.e("putDataTo Disk");
                putDataToDiskLruCache(image);
            }
        }).subscribeOn(Schedulers.io()).subscribe();
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


    public void putDataToDiskLruCache(Image image) {
        try {
            DiskLruCache.Editor edit = mDiskLruCache.edit(DiskUtil.hashKeyForDisk(image.getUrl()));
            OutputStream outputStream = edit.newOutputStream(0);
            if (downloadUrlToStream(image.getUrl(), outputStream)) {
                edit.commit();
            } else {
                edit.abort();
            }
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
