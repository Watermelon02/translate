package com.experiment.translate.connect;
import java.lang.Exception;

public class MCall {
    private Request request;

    public MCall(Request request) {
        this.request = request;
    }

    public void enqueue(ResponseCallback onResponse, ExceptionCallback onFailure) {
        switch (request.getMethod()) {
            case "GET":
                new Thread(() -> {
                    try {
                        Response response = MConnect.get(request.getUrl());
                        onResponse.onResponse(response);
                    } catch (Exception e) {
                        onFailure.onFailure(e);
                    }
                }).start();
                break;
            case "POST":
                new Thread(() -> {
                    try {
                        Response response = MConnect.post(request.getUrl());
                        onResponse.onResponse(response);
                    } catch (Exception e) {
                        onFailure.onFailure(e);
                    }
                }).start();
                break;
        }
    }

    public interface ResponseCallback {
        void onResponse(Response response);
    }

    public interface ExceptionCallback {
        void onFailure(Exception e);
    }
}

