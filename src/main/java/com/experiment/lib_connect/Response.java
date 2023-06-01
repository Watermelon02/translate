package com.experiment.lib_connect;

public class Response<T> {
    public T body;

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Response(T body) {
        this.body = body;
    }
}
