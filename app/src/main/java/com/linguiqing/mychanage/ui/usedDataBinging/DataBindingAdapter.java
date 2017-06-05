package com.linguiqing.mychanage.ui.usedDataBinging;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/6/5 0005
 * ***************************************
 */

public class DataBindingAdapter extends RecyclerView.Adapter<DataBindingAdapter.MyViewHolder> {


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, -1, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(binding.getRoot());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
//    holder.getViewDataBinding().setVariable()
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding mViewDataBinding;

        public ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }

        public void setViewDataBinding(ViewDataBinding viewDataBinding) {
            mViewDataBinding = viewDataBinding;
        }


        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
