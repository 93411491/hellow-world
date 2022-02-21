package com.example.myrxjava.SubscribeOnOperator;

import com.example.myrxjava.Scheduler.Scheduler;
import com.example.myrxjava.core.ObservableSource;
import com.example.myrxjava.core.Observer;
import com.example.myrxjava.mapOperator.AbstractObservableWithUpStream;

/**
 * author : wangzhirui
 * date : 2021/12/7
 * description :
 */
public class ObservableSubscribeOn<T> extends AbstractObservableWithUpStream<T,T> {

    Scheduler scheduler;

    public ObservableSubscribeOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActually(Observer<T> observer) {
        //subscribeOn只会影响到被观察者的发送事件时所处的线程，因此onSubscribe的调用应仍处于主线程
        observer.onSubscribe();
        Scheduler.Worker worker = scheduler.createWorker();
        worker.scheduler(new SubscribeTask(new SubscribeOnObserver<>(observer)));
    }

    static class SubscribeOnObserver<T> implements Observer<T>{

        Observer<T> downStreamObserver;

        public SubscribeOnObserver(Observer<T> downStreamObserver) {
            this.downStreamObserver = downStreamObserver;
        }

        @Override
        public void onNext(T t) {
            //subscribeOn 并不影响观察者处理事件的线程，因此用原始观察者即可
            downStreamObserver.onNext(t);
        }

        @Override
        public void onComplete() {
            //subscribeOn 并不影响观察者处理事件的线程，因此用原始观察者即可
            downStreamObserver.onComplete();
        }

        @Override
        public void onError(Throwable throwable) {
            //subscribeOn 并不影响观察者处理事件的线程，因此用原始观察者即可
            downStreamObserver.onError(throwable);
        }

        @Override
        public void onSubscribe() {

        }
    }

    final class SubscribeTask implements Runnable{

        SubscribeOnObserver<T> observer;

        public SubscribeTask(SubscribeOnObserver<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            source.subscibe(observer);
        }
    }
}
