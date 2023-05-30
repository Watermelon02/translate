package com.experiment.translate.retrofit;

import com.experiment.translate.connect.MCall;
import com.experiment.translate.connect.Request;
import com.experiment.translate.connect.RequestBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MRetrofit {
    private String baseurl;
    private String url;

    public MRetrofit(String baseurl) {
        this.baseurl = baseurl;
    }

    public <T> T create(Class<T> service) {
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return loadServiceMethod(method).invoke(args);
            }
        });
    }

    private RequestMethod loadServiceMethod(Method method) {
        RequestMethod requestMethod = null;
        Annotation annotation = parseAnnotation(method);
        if (annotation instanceof MRetrofitBuilder.GET) {
            requestMethod = new RequestMethod() {
                @Override
                public MCall invoke(Object[] args) {
                    Request request = new RequestBuilder().method("GET").url(url).build();
                    return new MCall(request);
                }
            };
        } else if (annotation instanceof MRetrofitBuilder.POST) {
            requestMethod = new RequestMethod() {
                @Override
                public MCall invoke(Object[] args) {
                    Request request = new RequestBuilder().method("POST").url(url).build();
                    return new MCall(request);
                }
            };
        }
        return requestMethod;
    }

    private Annotation parseAnnotation(Method method) {
        Annotation annotation = method.getAnnotations()[0];
        if (annotation instanceof MRetrofitBuilder.GET) {
            url = baseurl + ((MRetrofitBuilder.GET) annotation).value();
        } else if (annotation instanceof MRetrofitBuilder.POST) {
            url = baseurl + ((MRetrofitBuilder.POST) annotation).value();
        }
        return annotation;
    }
}

