package com.experiment.lib_react.observer;

import com.experiment.lib_react.disposable.Disposable;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/7 15:03
 */
public interface Observer<T> {
    void onSubscribe(Disposable disposable);
    void onNext(T t);
    void onComplete();
    void onError(Throwable throwable);
}
