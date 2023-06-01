package com.experiment.lib_react.observer;

import com.experiment.lib_react.disposable.Disposable;

public abstract class OnNextObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable disposable) {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onError(Throwable throwable) {
        try {

        }catch (Exception e){
            throwable.printStackTrace();
        }
    }
}
