package com.linguiqing.mychanage.ui.rxjava;

/**
 * ***************************************
 * statement: 男友被观察者
 * auther: lingguiqin
 * date created : 2017/7/9 0009
 * ***************************************
 */

public class BFSubject extends BaseSubject {

    public void change(String state) {
        notifyObservers(state);
    }
}
