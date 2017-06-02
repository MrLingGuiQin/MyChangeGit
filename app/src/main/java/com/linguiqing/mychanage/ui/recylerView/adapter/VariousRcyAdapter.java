package com.linguiqing.mychanage.ui.recylerView.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewBean;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleOne;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleThree;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleTwo;
import com.linguiqing.mychanage.ui.recylerView.viewholder.VariousRcyTypeOneViewHolder;
import com.linguiqing.mychanage.ui.recylerView.viewholder.VariousRcyTypeThreeViewHolder;
import com.linguiqing.mychanage.ui.recylerView.viewholder.VariousRcyTypeTwoViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/28 0028
 * ***************************************
 */

public class VariousRcyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int TYPE_ONE = 1;
    public final static int TYPE_TWO = 2;
    public final static int TYPE_THREE = 3;

    private List<VariousRecylerViewModleOne> mList1;
    private List<VariousRecylerViewModleTwo> mList2;
    private List<VariousRecylerViewModleThree> mList3;
    private List<Integer> mTypes = new ArrayList<>();
    private Map<Integer, Integer> mPositions = new HashMap<>();
    private Context mContext;
    private final LayoutInflater mInflater;


    public VariousRcyAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setListData(List<VariousRecylerViewModleOne> mList1,
                            List<VariousRecylerViewModleTwo> mList2,
                            List<VariousRecylerViewModleThree> mList3) {
        this.mList1 = mList1;
        this.mList2 = mList2;
        this.mList3 = mList3;
        setType(TYPE_ONE, mList1);
        setType(TYPE_TWO, mList2);
        setType(TYPE_THREE, mList3);
    }

    private void setType(int type, List list) {
        mPositions.put(type, mTypes.size());
        for (int i = 0; i < list.size(); i++) {
            mTypes.add(type);
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case VariousRecylerViewBean.TYPE_ONE:
                View itemView = mInflater.inflate(R.layout.various_recylerview_type_one_layout, parent, false);
                viewHolder = new VariousRcyTypeOneViewHolder(itemView);
                break;
            case VariousRecylerViewBean.TYPE_TWO:
                View itemView2 = mInflater.inflate(R.layout.various_recylerview_type_two_layout, parent, false);
                viewHolder = new VariousRcyTypeTwoViewHolder(itemView2);
                break;
            case VariousRecylerViewBean.TYPE_THREE:
                View itemView3 = mInflater.inflate(R.layout.various_recylerview_type_three_layout, parent, false);
                viewHolder = new VariousRcyTypeThreeViewHolder(itemView3);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        // 获取到每个集合里真实的position
        int relPosition = position - mPositions.get(type);
        switch (type) {
            case TYPE_ONE:
                ((VariousRcyTypeOneViewHolder) holder).bindHolder(mList1.get(relPosition));
                break;
            case TYPE_TWO:
                ((VariousRcyTypeTwoViewHolder) holder).bindHolder(mList2.get(relPosition));
                break;
            case TYPE_THREE:
                ((VariousRcyTypeThreeViewHolder) holder).bindHolder(mList3.get(relPosition));
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mTypes.size();
    }

    //返回值赋值给onCreateViewHolder的参数 viewType  
    @Override
    public int getItemViewType(int position) {
        return mTypes.get(position);
    }
}
