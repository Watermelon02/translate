package com.experiment.translate.lib_react.scheduler;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/8 22:46
 */
public class NewThreadScheduler extends Scheduler {
    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue<>());

    @Override
    public Worker createWorker() {
        return new NewThreadWorker();
    }

    @Override
    public void scheduleActual(Runnable runnable) {
        threadPool.submit(runnable);
    }

    public class NewThreadWorker extends Worker {
        @Override
        public void schedule(Runnable runnable) {
            threadPool.submit(runnable);
        }
    }
}
