package com.experiment.lib_connect;

public class Request {
    private final String method;
    private final String url;
    private final String body;

    public Request(String method, String url, String body) {
        this.method = method;
        this.url = url;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }
}


