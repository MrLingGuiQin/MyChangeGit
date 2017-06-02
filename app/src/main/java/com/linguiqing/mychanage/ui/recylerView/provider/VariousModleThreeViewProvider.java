package com.linguiqing.mychanage.ui.recylerView.provider;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleThree;
import com.linguiqing.mychanage.ui.recylerView.viewholder.VariousRcyTypeThreeViewHolder;

import me.drakeet.multitype.ItemViewProvider;

/**
 * ***************************************
 * statement: RecylerView多样式布局3的界面刷新显示
 * auther: lingguiqin
 * date created : 2017/3/1 0001
 * ***************************************
 */

public class VariousModleThreeViewProvider extends ItemViewProvider<VariousRecylerViewModleThree, VariousRcyTypeThreeViewHolder> {
    @NonNull
    @Override
    protected VariousRcyTypeThreeViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = inflater.inflate(R.layout.various_recylerview_type_three_layout, parent, false);
        return new VariousRcyTypeThreeViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull VariousRcyTypeThreeViewHolder holder, @NonNull final VariousRecylerViewModleThree variousRecylerViewModleThree) {
        holder.bindHolder(variousRecylerViewModleThree);

    }

}
