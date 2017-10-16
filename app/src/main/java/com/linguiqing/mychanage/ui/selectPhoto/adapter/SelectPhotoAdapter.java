package com.linguiqing.mychanage.ui.selectPhoto.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseViewHolder;
import com.linguiqing.mychanage.base.CommonAdapter;

import java.util.LinkedList;
import java.util.List;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/9 0009
 * ***************************************
 */

public class SelectPhotoAdapter extends CommonAdapter<String> {
    public  String mImageDir;
    private List<String> mSelectImages = new LinkedList<String>();

    public SelectPhotoAdapter(Context context, List<String> mDatas, String imageDir, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        mImageDir = imageDir;
    }

    @Override
    public void convert(BaseViewHolder helper, final String item) {

        // 先设置默认的图片、选择的状态
        helper.setImageResource(R.id.img_select_photo_item_image, R.drawable.pictures_no);
        helper.setImageResource(R.id.imb_select_photo_item_select, R.drawable.picture_unselected);
        // 加载实际的图片

        helper.setImageByUrl(R.id.img_select_photo_item_image, mImageDir + "/" + item);
        final ImageButton mSelectView = helper.getView(R.id.imb_select_photo_item_select);
        final ImageView mImageView = helper.getView(R.id.img_select_photo_item_image);
        if (mSelectImages.contains(mImageDir + "/" + item)) {
            mSelectView.setImageResource(R.drawable.pictures_selected);
        }
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSelectImages.contains(mImageDir + "/" + item)) {
                    mSelectImages.remove(mImageDir + "/" + item);
                    mSelectView.setImageResource(R.drawable.picture_unselected);
                    mImageView.setColorFilter(null);
                } else {
                    mSelectImages.add(mImageDir + "/" + item);
                    mSelectView.setImageResource(R.drawable.pictures_selected);
                    mImageView.setColorFilter(Color.parseColor("#77000000"));
                }
            }
        });

    }
}
