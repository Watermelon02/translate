package com.experiment.translate.repository.bean;

import java.util.ArrayList;
import java.util.List;

public class WordSet {
    private long wordSetId;
    private String setName;
    private String setIntroduction;


    private List<Word> wordList = new  ArrayList<Word>();

    // 构造函数、Getter和Setter方法

    public WordSet() {
    }

    public WordSet(long wordSetId, String setName, String setIntroduction) {
        this.wordSetId = wordSetId;
        this.setName = setName;
        this.setIntroduction = setIntroduction;
    }

    public long getWordSetId() {
        return wordSetId;
    }

    public void setWordSetId(long wordSetId) {
        this.wordSetId = wordSetId;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getSetIntroduction() {
        return setIntroduction;
    }

    public void setSetIntroduction(String setIntroduction) {
        this.setIntroduction = setIntroduction;
    }

    public List<Word> getWordList() {
        return wordList;
    }

    public void setWordList(List<Word> wordList) {
        this.wordList = wordList;
    }
}

