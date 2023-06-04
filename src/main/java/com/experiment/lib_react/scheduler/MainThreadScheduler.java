package com.experiment.lib_react.scheduler;


import com.experiment.translate.MainApp;
import javafx.application.Platform;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/8 22:46
 */
public class MainThreadScheduler extends Scheduler {
    @Override
    public Worker createWorker() {
        return new MainThreadWorker();
    }

    @Override
    public void scheduleActual(Runnable runnable) {
        Platform.runLater(runnable);
    }

    public class MainThreadWorker extends Worker {
        @Override
        public void schedule(Runnable runnable) {
            Platform.runLater(runnable);
        }
    }
}

