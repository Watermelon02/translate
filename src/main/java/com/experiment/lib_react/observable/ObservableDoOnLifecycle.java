package com.experiment.lib_react.observable;

import com.experiment.lib_react.observer.DoOnLifecycleObserver;
import com.experiment.lib_react.observer.Observer;

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/17 09:26
 */
public class ObservableDoOnLifecycle<T> extends Observable<T> {
    private final Observable<T> source;
    private final Runnable onSubscribe;

    public ObservableDoOnLifecycle(Observable<T> source, Runnable onSubscribe) {
        this.source = source;
        this.onSubscribe = onSubscribe;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        DoOnLifecycleObserver<T> doOnLifecycleObserver = new DoOnLifecycleObserver<>(observer, onSubscribe);
        observer.onSubscribe(doOnLifecycleObserver);
        source.subscribe(doOnLifecycleObserver);
    }
}
