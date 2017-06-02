package com.linguiqing.mychanage.ui.productSku;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/21 0021
 * ***************************************
 */

public class ProductSkuActivity extends BaseActivity {
    @BindView(R.id.btn_select_sku)
    Button mBtnSelectSku;
    @BindView(R.id.txt_select_sku_info)
    TextView mTxtSelectSkuInfo;
    private SelectSkuDialog mSelectSkuDialog;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_product_sku_layout;
    }

    @Override
    public void initView(Bundle savedInstanceState) {

    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "选择商品Sku", "", null);
        return super.addCommonTitlebar(root);
    }

    @OnClick(R.id.btn_select_sku)
    public void onClick() {
        if (mSelectSkuDialog == null ) {
            mSelectSkuDialog = new SelectSkuDialog((Activity) mContext, R.style.bottomDialogStyle, 1, 0.7, Gravity.BOTTOM, true);
        }
        mSelectSkuDialog.show();

    }
}
