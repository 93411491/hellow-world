package com.example.myrxjava.Scheduler;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;

/**
 * author : wangzhirui
 * date : 2021/12/7
 * description :
 */
public class Schedulers {
    private static Scheduler MAIN_THREAD = null;
    private static Scheduler NEW_THREAD = null;
    static {
        MAIN_THREAD = new HandlerScheduler(new Handler(Looper.getMainLooper()));
        NEW_THREAD = new NewThreadScheduler(Executors.newScheduledThreadPool(2));
    }

    public static Scheduler mainThread() {
        return MAIN_THREAD;
    }

    public static Scheduler NewThread() {
        return NEW_THREAD;
    }
}
