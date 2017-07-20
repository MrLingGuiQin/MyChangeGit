package com.linguiqing.mychanage.ui.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * ***************************************
 * statement: 定义被观察者抽象类
 * auther: lingguiqin
 * date created : 2017/7/9 0009
 * ***************************************
 */

public abstract class BaseSubject {
    // 定义集合装载女朋友
    public List<BaseObserver> mLists = new ArrayList<BaseObserver>();

    /**
     * 增加女朋友方法
     *
     * @param observer
     */
    public void attach(BaseObserver observer) {
        mLists.add(observer);
    }

    /**
     * 分手移除女朋友
     *
     * @param observer
     */
    public void dettch(BaseObserver observer) {
        mLists.remove(observer);
    }

    /**
     * 通知所有的女朋友我更新状态了
     *
     * @param state
     */
    public void notifyObservers(String state) {
        for (BaseObserver observer : mLists) {
            observer.update(state);
        }
    }

}
