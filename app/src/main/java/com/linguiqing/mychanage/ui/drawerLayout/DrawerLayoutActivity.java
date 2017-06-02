package com.linguiqing.mychanage.ui.drawerLayout;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ***************************************
 * statement:  使用DrawerLayout 实现测滑菜单效果
 * auther: lingguiqin
 * date created : 2017/4/27 0027
 * ***************************************
 */

public class DrawerLayoutActivity extends BaseActivity {
    @BindView(R.id.fl_drawer_layout_content)
    FrameLayout mFlContent;
    @BindView(R.id.lsv_drawer_layout_menu)
    ListView mLsvMenu;
    @BindView(R.id.dl_drawer_layout)
    DrawerLayout mDlDrawerLayout;
    @BindView(R.id.txt_drawer_layout_content)
    TextView mTxtContent;
    private List<String> mMenuList;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_drawer_layout;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.initTitlebar(true, "DrawerLayout使用", "", null);
        return titlebar;
    }

    @Override
    public void initData() {
        mMenuList = new ArrayList<>();
        mMenuList.add("首页");
        mMenuList.add("分类");
        mMenuList.add("购物车");
        mMenuList.add("我的商城");
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mLsvMenu.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMenuList));
        mLsvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mTxtContent.setText(mMenuList.get(position));
                mDlDrawerLayout.closeDrawers();
            }
        });

        mDlDrawerLayout.openDrawer(mLsvMenu);

        // 活动监听
        mDlDrawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                showToast("菜单打开啦", Toast.LENGTH_SHORT);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                showToast("菜单关闭啦", Toast.LENGTH_SHORT);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

    }


}
