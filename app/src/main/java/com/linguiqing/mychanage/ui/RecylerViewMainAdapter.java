package com.linguiqing.mychanage.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.interfaces.OnItemClickListener;
import com.linguiqing.mychanage.interfaces.OnItemLongClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/28 0028
 * ***************************************
 */

public class RecylerViewMainAdapter extends RecyclerView.Adapter<RecylerViewMainAdapter.MainViewHolder> {

    private List<String> mData;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    public RecylerViewMainAdapter(List<String> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @Override
    public RecylerViewMainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recylerview_main_layout, parent, false);
        return new MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, int position) {
        holder.mTxtItemText.setText(mData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v, pos);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                if (mOnItemLongClickListener != null) {
                    mOnItemLongClickListener.onLongClick(v, pos);
                }
                return false;
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_main_item_text)
        TextView mTxtItemText;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
