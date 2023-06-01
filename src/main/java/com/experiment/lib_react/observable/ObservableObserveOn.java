package com.experiment.lib_react.observable;

import com.experiment.lib_react.observer.ObserveOnObserver;
import com.experiment.lib_react.observer.Observer;
import com.experiment.lib_react.scheduler.Scheduler;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/8 22:02
 * @param <T> the type of the items emitted by the observable
 */
public class ObservableObserveOn<T> extends Observable<T> {
    private final Observable<T> source;
    private final Scheduler scheduler;

    public ObservableObserveOn(Observable<T> source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        Scheduler.Worker worker = scheduler.createWorker();
        source.subscribe(new ObserveOnObserver<>(worker, observer));
    }
}
