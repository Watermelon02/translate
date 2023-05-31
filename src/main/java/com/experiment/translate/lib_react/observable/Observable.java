package com.experiment.translate.lib_react.observable;

import com.experiment.translate.lib_react.ObservableOnSubscribe;
import com.experiment.translate.lib_react.observer.Observer;
import com.experiment.translate.lib_react.observer.OnNextAction;
import com.experiment.translate.lib_react.scheduler.Scheduler;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/7 14:54
 */
public abstract class Observable<T> {

    public static <T> Observable<T> create(ObservableOnSubscribe<T> source) {
        return new ObservableCreate<>(source);
    }

    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public Observable<T> observeOn(Scheduler scheduler) {
        return new ObservableObserveOn<>(this, scheduler);
    }

    public Observable<T> subscribeOn(Scheduler scheduler) {
        return new ObservableSubscribeOn<>(this, scheduler);
    }

    public Observable<T> doOnSubscribe(Runnable onSubscribe) {
        return new ObservableDoOnLifecycle<>(this, onSubscribe);
    }

    public Observable<T> doOnNext(OnNextAction<T> onNext) {
        return new ObservableDoOnEach<>(this, onNext, null, null);
    }

    public Observable<T> doOnError(Runnable onError) {
        return new ObservableDoOnEach<>(this, null, onError, null);
    }

    public Observable<T> doOnComplete(Runnable onComplete) {
        return new ObservableDoOnEach<>(this, null, null, onComplete);
    }
}
