package com.experiment.lib_retrofit;

import com.google.gson.Gson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class RetrofitBuilder {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface GET {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface POST {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface Path {
        String value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Field {
        String value();
    }

    private String baseUrl;
    private Gson gson = new Gson();

    public RetrofitBuilder baseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public RetrofitBuilder addGson(Gson gson) {
        this.gson = gson;
        return this;
    }

    public Retrofit build() {
        return new Retrofit(baseUrl);
    }
}
