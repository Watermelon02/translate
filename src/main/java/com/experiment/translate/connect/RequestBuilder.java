package com.experiment.translate.connect;

public class RequestBuilder {
    private String method;
    private String url;

    public RequestBuilder method(String method) {
        this.method = method;
        return this;
    }

    public RequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public Request build() {
        return new Request(method, url);
    }
}
