package com.experiment.lib_react.observer;

import com.experiment.lib_react.disposable.Disposable;

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/5/17 09:26
 */
public class DoOnLifecycleObserver<T> implements Observer<T>, Disposable {
    private final Observer<T> observer;
    private final Runnable onSubscribe;

    public DoOnLifecycleObserver(Observer<T> observer, Runnable onSubscribe) {
        this.observer = observer;
        this.onSubscribe = onSubscribe;
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        onSubscribe.run();
    }

    @Override
    public void onNext(T t) {
        observer.onNext(t);
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable throwable) {
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean isDisposed() {
        return false;
    }
}
