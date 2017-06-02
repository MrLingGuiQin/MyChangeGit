package com.linguiqing.mychanage.ui.productSku;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseViewHolder;
import com.linguiqing.mychanage.base.CommonAdapter;
import com.linguiqing.mychanage.util.DPUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/22 0022
 * ***************************************
 */

public class SkuAdapter extends CommonAdapter<ProductBean.SkuTypeListBean> {

    private ProductBean mProductBean;
    private int mSkuTextViewHorizontalPadding = 14; // sku属性TextView的横向padding值(sp)
    private int mSkuTextViewVerticalPadding = 4; // sku属性TextView的纵向padding值(sp)
    private int mFlexboxLayoutSpacing = 5; // FlexboxLayout子View的横向纵向间隔(dp)
    private int mSkuTextViewTextSize = 16; // sku属性TextView字体大小(sp)
    public List<Integer> mSelectPositionList; // 用于记录每一行选择的属性值的索引值  -1: 代表未选择
    private OnChangeSkuProductDataListener onChangeSkuProductDataListener;
    // 获取没有库存的sku组合数据
    List<String> skuProductFailureList;
    StringBuilder startSpliceSkuGroupBuilder = new StringBuilder();
    StringBuilder endSpliceSkuGroupBuilder = new StringBuilder();
    StringBuilder skuGroupBuilder = new StringBuilder();

    public interface OnChangeSkuProductDataListener {
        void updateSkuProductData(ProductBean.SkuProductListBean skuProductListBean);
    }

    public  void setOnChangeSkuProductDataListener(OnChangeSkuProductDataListener listener) {
        onChangeSkuProductDataListener = listener;
    }

    public SkuAdapter(Context context,
                      ProductBean productBean,
                      int itemLayoutId) {
        super(context, productBean.getSkuTypeList(), itemLayoutId);
        mProductBean = productBean;
        skuProductFailureList = mProductBean.getSkuProductFailureList();
        mSelectPositionList = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mSelectPositionList.add(-1);
        }

    }

    @Override
    public void convert(BaseViewHolder helper, ProductBean.SkuTypeListBean item) {

        helper.setText(R.id.txt_sku_title, item.getSkuTitle());
        FlexboxLayout mFlexboxLayout = helper.getView(R.id.flb_sku_flexbox);
        mFlexboxLayout.removeAllViews();
        addSkuAttributeViewToFlexboxLayout(item, mFlexboxLayout, helper.getPosition());
    }


    /**
     * 根据数据动态添加sku的属性TextView
     *
     * @param item           sku数据
     * @param mFlexboxLayout 流式布局管理器
     * @param position
     * @return TextView[]数组
     */

    private void addSkuAttributeViewToFlexboxLayout(ProductBean.SkuTypeListBean item,
                                                    FlexboxLayout mFlexboxLayout,
                                                    int position) {

        TextView[] textViews = new TextView[item.getSkuAttributeList().size()];
        for (int i = 0; i < item.getSkuAttributeList().size(); i++) {
            String skuAttributeCode = item.getSkuAttributeList().get(i).getSkuAttributeCode();
            String skuAttribute = item.getSkuAttributeList().get(i).getSkuAttribute();
            TextView textView = new TextView(mContext);
            textView.setText(skuAttribute);
            textView.setPadding(DPUtils.dip2px(mContext, mSkuTextViewHorizontalPadding),
                    DPUtils.dip2px(mContext, mSkuTextViewVerticalPadding),
                    DPUtils.dip2px(mContext, mSkuTextViewHorizontalPadding),
                    DPUtils.dip2px(mContext, mSkuTextViewVerticalPadding));

            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, mSkuTextViewTextSize);
            FlexboxLayout.LayoutParams layoutParams =
                    new FlexboxLayout.LayoutParams(FlexboxLayout.LayoutParams.WRAP_CONTENT,
                            FlexboxLayout.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);
            textView.setClickable(true);// 设置textView可以被点击

            // 重置状态全部设为正常状态
            textView.setTextColor(mContext.getResources().getColor(R.color.sku_text_normal_color));
            textView.setBackgroundResource(R.drawable.select_product_sku_type_normal);

            // 设置选中的状态
            if (mSelectPositionList.get(position) == i) {
                textView.setTextColor(mContext.getResources().getColor(R.color.sku_text_select_color));
                textView.setBackgroundResource(R.drawable.select_product_sku_type_enable);
            }

            // 设置sku 不可点击的状态
            if (item.getSkuAttributeFailureList() != null
                    && item.getSkuAttributeFailureList().size() > 0) {
                for (String skuCode : item.getSkuAttributeFailureList()) {
                    if (skuCode.equals(skuAttributeCode)) {
                        textView.setEnabled(false);
                        textView.setTextColor(mContext.getResources().getColor(R.color.sku_text_no_enable_color));
                        textView.setBackgroundResource(R.drawable.select_product_sku_type_no_enable);
                    }
                }
            }
            textViews[i] = textView;
            mFlexboxLayout.addView(textView, layoutParams);
        }

        // 设置点击事件
        for (int j = 0; j < textViews.length; j++) {
            textViews[j].setTag(textViews);
            textViews[j].setOnClickListener(new SkuTypeClickListener(position));
        }
    }

    /**
     * sku属性按钮点击监听事件
     */
    private class SkuTypeClickListener implements View.OnClickListener {
        int mPosition;

        public SkuTypeClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            // 获取该类型的所有属性按钮
            TextView[] textViews = (TextView[]) v.getTag();
            TextView textView = (TextView) v;
            for (int i = 0; i < textViews.length; i++) {

                // 更新点击按钮的状态
                if (textViews[i].equals(textView)) {
                    // 已选择 再次点击取消选中
                    if (mSelectPositionList.get(mPosition) == i) {
                        mSelectPositionList.set(mPosition, -1);
                        textViews[i].setBackgroundResource(R.drawable.select_product_sku_type_normal);
                        textViews[i].setTextColor(mContext.getResources().getColor(R.color.sku_text_normal_color));
                    } else {
                        // 让点击的标签背景变成橙色，字体颜色变为白色
                        mSelectPositionList.set(mPosition, i);
                        textViews[i].setBackgroundResource(R.drawable.select_product_sku_type_enable);
                        textViews[i].setTextColor(mContext.getResources().getColor(R.color.sku_text_select_color));
                    }

                } else {
                    // 其他标签背景变成白色，字体颜色为灰色
                    textViews[i].setBackgroundResource(R.drawable.select_product_sku_type_normal);
                    textViews[i].setTextColor(mContext.getResources().getColor(R.color.sku_text_normal_color));
                }
            }
            handleSkuAttributeSelectState();
        }


    }

    private void handleSkuAttributeSelectState() {
        // 判断用户点击选择了几个sku按钮
        int unSelectSkuCount = 0;
        int unSelectSkuPosition = -1;
        int index = -1; // 遍历集合的position 值
        // 初始化
        for (int selectSkuPosition : mSelectPositionList) {
            index++;
            if (selectSkuPosition == -1) {
                unSelectSkuCount++;
                unSelectSkuPosition = index;
            }
            getItem(index).getSkuAttributeFailureList().clear();// 清空item sku不可点击的数据
        }


        // 如果用户至少大于两个sku属性还未选中，则清除所有item Sku按钮属性不可点击的状态
        if (unSelectSkuCount >= 2) {
            notifyDataSetChanged();
            return;
        }

        // 当unSelectSkuCount == 1时 说明用户只有一个sku属性未选择，则遍历未选择的属性，组合成参数
        // 1、如果有库存为0的，直接置灰，不可点击
        if (unSelectSkuCount == 1) {
            handleUnSelectItemSkuAttributeFailureListData(unSelectSkuPosition);
            notifyDataSetChanged();
            return;
        }

        // 当unSelectSkuCount == 0时 说明用户sku属性均已选择，
        // 1、如果有库存为0的，直接置灰，不可点击
        if (unSelectSkuCount == 0) {

            skuGroupBuilder.delete(0, skuGroupBuilder.length());
            for (int i = 0; i < mSelectPositionList.size(); i++) {
                int selectItemSkuAttributePosition = mSelectPositionList.get(i);
                if (skuGroupBuilder.length() > 0) {
                    skuGroupBuilder.append(",");
                }
                skuGroupBuilder.append(getItem(i).getSkuAttributeList().get(selectItemSkuAttributePosition).getSkuAttributeCode());

                handleUnSelectItemSkuAttributeFailureListData(i);
            }

            // SKU商品信息数据更新接口回调
            if (onChangeSkuProductDataListener != null) {
                onChangeSkuProductDataListener.updateSkuProductData(querySelectSkuGroupProductData());
            }

            notifyDataSetChanged();
        }
    }

    private ProductBean.SkuProductListBean querySelectSkuGroupProductData() {
        // 根据用户选中的sku组合，遍历查询到相应的skuid 及商品具体信息
        int tempIndex = -1;
        ProductBean.SkuProductListBean skuProductListBean = null;
        for (ProductBean.SkuProductListBean tempSkuProductListBean : mProductBean.getSkuProductList()) {
            tempIndex++;
            if (skuGroupBuilder.toString().equals(tempSkuProductListBean.getSkuGroup())) {
                skuProductListBean = tempSkuProductListBean;
                break;
            }
        }
        return skuProductListBean;
    }

    private void handleUnSelectItemSkuAttributeFailureListData(int querySkuAttributeFailureItemPosition) {
        startSpliceSkuGroupBuilder.delete(0, startSpliceSkuGroupBuilder.length());
        endSpliceSkuGroupBuilder.delete(0, endSpliceSkuGroupBuilder.length());
        for (int j = 0; j < querySkuAttributeFailureItemPosition; j++) {
            int selectItemSkuAttributePosition = mSelectPositionList.get(j);
            String skuAttributeCode =
                    getItem(j).getSkuAttributeList().get(selectItemSkuAttributePosition).getSkuAttributeCode();
            startSpliceSkuGroupBuilder.append(skuAttributeCode);
            startSpliceSkuGroupBuilder.append(",");
        }

        for (int z = querySkuAttributeFailureItemPosition + 1; z < mSelectPositionList.size(); z++) {
            int selectItemSkuAttributePosition = mSelectPositionList.get(z);
            String skuAttributeCode =
                    getItem(z).getSkuAttributeList().get(selectItemSkuAttributePosition).getSkuAttributeCode();
            endSpliceSkuGroupBuilder.append(",");
            endSpliceSkuGroupBuilder.append(skuAttributeCode);
        }

        // 获取存放当前item的sku属性列表
        List<ProductBean.SkuTypeListBean.SkuAttributeListBean> skuAttributeList
                = getItem(querySkuAttributeFailureItemPosition).getSkuAttributeList();
        // 获取到存放当前item不能购买的属性列表
        List<String> skuAttributeFailureList
                = getItem(querySkuAttributeFailureItemPosition).getSkuAttributeFailureList();

        //遍历组合sku 参数
        for (ProductBean.SkuTypeListBean.SkuAttributeListBean bean : skuAttributeList) {

            String skuGroupCode = startSpliceSkuGroupBuilder.toString()
                    + bean.getSkuAttributeCode()
                    + endSpliceSkuGroupBuilder.toString();
            for (String failureCode : skuProductFailureList) {
                if (skuGroupCode.equals(failureCode)) {
                    skuAttributeFailureList.add(bean.getSkuAttributeCode());
                }
            }
        }
    }
}
