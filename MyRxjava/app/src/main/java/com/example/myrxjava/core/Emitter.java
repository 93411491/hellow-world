package com.example.myrxjava.core;

/**
 * author : wangzhirui
 * date : 2021/10/8
 * description : 事件发射器接口
 */
public interface Emitter<T> {
    public void onNext(T t);

    public void onComplete();

    public void onError(Throwable throwable);
}
