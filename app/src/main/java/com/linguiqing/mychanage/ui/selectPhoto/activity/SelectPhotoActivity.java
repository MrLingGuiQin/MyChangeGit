package com.linguiqing.mychanage.ui.selectPhoto.activity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.ui.selectPhoto.SelectImageDirPopupWindow;
import com.linguiqing.mychanage.ui.selectPhoto.adapter.SelectPhotoAdapter;
import com.linguiqing.mychanage.ui.selectPhoto.bean.ImageFloder;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ***************************************
 * statement: 模仿微信选择图片
 * auther: lingguiqin
 * date created : 2017/3/3 0003
 * ***************************************
 */

public class SelectPhotoActivity extends BaseActivity implements SelectImageDirPopupWindow.OnSelectDirListener {

    @BindView(R.id.gv_select_photo_gridview)
    GridView mGridview;
    @BindView(R.id.txt_select_photo_choose_dir)
    TextView mTxtChooseDir;
    @BindView(R.id.txt_select_photo_total_count)
    TextView mTxtPhotoTotalCount;
    @BindView(R.id.rl_select_photo_bottom_root)
    RelativeLayout mRlSelectPhotoBottomRoot;
    @BindView(R.id.main_root)
    RelativeLayout mMainRoot;

    /**
     * 存储文件夹中的图片数量
     */
    private int mPicsSize;
    /**
     * 图片数量最多的文件夹
     */
    private File mImgDir;
    /**
     * 所有的图片
     */
    private List<String> mImgs = new ArrayList<String>();

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();

    /**
     * 扫描拿到所有的图片文件夹
     */
    private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();

    int totalCount = 0;

    private int mScreenHeight;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 图片扫描完成加载图片
            mProgressDialog.dismiss();
            // view 绑定数据
            data2View();


        }
    };
    private SelectPhotoAdapter mAdapter;
    private SelectImageDirPopupWindow mPopupWindow;

    /**
     * 为View 绑定数据
     */
    private void data2View() {
        if (mImgDir == null) {
            showToast("擦，一张图片都没找到", Toast.LENGTH_SHORT);
            return;
        }
        mTxtPhotoTotalCount.setText(totalCount + "张");
        mImgs.addAll(Arrays.asList(mImgDir.list()));
        mAdapter = new SelectPhotoAdapter(mContext, mImgs, mImgDir.getAbsolutePath(), R.layout.select_photo_grid_item);
        mGridview.setAdapter(mAdapter);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.select_photo_main;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        getScreenHight();
        getImages();
    }

    /**
     * 获取手机屏幕的高度
     */
    private void getScreenHight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mScreenHeight = dm.heightPixels;
    }


    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "微信图片加载", "", null);
        return titlebar;
    }

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }
        // 显示进度条
        mProgressDialog = ProgressDialog.show(mContext, null, "正在加载...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String firstImage = null;
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = mContext.getContentResolver();

                // 只查询 jpeg、png 的 图片
                Cursor cursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or "
                                + MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png"},
                        MediaStore.Images.Media.DATE_MODIFIED);

                while (cursor.moveToNext()) {

                    // 获取图片的路径
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Images.Media.DATA));

                    // 拿到第一张图片的路径
                    if (firstImage == null) {
                        firstImage = path;
                    }
                    // 获取改图片的父路径名
                    File parentFlie = new File(path).getParentFile();
                    if (parentFlie == null) {
                        continue;
                    }
                    String dirPath = parentFlie.getAbsolutePath();
                    ImageFloder floder = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
                    if (mDirPaths.contains(dirPath)) {
                        continue;
                    } else {
                        mDirPaths.add(dirPath);
                        floder = new ImageFloder();
                        floder.setFirstImagePath(firstImage);
                        floder.setDir(dirPath);
                        firstImage = null;
                    }
                    int picSize = parentFlie.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File file, String filename) {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith("jpeg")) {
                                return true;
                            }

                            return false;
                        }
                    }).length;

                    totalCount += picSize;
                    floder.setCount(picSize);
                    mImageFloders.add(floder);

                    // 获取到最多图片的目录文件夹
                    if (picSize > mPicsSize) {
                        mPicsSize = picSize;
                        mImgDir = parentFlie;
                    }
                }
                cursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;

                // 通知Handler扫描图片完成
                mHandler.sendEmptyMessage(0x110);
            }
        }).start();

    }

    @OnClick(R.id.rl_select_photo_bottom_root)
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rl_select_photo_bottom_root:
                mPopupWindow = new SelectImageDirPopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) (mScreenHeight * 0.6), true, mImageFloders,
                        View.inflate(mContext, R.layout.select_photo_list_dir, null));
                mPopupWindow.setAnimationStyle(R.style.anim_popupWindow);
                mPopupWindow.setOnSelectDirListener(this);
                mPopupWindow.showAtLocation(mMainRoot, Gravity.BOTTOM, 0, 0);
                mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        // 设置背景颜色变暗
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getWindow().setAttributes(lp);
                    }
                });

                // 设置背景颜色变暗
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.3f;
                getWindow().setAttributes(lp);
                break;
        }
    }

    @Override
    public void onSelect(ImageFloder imageFloder) {

        mTxtPhotoTotalCount.setText(imageFloder.getCount() + "张");
        mTxtChooseDir.setText(imageFloder.getName());
        mImgs.clear();
        mImgDir = new File(imageFloder.getDir());
        mImgs.addAll(Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        })));
        mAdapter.mImageDir = mImgDir.getAbsolutePath();
        mAdapter.notifyDataSetChanged();
        mPopupWindow.dismiss();
    }
}
