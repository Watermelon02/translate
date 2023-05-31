package com.experiment.translate.lib_react.observable;

import com.experiment.translate.lib_react.observer.DoOnEachObserver;
import com.experiment.translate.lib_react.observer.Observer;
import com.experiment.translate.lib_react.observer.OnNextAction;

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/17 09:44
 */
public class ObservableDoOnEach<T> extends Observable<T> {
    private final Observable<T> source;
    private final OnNextAction<T> onNext;
    private final Runnable onComplete;
    private final Runnable onError;

    public ObservableDoOnEach(Observable<T> source, OnNextAction<T> onNext, Runnable onComplete, Runnable onError) {
        this.source = source;
        this.onNext = onNext;
        this.onComplete = onComplete;
        this.onError = onError;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        DoOnEachObserver<T> doOnEachObserver = new DoOnEachObserver<>(observer, onNext, onComplete, onError);
        observer.onSubscribe(doOnEachObserver);
        source.subscribe(doOnEachObserver);
    }
}
