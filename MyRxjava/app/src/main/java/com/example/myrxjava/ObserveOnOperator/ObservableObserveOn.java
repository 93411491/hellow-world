package com.example.myrxjava.ObserveOnOperator;

import com.example.myrxjava.Scheduler.Scheduler;
import com.example.myrxjava.core.ObservableSource;
import com.example.myrxjava.core.Observer;
import com.example.myrxjava.mapOperator.AbstractObservableWithUpStream;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * author : wangzhirui
 * date : 2022/2/11
 * description :
 *      核心在于：
 *          1. 使用队列缓存事件，等到线程切换完成后，再从队列中取出事件并执行
 *          2. 内部新创建的观察者实现了runnable接口，在其run方法中实现取出事件操作
 */
public class ObservableObserveOn<T> extends AbstractObservableWithUpStream<T,T> {
    //线程调度器
    final Scheduler scheduler;

    public ObservableObserveOn(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActually(Observer<T> observer) {
        Scheduler.Worker worker = scheduler.createWorker();
        source.subscribe(new ObserveOnObserver<>(observer, worker));
    }

    static final class ObserveOnObserver<T> implements Observer<T>, Runnable {

        final Observer<T> downStreamObserver;
        final Scheduler.Worker worker;
        //获取到事件后先不执行，将时间放入队列中，等到切换完线程后在执行相应的操作
        final Deque<T> queue;

        //用volatile处理线程安全问题
        volatile boolean done;
        volatile boolean over;
        volatile Throwable error;//用来表示事件是否存在异常


        public ObserveOnObserver(Observer<T> downStreamObserver, Scheduler.Worker worker) {
            this.downStreamObserver = downStreamObserver;
            this.worker = worker;
            queue = new ArrayDeque<>();
        }

        @Override
        public void onNext(T t) {
            queue.offer(t);//把事件加入队列，offer不抛异常，超出容量限制后只会返回false
            //将事件存入队列后，再执行切换线程的操作
            schedule();
        }

        private void schedule() {
            //该内部类实现了runnable接口
            worker.scheduler(this);
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onSubscribe() {
            downStreamObserver.onSubscribe();
        }



        @Override
        public void run() {
            drainNormal();
        }

        /**
         * 从队列中取出事件并处理
         */
        private void drainNormal() {
            final Deque<T> q = queue;
            final Observer<T> o = downStreamObserver;

            while (true) {
                boolean d = done;

                T t = q.poll();//取出数据，该方法没有数据的时候不会抛异常，返回null

                boolean empty = t == null;
                //判断是否需要终止循环
                if (checkTerminated(d, empty, o)) {
                    break;
                }

                if (empty) {
                    break;
                }

                downStreamObserver.onNext(t);
            }
        }

        private boolean checkTerminated(boolean d, boolean empty, Observer<T> o) {
            if (over) {
                queue.clear();
                return true;
            }
            if (d) {
                Throwable e = error;
                if (e != null) {
                    over = true;
                    o.onError(e);
                    return true;
                } else if (empty) {
                    over = true;
                    o.onComplete();
                    return true;
                }
            }
            return false;
        }
    }
}
