package com.experiment.lib_retrofit;

import com.experiment.lib_retrofit.proxy_method.ServiceMethod;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Retrofit {
    private final String baseUrl;
//    private final Gson gson;

    public String getBaseUrl() {
        return baseUrl;
    }

    public Retrofit(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public <T> T create(Class<T> service) {
        T t = (T) Proxy.newProxyInstance(
                service.getClassLoader(),
                new Class<?>[]{service},
                (proxy, method, args) -> {
                    return loadServiceMethod(method, this).invoke(args);
                }
        );
        return t;
    }

    private ServiceMethod<?> loadServiceMethod(Method method, Retrofit retrofit) {
        return ServiceMethod.parseAnnotation(method, retrofit);
    }
}
