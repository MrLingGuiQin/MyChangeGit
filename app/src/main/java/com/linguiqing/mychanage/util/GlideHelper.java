package com.linguiqing.mychanage.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.linguiqing.mychanage.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/25 0025
 * ***************************************
 */

public class GlideHelper {

    private GlideHelper (){

    }

    public static void loadImage(Context context, String url, ImageView  imageView) {
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.img_default)
                .into(imageView);
    }

    public static void loadImage2(Context context, String url, ImageView  imageView) {
        Glide.with(context)
                .load(url)
                .bitmapTransform(new CropCircleTransformation(context))
                .placeholder(R.drawable.img_default)
                .into(imageView);
    }

}
