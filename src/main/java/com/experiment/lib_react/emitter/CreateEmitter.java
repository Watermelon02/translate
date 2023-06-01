package com.experiment.lib_react.emitter;

import com.experiment.lib_react.disposable.Disposable;
import com.experiment.lib_react.disposable.DisposableHelper;
import com.experiment.lib_react.observer.Observer;
import java.util.concurrent.atomic.AtomicReference;

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/7 14:57
 */
public class CreateEmitter<T> implements Disposable, ObservableEmitter<T> {
    private AtomicReference<Disposable> disposable = new AtomicReference<>(this);
    private Disposable disposed = new DisposableHelper();
    private Observer<T> observer;

    public CreateEmitter(Observer<T> observer) {
        this.observer = observer;
    }

    @Override
    public void dispose() {
        disposable.set(disposed);
    }

    @Override
    public boolean isDisposed() {
        return disposable.get() == disposed;
    }

    @Override
    public void onNext(T t) {
        if (!isDisposed()) {
            observer.onNext(t);
        }
    }

    @Override
    public void onComplete() {
        if (!isDisposed()) {
            observer.onComplete();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (!isDisposed()) {
            observer.onError(throwable);
        }
    }
}
