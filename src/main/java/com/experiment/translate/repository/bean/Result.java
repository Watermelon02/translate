package com.experiment.translate.repository.bean;


import java.io.Serializable;

public class Result<T> implements Serializable {
    private int status;
    private Long total;
    private T data;

    public Result(int status, Long total, T data) {
        this.status = status;
        this.total = total;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
