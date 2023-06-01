package com.experiment.lib_retrofit.proxy_method;

import com.experiment.lib_connect.Connect;
import com.experiment.lib_connect.Request;
import com.experiment.lib_connect.RequestBuilder;
import com.experiment.lib_retrofit.Retrofit;
import com.experiment.lib_retrofit.RetrofitBuilder;

import java.lang.reflect.Method;

public class ReturnTypeMethod<ReturnT> extends ParseArgsMethod<ReturnT> {
    @Override
    public ReturnT invoke(Object[] args) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <ReturnT> ReturnTypeMethod<ReturnT> parseAnnotation(
            Method method,
            Retrofit retrofit) {
        Object annotation = method.getAnnotations()[0];
        if (annotation instanceof RetrofitBuilder.GET) {
            return new ReturnTypeMethod<ReturnT>() {
                @Override
                public ReturnT invoke(Object[] args) {
                    url = retrofit.getBaseUrl() + ((RetrofitBuilder.GET) annotation).value();
                    parseArgs(method, args);
                    Request request = new RequestBuilder().method("GET").url(url).build();
                    return (ReturnT) Connect.get(request.getUrl(), method.getReturnType().getClass()).body;
                }
            };
        } else if (annotation instanceof RetrofitBuilder.POST) {
            return new ReturnTypeMethod<ReturnT>() {
                @Override
                public ReturnT invoke(Object[] args) {
                    url = retrofit.getBaseUrl() + ((RetrofitBuilder.POST) annotation).value();
                    parseArgs(method, args);
                    Request request = new RequestBuilder().method("POST").url(url).build();
                    return (ReturnT) Connect.post(request, method.getReturnType().getClass()).body;
                }
            };
        } else {
            throw new RuntimeException();
        }
    }
}
