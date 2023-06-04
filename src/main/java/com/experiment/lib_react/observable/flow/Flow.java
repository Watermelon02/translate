package com.experiment.lib_react.observable.flow;

import com.experiment.lib_react.emitter.FlowEmitter;
import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_react.observer.Observer;

public class Flow<T> extends Observable<T> {
    FlowEmitter<T> parent;

    public Observable<T> onNext(T t) {
        parent.onNext(t);
        return this;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        if (parent == null) {
            parent = new FlowEmitter<>();
        }
        observer.onSubscribe(parent);

        parent.addObserver(observer);
    }

    public void clearAllObserver(){
        parent.clearAllObservers();
    }
}
