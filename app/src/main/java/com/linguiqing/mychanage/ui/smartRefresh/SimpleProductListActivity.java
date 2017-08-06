package com.linguiqing.mychanage.ui.smartRefresh;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

public class SimpleProductListActivity extends BaseActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_simple_product_list;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.initTitlebar(true, "商品列表", "", null);
        return titlebar;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

}
