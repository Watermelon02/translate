package com.experiment.lib_react.observable.flow;

import com.experiment.lib_react.emitter.FlowEmitter;
import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_react.observer.Observer;

public class StateFlow<T> extends Flow<T>{
    private T value;

    public StateFlow(T value) {
        this.value = value;

    }

    public T getValue() {
        return value;
    }

    @Override
    public Observable<T> onNext(T t) {
        this.value = t;
        return super.onNext(t);
    }

    public void setValue(T value) {
        this.value = value;
        onNext(value);
    }
}
