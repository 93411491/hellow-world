package com.example.myrxjava.core;

import com.example.myrxjava.ObserveOnOperator.ObservableObserveOn;
import com.example.myrxjava.Scheduler.Scheduler;
import com.example.myrxjava.SubscribeOnOperator.ObservableSubscribeOn;
import com.example.myrxjava.createOperator.ObservableCreate;
import com.example.myrxjava.flatmapOperator.ObservableFlatmap;
import com.example.myrxjava.mapOperator.Function;
import com.example.myrxjava.mapOperator.ObservableMap;

/**
 * author : wangzhirui
 * date : 2021/10/8
 * description : 被观察者的核心抽象类，使用框架的入口
 */
public abstract class Observable<T> implements ObservableSource<T> {
    @Override
    public void subscribe(Observer<T> observer) {

        //和谁建立订阅
        //怎么建立订阅
        //为了保证拓展性，这里定义抽象方法，交给开发者自己来实现
        subscribeActually(observer);
    }

    protected abstract void subscribeActually(Observer<T> observer);

    public static<T> Observable<T> create(ObservableOnSubscribe<T> source){
        return new ObservableCreate<>(source);
    }

    //map操作符
    public <R> ObservableMap<T,R> map(Function<T,R> function){
        return new ObservableMap<>(this, function);
    }

    //flatmap操作符
    public <R> ObservableFlatmap<T,R> flatmap(Function<T,ObservableSource<R>> function){
        return new ObservableFlatmap<>(this, function);
    }

    //subscribeOn操作符
    public ObservableSubscribeOn<T> subscribeOn(Scheduler scheduler) {
        return new ObservableSubscribeOn<T>(this, scheduler);
    }

    public ObservableObserveOn<T> ObserveOn(Scheduler scheduler) {
        return new ObservableObserveOn<T>(this, scheduler);
    }
}
