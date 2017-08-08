package com.linguiqing.mychanage.ui.rxjava.rxImageLoad;

import android.graphics.Bitmap;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/8/8 0008
 * ***************************************
 */

public class Image {
    private String url;
    private Bitmap bitmap;

    public Image(String url, Bitmap bitmap) {
        this.url = url;
        this.bitmap = bitmap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
