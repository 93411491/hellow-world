package com.example.myrxjava.core;

/**
 * author : wangzhirui
 * date : 2021/10/8
 * description : 观察者接口
 */
public interface Observer<T> {

    public void onNext(T t);

    public void onComplete();

    public void onError(Throwable throwable);

    public void onSubscribe();
}
