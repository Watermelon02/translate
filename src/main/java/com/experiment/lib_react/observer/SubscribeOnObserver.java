package com.experiment.lib_react.observer;

import com.experiment.lib_react.disposable.Disposable;

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/9 15:29
 */
public class SubscribeOnObserver<T> implements Observer<T>, Disposable {
    private final Observer<T> observer;

    public SubscribeOnObserver(Observer<T> observer) {
        this.observer = observer;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        observer.onSubscribe(disposable);
    }

    @Override
    public void onNext(T t) {
        observer.onNext(t);
    }

    @Override
    public void onComplete() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onError(Throwable throwable) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean isDisposed() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
