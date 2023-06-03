package com.experiment.translate.repository.bean;

public class Explanation {
    private long id;
    private String meaning;

    // 构造函数、Getter和Setter方法

    public Explanation() {
    }

    public Explanation(long id, String meaning) {
        this.id = id;
        this.meaning = meaning;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}

