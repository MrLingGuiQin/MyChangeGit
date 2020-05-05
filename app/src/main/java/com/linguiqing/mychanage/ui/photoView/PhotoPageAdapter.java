package com.linguiqing.mychanage.ui.photoView;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.linguiqing.mychanage.R;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/6/18 0018
 * ***************************************
 */

public class PhotoPageAdapter extends PagerAdapter {
    private int[] mImageArray = new int[]{R.drawable.wallpaper,
            R.drawable.wallpaper, R.drawable.wallpaper};

    @Override
    public int getCount() {
        return mImageArray.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        photoView.setImageResource(mImageArray[position]);
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
