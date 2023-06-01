package com.experiment.lib_react.observable;

import com.experiment.lib_react.observer.Observer;
import com.experiment.lib_react.observer.SubscribeOnObserver;
import com.experiment.lib_react.scheduler.Scheduler;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/9 15:22
 */
public class ObservableSubscribeOn<T> extends Observable<T> {
    private final Observable<T> source;
    private final Scheduler scheduler;

    public ObservableSubscribeOn(Observable<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        SubscribeOnObserver<T> subscribeOnObserver = new SubscribeOnObserver<>(observer);
        scheduler.scheduleActual(new SubscribeTask(observer));
    }

    private class SubscribeTask implements Runnable {
        private final Observer<T> observer;

        public SubscribeTask(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void run() {
            source.subscribe(observer);
        }
    }
}
