package com.linguiqing.mychanage.ui.recylerView.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewBean;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleOne;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleThree;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleTwo;
import com.linguiqing.mychanage.ui.recylerView.adapter.VariousRcyAdapter;
import com.linguiqing.mychanage.ui.coustomView.Titlebar;
import com.linguiqing.mychanage.util.GsonUtil;
import com.linguiqing.mychanage.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * ***************************************
 * statement: 多样式 不同条目的adapter
 * auther: lingguiqin
 * date created : 2017/2/28 0028
 * ***************************************
 */

public class VariousRecylerViewActivity extends BaseActivity {
    @BindView(R.id.rcy_various_recyclerview)
    RecyclerView mVariousRecyclerview;
    int colors[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};
    private VariousRcyAdapter mAdapter;
    private List<VariousRecylerViewModleOne> mList1;
    private List<VariousRecylerViewModleTwo> mList2;
    private List<VariousRecylerViewModleThree> mList3;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_various_recylerview_layout;
    }

    @Override
    public void initData() {
        mList1 = new ArrayList<>();
        mList2 = new ArrayList<>();
        mList3 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VariousRecylerViewModleOne data = new VariousRecylerViewModleOne();
            data.avatarColor = colors[0];
            data.name = "name :  " + i;
            mList1.add(data);
        }

        for (int i = 0; i < 10; i++) {
            VariousRecylerViewModleTwo data = new VariousRecylerViewModleTwo();
            data.avatarColor = colors[1];
            data.name = "name :  " + i;
            data.content = "content :  " + i;
            mList2.add(data);
        }

        for (int i = 0; i < 10; i++) {
            VariousRecylerViewModleThree data = new VariousRecylerViewModleThree();
            data.avatarColor = colors[2];
            data.name = "name :  " + i;
            data.content = "content :  " + i;
            data.contextColor = colors[1];
            mList3.add(data);
        }

    }

    @Override
    public void initView(Bundle savedInstanceState) {
//        mVariousRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
        //当list和 Grid 混排的时候需要用到该方法
        // 设置布局管理器每个item 所占一行的多少
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // eg:现在是2列，如果 return 1则代表所占一行的1/2  ，return 2 代表占一行
                int type = mVariousRecyclerview.getAdapter().getItemViewType(position);
                if (type == VariousRecylerViewBean.TYPE_THREE) {
                    return gridLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });
        mVariousRecyclerview.setLayoutManager(gridLayoutManager);
//        mVariousRecyclerview.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        // 设置间隔
        mVariousRecyclerview.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                // 列数
                int spanSize = layoutParams.getSpanSize();
                // 每一列的 item的索引位置 ，第一个 为 0，以此类推
                int spanIndex = layoutParams.getSpanIndex();
                outRect.top = 20; //设置顶部的偏移量
                if (spanSize != gridLayoutManager.getSpanCount()) {
                    if (spanIndex == 1) {
                        outRect.left = 10;
                    } else {
                        outRect.right = 10;
                    }
                }
            }
        });

        mAdapter = new VariousRcyAdapter(mContext);
        mAdapter.setListData(mList1, mList2, mList3);
        mVariousRecyclerview.setAdapter(mAdapter);
    }

    @Override
    public View addCommonTitlebar(RelativeLayout root) {
        Titlebar titlebar = new Titlebar(mContext, root);
        titlebar.initTitlebar(true, "多样式RecylerView", "", null);
        return titlebar;
    }
}
