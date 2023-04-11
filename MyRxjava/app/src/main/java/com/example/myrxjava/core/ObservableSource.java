package com.example.myrxjava.core;

import com.example.myrxjava.core.Observer;

/**
 * author : wangzhirui
 * date : 2021/10/8
 * description : 被观察者的顶层接口
 */
public interface ObservableSource<T> {

    public void subscribe(Observer<T> observer);
}
