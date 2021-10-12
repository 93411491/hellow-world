package com.example.myrxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myrxjava.core.Emitter;
import com.example.myrxjava.core.Observable;
import com.example.myrxjava.core.ObservableOnSubscribe;
import com.example.myrxjava.core.Observer;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "wzr->MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(Emitter<Object> emitter) {
                emitter.onNext("1111");
                emitter.onNext("22");
                emitter.onNext("333");
                emitter.onError(new Throwable("wzr"));
                emitter.onComplete();
            }
        }).subscibe(new Observer<Object>() {
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