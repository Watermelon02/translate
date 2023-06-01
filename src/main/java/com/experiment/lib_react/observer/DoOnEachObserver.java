package com.experiment.lib_react.observer;

import com.experiment.lib_react.disposable.Disposable;

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/17 09:40
 */
public class DoOnEachObserver<T> implements Observer<T>, Disposable {
    private final Observer<T> observer;
    private final OnNextAction<T> onNext;
    private final Runnable onComplete;
    private final Runnable onError;

    public DoOnEachObserver(Observer<T> observer, OnNextAction<T> onNext, Runnable onComplete, Runnable onError) {
        this.observer = observer;
        this.onNext = onNext;
        this.onComplete = onComplete;
        this.onError = onError;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
    }

    @Override
    public void onNext(T t) {
        if (onNext != null) {
            onNext.run(t);
        }
        observer.onNext(t);
    }

    @Override
    public void onComplete() {
        if (onComplete != null) {
            onComplete.run();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (onError != null) {
            onError.run();
        }
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
