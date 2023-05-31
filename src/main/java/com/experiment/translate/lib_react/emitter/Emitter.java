package com.experiment.translate.lib_react.emitter;

/**
 * description ： TODO:类的作用
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/7 20:53
 */
public interface Emitter<T> {
    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);
}
