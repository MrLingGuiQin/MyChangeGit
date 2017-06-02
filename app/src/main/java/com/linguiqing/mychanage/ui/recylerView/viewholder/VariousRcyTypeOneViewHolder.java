package com.linguiqing.mychanage.ui.recylerView.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleOne;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/28 0028
 * ***************************************
 */

public class VariousRcyTypeOneViewHolder extends VariousRcyBaseViewHolder<VariousRecylerViewModleOne> {
    public ImageView mImgAvatar;
    public TextView mTxtName;

    public VariousRcyTypeOneViewHolder(View itemView) {
        super(itemView);
        mImgAvatar = (ImageView) itemView.findViewById(R.id.img_type_one_avatar);
        mTxtName = (TextView) itemView.findViewById(R.id.txt_type_one_name);
    }

    @Override
    public void bindHolder(final VariousRecylerViewModleOne data) {
        mImgAvatar.setImageResource(data.avatarColor);
        mTxtName.setText(data.name);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "layoutposition = " + getLayoutPosition() + "adapterPosition = " + getAdapterPosition(), Toast.LENGTH_SHORT).show();

//                Toast.makeText(view.getContext(), "name = " + data.name, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
