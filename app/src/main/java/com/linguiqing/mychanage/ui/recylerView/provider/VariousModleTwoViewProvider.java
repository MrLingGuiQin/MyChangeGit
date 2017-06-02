package com.linguiqing.mychanage.ui.recylerView.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleTwo;
import com.linguiqing.mychanage.ui.recylerView.viewholder.VariousRcyTypeTwoViewHolder;

import me.drakeet.multitype.ItemViewProvider;

/**
 * ***************************************
 * statement: RecylerView多样式布局2的界面刷新显示
 * auther: lingguiqin
 * date created : 2017/3/1 0001
 * ***************************************
 */

public class VariousModleTwoViewProvider extends ItemViewProvider<VariousRecylerViewModleTwo, VariousRcyTypeTwoViewHolder> {
    @NonNull
    @Override
    protected VariousRcyTypeTwoViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.various_recylerview_type_two_layout, parent, false);
        return new VariousRcyTypeTwoViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull VariousRcyTypeTwoViewHolder holder, @NonNull VariousRecylerViewModleTwo variousRecylerViewModleTwo) {
        holder.bindHolder(variousRecylerViewModleTwo);

    }

}
