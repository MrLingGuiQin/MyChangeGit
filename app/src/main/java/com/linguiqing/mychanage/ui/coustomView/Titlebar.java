package com.linguiqing.mychanage.ui.coustomView;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.linguiqing.mychanage.R;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/2/26 0026
 * ***************************************
 */

public class Titlebar extends LinearLayout {


    public  ImageView mImgGoBack;
    private TextView mTxtTile;
    private TextView mTxtOK;
    public boolean handleGoBack = true;

    public Titlebar(Context context, RelativeLayout root) {
        super(context);
        initView(context, root);
    }

    public Titlebar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context, null);
    }

    public Titlebar(Context context) {
        super(context);
        initView(context, null);
    }

    public void initView(final Context context, RelativeLayout root) {
        View mTitleView = View.inflate(context, R.layout.title_bar_layout, root);
        mImgGoBack = (ImageView) mTitleView.findViewById(R.id.img_title_bar_back);
        mTxtTile = (TextView) mTitleView.findViewById(R.id.txt_title_bar_title);
        mTxtOK = (TextView) mTitleView.findViewById(R.id.txt_title_bar_ok);
    }

    public void initTitlebar(boolean isShowGoBack, String atyTitleText, String atyTitleOKText, View.OnClickListener atyOkListener) {

        if (!isShowGoBack) {
            mImgGoBack.setVisibility(INVISIBLE);
        }
        mTxtTile.setText(atyTitleText);
        mTxtOK.setText(atyTitleOKText);
        mTxtOK.setOnClickListener(atyOkListener);
        mImgGoBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (handleGoBack) {
                    ((Activity) getContext()).finish();
                }
            }
        });
    }
}
