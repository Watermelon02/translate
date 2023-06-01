package com.experiment.lib_retrofit.proxy_method;


import com.experiment.lib_connect.Call;
import com.experiment.lib_connect.Request;
import com.experiment.lib_connect.RequestBuilder;
import com.experiment.lib_retrofit.Retrofit;
import com.experiment.lib_retrofit.RetrofitBuilder;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public abstract class HttpUrlMethod<RequestT, ReturnT> extends ParseArgsMethod<ReturnT> {
    @Override
    public Call<ReturnT> invoke(Object[] args) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static <RequestT, ReturnT> HttpUrlMethod<RequestT, ReturnT> parseAnnotation(
            Method method,
            RequestT type,
            Retrofit retrofit) {
        Object annotation = method.getAnnotations()[0];
        if (annotation instanceof RetrofitBuilder.GET) {
            return new HttpUrlMethod<RequestT, ReturnT>() {
                @Override
                public Call<ReturnT> invoke(Object[] args) {
                    url = retrofit.getBaseUrl() + ((RetrofitBuilder.GET) annotation).value();
                    parseArgs(method, args);
                    Request request = new RequestBuilder().method("GET").url(url).build();
                    return new Call<ReturnT>(request);
                }
            };
        } else if (annotation instanceof RetrofitBuilder.POST) {
            return new HttpUrlMethod<RequestT, ReturnT>() {
                @Override
                public Call<ReturnT> invoke(Object[] args) {
                    url = retrofit.getBaseUrl() + ((RetrofitBuilder.POST) annotation).value();
                    parseArgs(method, args);
                    Request request = new RequestBuilder().method("POST").url(url).body(body).build();
                    return new Call(request);
                }
            };
        } else {
            throw new RuntimeException();
        }
    }

    public void parseArgs(Method method, Object[] args) {
        int index = 0;
        for (Parameter parameter : method.getParameters()) {
            if (parameter.getAnnotations().length > 0 && parameter.getAnnotations()[0] instanceof RetrofitBuilder.Path) {
                RetrofitBuilder.Path pathAnnotation = (RetrofitBuilder.Path) parameter.getAnnotations()[0];
                String str = pathAnnotation.value();
                String[] strList = url.split("\\{" + str + "\\}");
                url = strList[0] + args[index];
                if (strList.length > 1) {//当参数为url最末尾时,strList的长度为1，所以做此处理避免溢出
                    url = strList[0] + args[index] + strList[1];
                }
                index++;
            } else if (parameter.getAnnotations().length > 0 && parameter.getAnnotations()[0] instanceof RetrofitBuilder.Field) {
                body = (String) args[index];
                index++;
            }
        }
        System.out.println("url:" + url);
    }
}