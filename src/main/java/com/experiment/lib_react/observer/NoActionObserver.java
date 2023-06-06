package com.experiment.lib_react.observer;

import com.experiment.lib_react.disposable.Disposable;

public abstract class NoActionObserver<T> implements Observer<T> {
    @Override
    public void onNext(T t) {
    }

    @Override
    public void onSubscribe(Disposable disposable) {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable throwable) {
        try {
            throwable.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
