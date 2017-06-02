package com.linguiqing.mychanage.ui.usedFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linguiqing.mychanage.R;

/**
 * ***************************************
 * statement:
 * auther: lingguiqin
 * date created : 2017/3/19 0019
 * ***************************************
 */

public class ContentFragment extends Fragment {
    private View mContentView;
    private TextView mTxtContent;
    public String mContent = "My Change";


    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("ContentFragment", "onCreateView方法执行啦！");
        mContentView = inflater.inflate(R.layout.used_fragment_fragment_view_layout, container, false);
        mTxtContent = (TextView) mContentView.findViewById(R.id.txt_used_fragment_content);
        mTxtContent.setText(mContent);
        return mContentView;
    }

}
