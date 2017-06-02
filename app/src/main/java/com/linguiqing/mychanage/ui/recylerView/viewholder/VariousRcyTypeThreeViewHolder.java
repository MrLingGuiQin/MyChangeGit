package com.linguiqing.mychanage.ui.recylerView.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linguiqing.mychanage.R;
import com.linguiqing.mychanage.ui.recylerView.data.VariousRecylerViewModleThree;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/28 0028
 * ***************************************
 */

public class VariousRcyTypeThreeViewHolder extends VariousRcyBaseViewHolder<VariousRecylerViewModleThree> {

    public ImageView mImgAvatar;
    public TextView mTxtName;
    public TextView mTxtContent;
    public ImageView mImgContent;

    public VariousRcyTypeThreeViewHolder(View itemView) {
        super(itemView);
        mImgAvatar = (ImageView) itemView.findViewById(R.id.img_type_three_avatar);
        mTxtName = (TextView) itemView.findViewById(R.id.txt_type_three_name);
        mTxtContent = (TextView) itemView.findViewById(R.id.txt_type_three_content);
        mImgContent = (ImageView) itemView.findViewById(R.id.img_type_three_content);
    }

    @Override
    public void bindHolder(final VariousRecylerViewModleThree data) {
        mImgAvatar.setImageResource(data.avatarColor);
        mTxtName.setText(data.name);
        mTxtContent.setText(data.content);
        mImgContent.setImageResource(data.contextColor);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "name = " + data.name, Toast.LENGTH_SHORT).show();
                Toast.makeText(view.getContext(), "layoutposition = " + getLayoutPosition() + "adapterPosition = " +getAdapterPosition(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
