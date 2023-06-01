package com.experiment.lib_react.observable;

import com.experiment.lib_react.ObservableOnSubscribe;
import com.experiment.lib_react.observer.Observer;
import com.experiment.lib_react.emitter.CreateEmitter;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/7 15:01
 */
public class ObservableCreate<T> extends Observable<T> {
    private final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<T> observer) {
        CreateEmitter<T> parent = new CreateEmitter<>(observer);
        observer.onSubscribe(parent);
        try {
            source.subscribe(parent);
        } catch (Exception e) {
            parent.onError(e);
        }
    }
}
