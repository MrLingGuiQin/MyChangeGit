//package com.linguiqing.mychanage.ui.usedDataBinging;
//
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.linguiqing.mychanage.interfaces.OnItemClickListener;
//
//import java.util.List;
//
//
///**
// * ***************************************
// * statement:
// * auther: lingguiqin
// * date created : 2017/6/5 0005
// * ***************************************
// */
//
//public class DataBindingAdapter<T> extends RecyclerView.Adapter<DataBindingAdapter.MyViewHolder> {
//    private List<T> mData;
//    private int mLayoutId;
//    private int mBRId;
//    private OnItemClickListener mOnItemClickListener;
//
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        mOnItemClickListener = onItemClickListener;
//    }
//
//    public DataBindingAdapter(List<T> data, int layoutId, int BRId) {
//        mData = data;
//        mLayoutId = layoutId;
//        mBRId = BRId;
//    }
//
//    @Override
//    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        ViewDataBinding binding = DataBindingUtil.inflate(inflater, mLayoutId, parent, false);
//        MyViewHolder viewHolder = new MyViewHolder(binding.getRoot());
//        viewHolder.setViewDataBinding(binding);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(DataBindingAdapter.MyViewHolder holder, int position) {
//        holder.getViewDataBinding().setVariable(mBRId, mData.get(position));
//        holder.getViewDataBinding().executePendingBindings();
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnItemClickListener != null) {
//                    mOnItemClickListener.onClick(holder.itemView, holder.getLayoutPosition());
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData != null ? mData.size() : 0;
//    }
//
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        ViewDataBinding mViewDataBinding;
//
//        public ViewDataBinding getViewDataBinding() {
//            return mViewDataBinding;
//        }
//
//        public void setViewDataBinding(ViewDataBinding viewDataBinding) {
//            mViewDataBinding = viewDataBinding;
//        }
//
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
//}
