package com.linguiqing.mychanage.ui.productSku;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.util.BaseCustomDialog;
import com.linguiqing.mychanage.util.GlideHelper;
import com.linguiqing.mychanage.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/22 0022
 * ***************************************
 */

public class SelectSkuDialog extends BaseCustomDialog implements View.OnClickListener, SkuAdapter.OnChangeSkuProductDataListener {

    private ImageView mImgProductImage;
    private TextView mTxtProductPrice;
    private TextView mTxtProductStorage;
    private TextView mTxtProductSelectInfo;
    private ListView mLsvSku;
    private ProductBean mProductBean;
    private Button mBtnOk;

    public SelectSkuDialog(Activity activity, int style, double widthPercentage, double heightPercentage, int gravity, boolean isCanceledOnTouchOutside) {
        super(activity, style, widthPercentage, heightPercentage, gravity, isCanceledOnTouchOutside);
        initData();
    }


    @Override
    public View initView() {
        View dialogView = mInflater.inflate(R.layout.dialog_select_sku_layout, null, false);
        mImgProductImage = (ImageView) dialogView.findViewById(R.id.img_product_sku_dialog_product_image);
        mTxtProductPrice = (TextView) dialogView.findViewById(R.id.txt_product_sku_dialog_product_price);
        mTxtProductStorage = (TextView) dialogView.findViewById(R.id.txt_product_sku_dialog_product_storage);
        mTxtProductSelectInfo = (TextView) dialogView.findViewById(R.id.txt_product_sku_dialog_select_product_info);
        mTxtProductSelectInfo = (TextView) dialogView.findViewById(R.id.txt_product_sku_dialog_select_product_info);
        mBtnOk = (Button) dialogView.findViewById(R.id.btn_product_sku_dialog_close);
        mLsvSku = (ListView) dialogView.findViewById(R.id.lsv_product_sku_dialog_product_sku);
        mBtnOk.setOnClickListener(this);
        return dialogView;
    }

    private void initData() {
        mProductBean = GsonUtil.StringToObject(SkuDataJson.PRODUCT_DATA, ProductBean.class);
        // 处理不能购买的sku组合数据
        List<String> failureSkuList = new ArrayList<>();
        if (mProductBean.getSkuProductList() != null
                && mProductBean.getSkuProductList().size() > 0) {
            for (ProductBean.SkuProductListBean skuProductListBean : mProductBean.getSkuProductList()) {
                if (skuProductListBean.getSkuStorage().equals("0")) {
                    failureSkuList.add(skuProductListBean.getSkuGroup());
                }
            }
            mProductBean.setSkuProductFailureList(failureSkuList);
        }

        // 处理sku不可点击的数据
        if (mProductBean.getSkuTypeList() != null
                && mProductBean.getSkuTypeList().size() > 0) {
            for (ProductBean.SkuTypeListBean skuTypeListBean : mProductBean.getSkuTypeList()) {
                skuTypeListBean.setSkuAttributeFailureList(new ArrayList<String>());
            }
        }

        SkuAdapter mAdapter = new SkuAdapter(mActivity, mProductBean, R.layout.dialog_listview_sku_item);
        mAdapter.setOnChangeSkuProductDataListener(this);
        mLsvSku.setAdapter(mAdapter);
    }

    @Override
    public void updateSkuProductData(ProductBean.SkuProductListBean skuProductListBean) {
        if (skuProductListBean != null) {
            mTxtProductPrice.setText("￥" + skuProductListBean.getSkuPrice());
            mTxtProductStorage.setText("库存：" + skuProductListBean.getSkuStorage() + skuProductListBean.getProdPropName());
            GlideHelper.loadImage(mActivity, skuProductListBean.getProdColorPropImg(), mImgProductImage);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_product_sku_dialog_close:
                cancel();
                break;
        }
    }


}
