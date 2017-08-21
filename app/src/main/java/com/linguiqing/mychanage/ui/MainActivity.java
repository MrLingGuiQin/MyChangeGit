package com.linguiqing.mychanage.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.interfaces.OnItemClickListener;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.ui.dagger.StudyDaggerActivity;
import com.linguiqing.mychanage.ui.drawerLayout.DrawerLayoutActivity;
import com.linguiqing.mychanage.ui.eventbus.StudyEventBusActivity;
import com.linguiqing.mychanage.ui.handler.StudyHandlerActivity;
import com.linguiqing.mychanage.ui.permission.StudyPermissionActivity;
import com.linguiqing.mychanage.ui.photoView.StudyPhotoViewActivity;
import com.linguiqing.mychanage.ui.productSku.ProductSkuActivity;
import com.linguiqing.mychanage.ui.recylerView.activity.VariousRecylerViewActivity;
import com.linguiqing.mychanage.ui.recylerView.activity.VariousRecylerViewUsedMultiTypeActivity;
import com.linguiqing.mychanage.ui.retrofit.StudyRetrofitActivity;
import com.linguiqing.mychanage.ui.richText.StudyRichTextActivity;
import com.linguiqing.mychanage.ui.rxjava.StudyRxJavaActivity;
import com.linguiqing.mychanage.ui.selectPhoto.activity.SelectPhotoActivity;
import com.linguiqing.mychanage.ui.smartRefresh.SimpleProductListActivity;
import com.linguiqing.mychanage.ui.usedAnimation.StudyAnimationActivity;
import com.linguiqing.mychanage.ui.usedFragment.UsedFragmentActivity;
import com.linguiqing.mychanage.ui.usedOkHttp.UsedOkHttpActivity;
import com.linguiqing.mychanage.ui.webView.StudyWebFragmentActivity;
import com.linguiqing.mychanage.util.BaseCustomDialog;
import com.linguiqing.mychanage.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rcy_recyclerview)
    RecyclerView mRecyclerview;
    private List<String> mData;
    private RecylerViewMainAdapter mAdapter;

    @Override
    public void initView(Bundle savedInstanceState) {
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mAdapter = new RecylerViewMainAdapter(mData, mContext);
        mRecyclerview.setAdapter(mAdapter);
        setItemClickListener();
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        mData.add("RecylerView实现多种样式的布局");
        mData.add("RecylerView实现多种样式的布局2(使用框架MultiType)");
        mData.add("Handle的学习");
        mData.add("仿微信选择图片");
        mData.add("自定义dialog");
        mData.add("自定义dialog2");
        mData.add("fragment的使用");
        mData.add("内存泄露优化");
        mData.add("商品sku业务实现");
        mData.add("okhttp的使用");
        mData.add("DrawerLayout测滑的使用");
        mData.add("EventBus的使用");
        mData.add("Animation动画的使用");
        mData.add("DataBining的使用");
        mData.add("android 6.0 Permission处理");
        mData.add("TextView  富文本的使用");
        mData.add("Dagger2的使用");
        mData.add("PhotoView的使用");
        mData.add("WebView的使用");
        mData.add("RxJava的使用");
        mData.add("Retrofit的使用");
        mData.add("SmartRefreshLayout的使用");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "首页", "", null);
        return titlebar;
    }

    private void setItemClickListener() {
        mAdapter.setmOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 0: // RecylerView实现多样式布局
                        goToCustomActivity(VariousRecylerViewActivity.class);
                        break;
                    case 1: // MultiType 框架的使用
                        goToCustomActivity(VariousRecylerViewUsedMultiTypeActivity.class);
                        break;
                    case 2: // handle 的使用
                        goToCustomActivity(StudyHandlerActivity.class);
                        break;
                    case 3:// 仿微信选择图片
                        goToCustomActivity(SelectPhotoActivity.class);
                        break;

                    case 4: // 自定义dialog1
                        initCoustomDialog();
                        break;

                    case 5: // 自定义dialog2
                        initCoustomDialog2();
                        break;

                    case 6: // fragment的使用
                        goToCustomActivity(UsedFragmentActivity.class);
                        break;

                    case 7: // 内存泄露
                        break;

                    case 8: // sku
                        goToCustomActivity(ProductSkuActivity.class);
                        break;

                    case 9: //okhttp的使用
                        goToCustomActivity(UsedOkHttpActivity.class);
                        break;
                    case 10: //DrawerLayout测滑菜单的使用
                        goToCustomActivity(DrawerLayoutActivity.class);
                        break;
                    case 11: //EventBus的使用
                        goToCustomActivity(StudyEventBusActivity.class);
                        break;
                    case 12: //动画的使用
                        goToCustomActivity(StudyAnimationActivity.class);
                        break;
                    case 13: //DataBining的使用
//                        goToCustomActivity(StudyDataBindingActivity.class);
                        break;
                    case 14: // android 6.0 权限处理
                        goToCustomActivity(StudyPermissionActivity.class);
                        break;
                    case 15: // textView 富文本的使用
                        goToCustomActivity(StudyRichTextActivity.class);
                        break;
                    case 16: // Dagger2的使用
                        goToCustomActivity(StudyDaggerActivity.class);
                        break;
                    case 17: // photoView的使用
                        goToCustomActivity(StudyPhotoViewActivity.class);
                        break;
                    case 18: //webView的使用
//                        goToCustomActivity(StudyWebActivity.class);
                        goToCustomActivity(StudyWebFragmentActivity.class);
                        break;
                    case 19: //RxJava的使用
                        goToCustomActivity(StudyRxJavaActivity.class);
                        break;
                    case 20: //Retrofit的使用
                        goToCustomActivity(StudyRetrofitActivity.class);
                        break;

                    case 21:  //SmartRefreshLayout的使用
                        goToCustomActivity(SimpleProductListActivity.class);
//                        goToCustomActivity(CalendarRecylerViewActivity.class);
                        break;
                }
            }
        });
    }

    private void initCoustomDialog2() {
        BaseCustomDialog dialog2 = new BaseCustomDialog((Activity) mContext,
                R.style.Dialog, 1, -1, Gravity.BOTTOM, false) {
            @Override
            public View initView() {
                View view = View.inflate(mContext, R.layout.dialog_layout, null);
                TextView txtMsg = (TextView) view.findViewById(R.id.txt_msg);
                txtMsg.setText("66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666 ");
                TextView txtOk = (TextView) view.findViewById(R.id.txt_sure);
                txtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("6666660", Toast.LENGTH_SHORT);
                        dismiss();
                    }
                });
                return view;
            }
        };
        dialog2.show();
    }

    private void initCoustomDialog() {
        final BaseCustomDialog dialog = new BaseCustomDialog((Activity) mContext) {
            @Override
            public View initView() {
                View view = View.inflate(mContext, R.layout.dialog_layout, null);
                TextView txtMsg = (TextView) view.findViewById(R.id.txt_msg);
                txtMsg.setText("66666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666 ");
                TextView txtOk = (TextView) view.findViewById(R.id.txt_sure);
                txtOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showToast("6666660", Toast.LENGTH_SHORT);
                        dismiss();
                    }
                });
                return view;
            }
        };
        dialog.show();
    }

}
