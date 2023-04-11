package com.example.myrxjava.flatmapOperator;

import com.example.myrxjava.core.ObservableSource;
import com.example.myrxjava.core.Observer;
import com.example.myrxjava.mapOperator.AbstractObservableWithUpStream;
import com.example.myrxjava.mapOperator.Function;

/**
 * author : wangzhirui
 * date : 2021/11/29
 * description : 将原被观察者发送的事件，处理后转换成一个新的观察者，在用新的观察者发送新的时间，这样子就能实现拼接的功能
 */
public class ObservableFlatmap<T,U> extends AbstractObservableWithUpStream<T,U> {

    Function<T, ObservableSource<U>> function;

    public ObservableFlatmap(ObservableSource<T> source, Function<T, ObservableSource<U>> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActually(Observer<U> observer) {

        source.subscribe(new MergeObserver<T, U>(observer, function));
    }

    static class MergeObserver<T, U> implements Observer<T> {

        private Observer<U> downStreamObserver;
        private Function<T, ObservableSource<U>> function;

        public MergeObserver(Observer<U> downStreamObserver,Function<T, ObservableSource<U>> function) {
            this.downStreamObserver = downStreamObserver;
            this.function = function;
        }

        @Override
        public void onNext(T t) {
            ObservableSource<U> newObservable = function.apply(t);
            newObservable.subscribe(new Observer<U>() {
                @Override
                public void onNext(U u) {
                    downStreamObserver.onNext(u);
                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onSubscribe() {

                }
            });
        }

        @Override
        public void onComplete() {
            downStreamObserver.onComplete();
        }

        @Override
        public void onError(Throwable throwable) {
            downStreamObserver.onError(throwable);
        }

        @Override
        public void onSubscribe() {
            downStreamObserver.onSubscribe();
        }
    }
}
