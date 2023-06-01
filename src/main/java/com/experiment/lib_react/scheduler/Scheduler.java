package com.experiment.lib_react.scheduler;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/8 20:18
 */
public abstract class Scheduler {
    public abstract Worker createWorker();

    public abstract void scheduleActual(Runnable runnable);

    public abstract class Worker {
        public abstract void schedule(Runnable runnable);
    }
}
