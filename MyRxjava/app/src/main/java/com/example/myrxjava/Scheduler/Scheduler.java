package com.example.myrxjava.Scheduler;

/**
 * author : wangzhirui
 * date : 2021/12/7
 * description :
 */
public abstract class Scheduler {
    //暴露给外界调用，用来创建真正执行线程切换的线程调度器
    public abstract Worker createWorker();

    //线程调度具体执行的位置
    //runnable最终在哪个线程执行，就取决于worker在哪里
    public interface Worker{
        void scheduler(Runnable runnable);
    }
}
