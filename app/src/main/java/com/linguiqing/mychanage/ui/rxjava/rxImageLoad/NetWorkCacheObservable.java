package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public class NetWorkCacheObservable extends CacheObservable {
    @Override
    public Image getDataFromCache(String url) {
        Bitmap bitmap = loadImage(url);
        if (bitmap != null) {
            return new Image(url, bitmap);
        }
        return null;
    }

    @Override
    public void putDataToCache(Image image) {

    }

    public Bitmap loadImage(String url) {
        Bitmap bitmap = null;
        InputStream inputStream = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            inputStream = urlConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bitmap;
    }
}
