package com.experiment.translate.repository.bean;

import javafx.scene.image.Image;

public class User {
    private long userId;
    private String account;
    private String password;
    private int level;
    private int learningDays;
    private int vocabulary;
    private String role;
    private Image profileImage;
    private String name;

    // 构造函数、Getter和Setter方法

    public User() {
    }

    public User(long userId, String account, String password, int level, int learningDays, int vocabulary, String role, Image profileImage, String name) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.level = level;
        this.learningDays = learningDays;
        this.vocabulary = vocabulary;
        this.role = role;
        this.name = name;
        this.profileImage = profileImage;

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLearningDays() {
        return learningDays;
    }

    public void setLearningDays(int learningDays) {
        this.learningDays = learningDays;
    }

    public int getVocabulary() {
        return vocabulary;
    }

    public void setVocabulary(int vocabulary) {
        this.vocabulary = vocabulary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(Image profileImage) {
        this.profileImage = profileImage;
    }
}


