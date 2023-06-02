package com.experiment.lib_retrofit.proxy_method;

import com.experiment.lib_retrofit.RetrofitBuilder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URLEncoder;

public abstract class ParseArgsMethod<T> extends ServiceMethod<T> {

    public void parseArgs(Method method, Object[] args) {
        int index = 0;
        for (Parameter parameter : method.getParameters()) {
            if (parameter.getAnnotations().length > 0 && parameter.getAnnotations()[0] instanceof RetrofitBuilder.Path) {
                RetrofitBuilder.Path pathAnnotation = (RetrofitBuilder.Path) parameter.getAnnotations()[0];
                String str = pathAnnotation.value();
                String[] strList = url.split("\\{" + str + "\\}");
                String arg = args[index].toString();
                try {
                    arg = URLEncoder.encode(args[index].toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
                url = strList[0] + arg;
                if (strList.length > 1) {//当参数为url最末尾时,strList的长度为1，所以做此处理避免溢出
                    url = strList[0] + arg + strList[1];
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
