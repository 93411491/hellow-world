package com.example.myrxjava.Scheduler;

import android.os.Handler;

/**
 * author : wangzhirui
 * date : 2021/12/7
 * description :
 */
public class HandlerScheduler extends Scheduler{

    private final Handler handler;

    public HandlerScheduler(Handler handler) {
        this.handler = handler;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(handler);
    }

    static class HandlerWorker implements Worker{

        //外部注入的，执行线程切换的映射
        Handler mapper;

        public HandlerWorker(Handler mapper) {
            this.mapper = mapper;
        }

        @Override
        public void scheduler(Runnable runnable) {
            mapper.post(runnable);
        }
    }
}
