package com.linguiqing.mychanage.ui.dagger;

import android.content.Context;
import android.widget.Toast;

import com.linguiqing.mychanage.util.LogUtil;

import javax.inject.Inject;

/**
 * ***************************************
 * statement: 缓存获取数据
 * auther: lingguiqin
 * date created : 2017/8/18 0018
 * ***************************************
 */

public class UserStore {
    private Context mContext;

    public UserStore(Context context) {
        LogUtil.e("UserStore 初始化----");
        mContext = context;
    }

    //    @Inject
    public UserStore() {
    }


    public void register() {
        Toast.makeText(mContext, "从缓存中获取数据啦----", Toast.LENGTH_SHORT).show();
        LogUtil.e("从缓存中获取数据啦----");
    }
}
