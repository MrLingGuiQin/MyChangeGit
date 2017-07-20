package com.linguiqing.mychanage.ui.rxjava;

import com.linguiqing.mychanage.util.LogUtil;

/**
 * ***************************************
 * statement: 定义观察者抽象接口
 * auther: lingguiqin
 * date created : 2017/7/9 0009
 * ***************************************
 */

public abstract class BaseObserver {
    String name;

    void update(String state) {
        LogUtil.d(name + " 接收到男友最新状态 ：" + state);
    }
}
