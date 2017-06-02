package com.linguiqing.mychanage.ui.recylerView.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/28 0028
 * ***************************************
 */

public abstract class VariousRcyBaseViewHolder<T> extends RecyclerView.ViewHolder {
    public VariousRcyBaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindHolder(T data);
}
