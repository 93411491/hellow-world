package com.example.myrxjava.Scheduler;

import android.os.Handler;

/**
 * author : wangzhirui
 * date : 2021/12/7
 * description : 外界将handler传入得到HandlerScheduler中，让runnable在相应
 *              线程执行
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

    //具体实现类
    static class HandlerWorker implements Worker{

        //外部注入的，执行线程切换的映射
        Handler mapper;

        public HandlerWorker(Handler mapper) {
            this.mapper = mapper;
        }

        //调用该方法时，runnable就在相关handler的
        @Override
        public void scheduler(Runnable runnable) {
            mapper.post(runnable);
        }
    }
}
