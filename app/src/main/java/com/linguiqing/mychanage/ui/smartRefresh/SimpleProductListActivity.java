package com.linguiqing.mychanage.ui.smartRefresh;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * ***************************************
 * statement: 简单的商品列表页
 * auther: lingguiqin
 * date created : 2017/7/21 0003
 * ***************************************
 */
public class SimpleProductListActivity extends BaseActivity {


    @BindView(R.id.rcy_product_list_recylerView)
    RecyclerView mRecylerView;
    @BindView(R.id.srl_product_list_refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_simple_product_list;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(this, root);
        titlebar.initTitlebar(true, "商品列表", "切换", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("切换", Toast.LENGTH_SHORT);
            }
        });
        return titlebar;
    }
}
