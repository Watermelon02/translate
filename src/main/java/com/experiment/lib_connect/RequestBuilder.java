package com.experiment.lib_connect;

public class RequestBuilder {
    private String method;
    private String url;
    private String body = "";

    public RequestBuilder method(String method) {
        this.method = method;
        return this;
    }

    public RequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public RequestBuilder body(String body) {
        this.body = body;
        return this;
    }

    public Request build() {
        return new Request(method, url, body);
    }
}