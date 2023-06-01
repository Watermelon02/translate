package com.experiment.lib_connect;


public class Call<T> {
    private Request request;

    public Call(Request request) {
        this.request = request;
    }

    public <T> void enqueue(Callback<T> callback, Class<T> responseType) {
        if (request.getMethod().equals("GET")) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        callback.onResponse(Connect.get(request.getUrl(), responseType));
                    } catch (Exception e) {
                        System.out.println("testTag:" + "Call:" + e);
                        callback.onFailure(e);
                    }
                }
            }).start();
        } else if (request.getMethod().equals("POST")) {
            try {
                callback.onResponse(Connect.post(request, responseType));
            } catch (Exception e) {
                callback.onFailure(e);
                throw new RuntimeException("Call: Error occurred"+e);
            }
        }
    }

    public <T> T await(Class<T> responseType) {
        try {
            if (request.getMethod().equals("GET")) {
                return Connect.get(request.getUrl(), responseType).body;
            } else if (request.getMethod().equals("POST")) {
                return Connect.post(request, responseType).body;
            } else {
                throw new RuntimeException("Call: No such method");
            }
        } catch (Exception e) {
            // 处理异常情况
            e.printStackTrace(); // 可以根据需要进行日志记录或其他处理
            throw new RuntimeException("Call: Error occurred", e);
        }
    }


    public interface Callback<T> {
        void onResponse(Response<T> response);

        void onFailure(Exception e);
    }
}
