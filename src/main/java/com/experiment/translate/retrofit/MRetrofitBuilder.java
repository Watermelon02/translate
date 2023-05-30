package com.experiment.translate.retrofit;

public class MRetrofitBuilder {
    public @interface GET {
        String value();
    }

    public @interface POST {
        String value();
    }

    private String baseurl;

    public MRetrofitBuilder baseUrl(String baseurl) {
        this.baseurl = baseurl;
        return this;
    }

    public MRetrofit build() {
        return new MRetrofit(baseurl);
    }
}
