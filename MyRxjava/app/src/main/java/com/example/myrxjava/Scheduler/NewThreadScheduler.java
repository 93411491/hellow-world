package com.example.myrxjava.Scheduler;

import android.os.Handler;

import java.util.concurrent.ExecutorService;

/**
 * author : wangzhirui
 * date : 2021/12/7
 * description :
 */
public class NewThreadScheduler extends Scheduler{

    ExecutorService executorService;

    public NewThreadScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Worker createWorker() {
        return new NewThreadWorker(executorService);
    }

    static final class NewThreadWorker implements Worker {

        ExecutorService mapper;

        public NewThreadWorker(ExecutorService mapper) {
            this.mapper = mapper;
        }

        @Override
        public void scheduler(Runnable runnable) {
            mapper.execute(runnable);
        }
    }

}
