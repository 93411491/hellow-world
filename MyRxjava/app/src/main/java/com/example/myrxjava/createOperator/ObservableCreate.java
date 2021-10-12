package com.example.myrxjava.createOperator;

import com.example.myrxjava.core.Emitter;
import com.example.myrxjava.core.Observable;
import com.example.myrxjava.core.ObservableOnSubscribe;
import com.example.myrxjava.core.Observer;

/**
 * author : wangzhirui
 * date : 2021/10/12
 * description : 该类真正建立了观察者与被观察者与事件发射器之间的关系，实现了create操作
 */
public class ObservableCreate<T> extends Observable<T> {

    private ObservableOnSubscribe<T> source;
    public ObservableCreate(ObservableOnSubscribe<T> observableOnSubscribe) {
        this.source = observableOnSubscribe;
    }

    /***
     *
     * @param observer
     */
    @Override
    protected void subscribeActually(Observer<T> observer) {
        //First,when building subscribe relationship , need use observer.onSubscribe()
        observer.onSubscribe();
        //Second, 建立被观察者与事件发射器的联系
        CreateEmitter<T> createEmitter = new CreateEmitter<>(observer);
        source.subscribe(createEmitter);
    }

    static class CreateEmitter<T> implements Emitter<T> {
        final Observer<T> observer;
        boolean done; //标记事件是否已经完成

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if (done) {
                return;
            }
            observer.onNext(t);
        }

        @Override
        public void onComplete() {
            if (done) {
                return;
            }
            observer.onComplete();
            done = true;
        }

        @Override
        public void onError(Throwable throwable) {
            if (done) {
                return;
            }
            observer.onError(throwable);
            done = true;
        }
    }
}
