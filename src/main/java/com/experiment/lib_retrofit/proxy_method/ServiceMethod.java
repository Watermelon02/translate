package com.experiment.lib_retrofit.proxy_method;

import com.experiment.lib_retrofit.Retrofit;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public abstract class ServiceMethod<T> {
    public abstract Object invoke(Object[] args);

    public String url;
    public String body = ""; // post请求中的body

    public static <RequestT> ServiceMethod<RequestT> parseAnnotation(Method method, Retrofit retrofit) {
        if (method.getReturnType().getPackageName().equals("com.experiment.lib_connect.call")) {
            return HttpUrlMethod.parseAnnotation(method, method.getGenericReturnType(), retrofit);
        } else if (method.getReturnType().getPackageName().equals("com.experiment.lib_react.observable")) {
            return ObservableMethod.parseAnnotation(method, retrofit);
        } else {
            return ReturnTypeMethod.parseAnnotation(method, retrofit);
        }
    }
}
