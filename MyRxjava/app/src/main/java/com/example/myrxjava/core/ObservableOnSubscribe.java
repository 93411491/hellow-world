package com.example.myrxjava.core;

/**
 * author : wangzhirui
 * date : 2021/10/8
 * description : 这个接口的作用是让被观察者与事件发射器建立关系，因为在Rxjava的观察者模式中，被观察者与事件发射器
 * 进行了解耦，事件并不是由被观察者直接产生，而是交由事件发射器产生
 */
public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<T> emitter);
}
