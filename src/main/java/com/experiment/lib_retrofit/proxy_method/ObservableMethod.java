package com.experiment.lib_retrofit.proxy_method;


import com.experiment.lib_connect.Connect;
import com.experiment.lib_connect.Request;
import com.experiment.lib_connect.RequestBuilder;
import com.experiment.lib_react.ObservableOnSubscribe;
import com.experiment.lib_react.emitter.ObservableEmitter;
import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_retrofit.Retrofit;
import com.experiment.lib_retrofit.RetrofitBuilder;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class ObservableMethod<ReturnT> extends ParseArgsMethod<ReturnT> {
    @Override
    public Observable<ReturnT> invoke(Object[] args) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <ReturnT> ObservableMethod<ReturnT> parseAnnotation(
            Method method,
            Retrofit retrofit) {
        Object annotation = method.getAnnotations()[0];
        ParameterizedType observableType  = (ParameterizedType)method.getGenericReturnType();
        Class actualTypeArgument = (Class<?>)observableType.getActualTypeArguments()[0];
        if (annotation instanceof RetrofitBuilder.GET) {
            return new ObservableMethod<ReturnT>() {
                @Override
                public Observable<ReturnT> invoke(Object[] args) {

                    return Observable.create(new ObservableOnSubscribe<ReturnT>() {
                        @Override
                        public void subscribe(ObservableEmitter<ReturnT> observableEmitter) {
                            url = retrofit.getBaseUrl() + ((RetrofitBuilder.GET) annotation).value();
                            parseArgs(method, args);
                            Request request = new RequestBuilder().method("GET").url(url).build();
                            ReturnT result = (ReturnT) Connect.get(request.getUrl(),actualTypeArgument).body;
                            observableEmitter.onNext(result);
                        }
                    });
                }
            };
        } else if (annotation instanceof RetrofitBuilder.POST) {
            return new ObservableMethod<ReturnT>() {
                @Override
                public Observable<ReturnT> invoke(Object[] args) {
                    return Observable.create(new ObservableOnSubscribe<ReturnT>() {
                        @Override
                        public void subscribe(ObservableEmitter<ReturnT> observableEmitter) {
                            url = retrofit.getBaseUrl() + ((RetrofitBuilder.POST) annotation).value();
                            parseArgs(method, args);
                            Request request = new RequestBuilder().method("POST").url(url).build();
                            ReturnT result = (ReturnT) Connect.post(request, actualTypeArgument).body;
                            observableEmitter.onNext(result);
                        }
                    });
                }
            };
        } else {
            throw new RuntimeException();
        }
    }
}