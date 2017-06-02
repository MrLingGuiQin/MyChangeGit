package com.linguiqing.mychanage.ui.recylerView.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleOne;
import com.linguiqing.mychanage.ui.recylerView.viewholder.VariousRcyTypeOneViewHolder;

import me.drakeet.multitype.ItemViewProvider;

/**
 * ***************************************
 * statement: RecylerView多样式布局1的界面刷新显示
 * auther: lingguiqin
 * date created : 2017/3/1 0001
 * ***************************************
 */

public class VariousModleOneViewProvider extends ItemViewProvider<VariousRecylerViewModleOne, VariousRcyTypeOneViewHolder> {
    @NonNull
    @Override
    protected VariousRcyTypeOneViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.various_recylerview_type_one_layout, parent, false);
        return new VariousRcyTypeOneViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull VariousRcyTypeOneViewHolder holder, @NonNull VariousRecylerViewModleOne variousRecylerViewModleOne) {
        holder.bindHolder(variousRecylerViewModleOne);
    }
}
