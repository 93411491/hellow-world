package com.example.myrxjava.core;

/**
 * author : wangzhirui
 * date : 2021/10/8
 * description : 被观察者的核心抽象类，使用框架的入口
 */
public abstract class Observable<T> implements ObservableSource<T> {
    @Override
    public void subscibe(Observer<T> observer) {

        //和谁建立订阅
        //怎么建立订阅
        //为了保证拓展性，这里定义抽象方法，交给开发者自己来实现
        subscribeActually(observer);
    }

    abstract void subscribeActually(Observer observer);
}
