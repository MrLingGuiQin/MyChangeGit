package com.linguiqing.mychanage.ui.recylerView.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.base.BaseActivity;
import com.linguiqing.mychanage.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CalendarRecylerViewActivity extends BaseActivity {

    private List<String> mList = new ArrayList();
    @BindView(R.id.rcy_calender_views)
    RecyclerView mRecylerView;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_calendar_recyler_view;
    }

    @Override
    public void initData() {
        for (int i = 0; i < 35; i++) {
            mList.add(i + 1 + "");
        }

    }

    @Override
    public void initView(Bundle savedInstanceState) {
        mRecylerView.setBackgroundColor(Color.parseColor("#000000"));
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(mContext, 7, GridLayoutManager.VERTICAL, false);
        mRecylerView.setLayoutManager(mGridLayoutManager);
        MyAdapter adapter = new MyAdapter();
        mRecylerView.setAdapter(adapter);
        mRecylerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = 2;
                outRect.right = 2;

                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                // 每一横列的 item的索引位置 ，第一个 为 0 ，0 - 6 以此类推
                int spanIndex = layoutParams.getSpanIndex();

                GridLayoutManager lm = (GridLayoutManager) parent.getLayoutManager();
                int spanSize = lm.getSpanCount(); // 列数 7

                if (spanIndex == 0) {
                    outRect.left = 6;
                }

                // 获取item的索引值 0 ~ mList.size -1
                int childLayoutPosition = parent.getChildLayoutPosition(view);
                if (childLayoutPosition < spanSize) {
                    outRect.top = 6;
                }
                LogUtil.e("spanIndex = " + spanIndex + "spanSize = " + spanSize
                        + "childLayoutPosition = " + childLayoutPosition);
            }
        });
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHold> {


        @Override
        public MyViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_calendar, null, false);
            return new MyViewHold(view);
        }

        @Override
        public void onBindViewHolder(MyViewHold holder, int position) {
            holder.mTextView.setText(mList.get(position));
        }


        @Override
        public int getItemCount() {
            return mList.size();
        }


    }

    public class MyViewHold extends RecyclerView.ViewHolder {
        TextView mTextView;

        public MyViewHold(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView666);
        }
    }


}
