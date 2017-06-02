package com.linguiqing.mychanage.ui.selectPhoto;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BasePopupWindowForListView;
import com.linguiqing.mychanage.base.BaseViewHolder;
import com.linguiqing.mychanage.base.CommonAdapter;
import com.linguiqing.mychanage.ui.selectPhoto.bean.ImageFloder;

import java.util.List;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/10 0010
 * ***************************************
 */

public class SelectImageDirPopupWindow extends BasePopupWindowForListView<ImageFloder> {


    private ListView mDirListView;
    private OnSelectDirListener onSelectDirListener;

    public  void setOnSelectDirListener(OnSelectDirListener onSelectDirListener) {
        this.onSelectDirListener = onSelectDirListener;
    }

    @Override
    protected void beforeInitWeNeedSomeParams(Object... params) {

    }

    public SelectImageDirPopupWindow(int width, int height, boolean focusable, List<ImageFloder> mDatas, View contentView) {
        super(contentView, width, height, focusable, mDatas);
    }

    @Override
    public void initViews() {
        mDirListView = (ListView) findViewById(R.id.lsv_select_photo_list_dir);
        mDirListView.setAdapter(new CommonAdapter<ImageFloder>(context, mDatas, R.layout.select_photo_list_dir_item) {
            @Override
            public void convert(BaseViewHolder helper, ImageFloder item) {
                helper.setImageResource(R.id.img_select_photo_dir_item_image, R.drawable.pictures_no);
                helper.setText(R.id.txt_select_photo_dir_item_name, "");
                helper.setText(R.id.txt_select_photo_dir_item_count, "");

                helper.setImageByUrl(R.id.img_select_photo_dir_item_image, item.getFirstImagePath());
                helper.setText(R.id.txt_select_photo_dir_item_name, item.getName());
                helper.setText(R.id.txt_select_photo_dir_item_count, item.getCount() + "");

            }
        });
    }

    @Override
    public void initEvents() {
        mDirListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (onSelectDirListener != null) {
                    onSelectDirListener.onSelect(mDatas.get(position));
                }

            }
        });
    }

    @Override
    public void init() {

    }

    public interface OnSelectDirListener {
        void onSelect(ImageFloder imageFloder);
    }
}
