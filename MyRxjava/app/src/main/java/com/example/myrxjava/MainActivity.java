package com.example.myrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myrxjava.Scheduler.Schedulers;
import com.example.myrxjava.core.Emitter;
import com.example.myrxjava.core.Observable;
import com.example.myrxjava.core.ObservableOnSubscribe;
import com.example.myrxjava.core.ObservableSource;
import com.example.myrxjava.core.Observer;
import com.example.myrxjava.mapOperator.Function;

import java.text.SimpleDateFormat;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.AsyncSubject;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "wzr->MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, Log.getStackTraceString(new Throwable()));
//        createTest();
//        mapTest();
//        flatmapTest();
//        subscribeOnTest();
    }


    private void test1(){
        AsyncSubject<Object > objectAsyncSubject = AsyncSubject.create();
        objectAsyncSubject.onNext("A");
        objectAsyncSubject.onNext("B");
        objectAsyncSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("TAG", "accept:" + 0);
            }
        });
        objectAsyncSubject.onNext("c");
        // 必须调川onComplete
        objectAsyncSubject.onComplete();
    }

    private void test2(){
        BehaviorSubject<Object > objectAsyncSubject = BehaviorSubject.create();
        objectAsyncSubject.onNext("A");
        objectAsyncSubject.onNext("B");
        objectAsyncSubject.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.i("TAG", "accept:" + 0);
            }
        });
        objectAsyncSubject.onNext("c");
        // 必须调川onComplete
        objectAsyncSubject.onComplete();
    }



    private void subscribeOnTest() {

        double time2 = 1643615465247d;
        String result2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time2);
        Log.d(TAG, "subscribeOnTest: result2");

        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(Emitter<Object> emitter) {
                Log.d(TAG, "(wzr)->subscribe: threadName = "+ Thread.currentThread().toString());
                emitter.onNext("111");
                emitter.onNext("222");
                emitter.onNext("333");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.NewThread())
                .ObserveOn(Schedulers.mainThread())
                .flatmap(new Function<Object, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(Object o) {
                Log.d(TAG, "(wzr)->apply: threadName = "+ Thread.currentThread().toString());
                return Observable.create(new ObservableOnSubscribe<Object>() {

                    @Override
                    public void subscribe(Emitter<Object> emitter) {
                        Log.d(TAG, "(wzr)->inner Observable subscribe: threadName = "+ Thread.currentThread().toString());
                        emitter.onNext("处理后的" + 444555);
                        emitter.onComplete();
                    }
                });
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onNext(Object o) {
                Log.d(TAG, "(wzr)->onNext: threadName = "+ Thread.currentThread().toString());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "(wzr)->onComplete: threadName = "+ Thread.currentThread().toString());
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "(wzr)->onError: threadName = "+ Thread.currentThread().toString());
            }

            @Override
            public void onSubscribe() {
                Log.d(TAG, "(wzr)->onSubscribe: threadName = "+ Thread.currentThread().toString());
            }
        });
    }

    private void flatmapTest() {
        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(Emitter<Object> emitter) {
                emitter.onNext("111");
                emitter.onNext("222");
                emitter.onNext("333");
                emitter.onComplete();
            }
        }).flatmap(new Function<Object, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(Object o) {
                Log.d(TAG, "(wzr)->apply: " + o);
                return Observable.create(new ObservableOnSubscribe<Object>() {

                    @Override
                    public void subscribe(Emitter<Object> emitter) {
                        emitter.onNext("处理后的" + 444555);
                        emitter.onComplete();
                    }
                });
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onNext(Object o) {
                Log.d(TAG, "(wzr)->onNext: " + o);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "(wzr)->onComplete: ");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "(wzr)->onError: " + throwable.toString());
            }

            @Override
            public void onSubscribe() {
                Log.d(TAG, "(wzr)->onSubscribe: ");
            }
        });
    }

    private void mapTest() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter<Object> emitter) {
                emitter.onNext("111");
                emitter.onComplete();
            }
        }).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) {
                return o + "222";
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onNext(Object o) {
                System.out.println(o);
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onSubscribe() {

            }
        });
    }

    private void createTest() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter<Object> emitter) {
                emitter.onNext("1111");
                emitter.onNext("22");
                emitter.onNext("333");
                emitter.onError(new Throwable("wzr"));
                emitter.onComplete();
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onNext(Object o) {
                Log.d(TAG, "(wzr)->onNext: " + o);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "(wzr)->onComplete: ");
            }

            @Override
            public void onError(Throwable throwable) {
                Log.d(TAG, "(wzr)->onError: " + throwable.toString());
            }

            @Override
            public void onSubscribe() {
                Log.d(TAG, "(wzr)->onSubscribe: ");
            }
        });
    }
}