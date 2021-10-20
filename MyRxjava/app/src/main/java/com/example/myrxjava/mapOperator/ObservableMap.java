package com.example.myrxjava.mapOperator;

import com.example.myrxjava.R;
import com.example.myrxjava.core.ObservableSource;
import com.example.myrxjava.core.Observer;

/**
 * author : wangzhirui
 * date : 2021/10/14
 * description :
 */
public class ObservableMap<T,U> extends AbstractObservableWithUpStream<T,U> {

    //使用Function接口来实现类型的转换
    private Function<T,U> function;

    public ObservableMap(ObservableSource<T> source, Function<T, U> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActually(Observer<U> observer) {
        /*
        * 正常来讲，这里是用source.subscibe(observer)即可建立订阅关系，但是
        * 并不是用传入的observer，因为如果使用传入的observer来处理事件，此时其
        * 处理实现的类型为T，但是我们真正要处理的是被map转换为R类型的事件，因此需
        * 要一个内部类来实现其类型转换能力
        * */
//        source.subscibe(observer);
        source.subscibe(new MapObserver<T, U>(observer,function));
    }

    static class MapObserver<T,U> implements Observer<T> {

        //这里的泛型要指定为转换后的类型，因为这个处理的事件类型是被function转换后的类型
        private final Observer<U> downStreamObserver;

        private final Function<T, U> function;

        public MapObserver(Observer<U> downStreamObserver, Function<T, U> function) {
            this.downStreamObserver = downStreamObserver;
            this.function = function;
        }

        @Override
        public void onNext(T t) {
            //map操作符核心，使用function来转换事件类型，然后使用观察者处理被转换后的事件
            U u  = function.apply(t);
            downStreamObserver.onNext(u);
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

        }
    }
}
