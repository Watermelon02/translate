package com.experiment.translate.lib_react;

import com.experiment.translate.lib_react.emitter.ObservableEmitter;

/**
 * author : Watermelon02
 * email : 1446157077@qq.com
 * date : 2022/4/7 14:56
 */
public abstract class ObservableOnSubscribe<T> {
    public abstract void subscribe(ObservableEmitter<T> observableEmitter);
}
