package com.experiment.translate.lib_react.observer;

import com.experiment.translate.lib_react.disposable.Disposable;
import com.experiment.translate.lib_react.scheduler.Scheduler;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/8 22:15
 */
public class ObserveOnObserver<T> implements Observer<T>, Runnable {
    private final Scheduler.Worker worker;
    private final Observer<T> actual;
    private final LinkedBlockingQueue<T> queue;

    public ObserveOnObserver(Scheduler.Worker worker, Observer<T> actual) {
        this.worker = worker;
        this.actual = actual;
        this.queue = new LinkedBlockingQueue<>();
    }

    @Override
    public void onSubscribe(Disposable disposable) {
        actual.onSubscribe(disposable);
    }

    @Override
    public void onNext(T t) {
        queue.add(t);
        worker.schedule(this);
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
    public void run() {
        while (true) {
            T data = queue.poll();
            if (data == null) {
                break;
            }
            actual.onNext(data);
        }
    }
}
