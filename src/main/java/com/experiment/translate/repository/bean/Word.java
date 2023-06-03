package com.experiment.translate.repository.bean;

import java.util.ArrayList;
import java.util.List;

public class Word {
    private String word_id;
    private String speakUrl;
    private String basicPhonetic;
    private String ukSpeech;
    private String usSpeech;
    private List<String> explanation = new ArrayList<>();

    // 构造函数、Getter和Setter方法

    public Word() {
    }

    public Word(String word_id, String speakUrl, String basicPhonetic, String ukSpeech, String usSpeech) {
        this.word_id = word_id;
        this.speakUrl = speakUrl;
        this.basicPhonetic = basicPhonetic;
        this.ukSpeech = ukSpeech;
        this.usSpeech = usSpeech;
    }

    public String getWord_id() {
        return word_id;
    }

    public void setWord_id(String word_id) {
        this.word_id = word_id;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public String getBasicPhonetic() {
        return basicPhonetic;
    }

    public void setBasicPhonetic(String basicPhonetic) {
        this.basicPhonetic = basicPhonetic;
    }

    public String getUkSpeech() {
        return ukSpeech;
    }

    public void setUkSpeech(String ukSpeech) {
        this.ukSpeech = ukSpeech;
    }

    public String getUsSpeech() {
        return usSpeech;
    }

    public void setUsSpeech(String usSpeech) {
        this.usSpeech = usSpeech;
    }

    public List<String> getExplanation() {
        return explanation;
    }

    public void setExplanation(List<String> explanation) {
        this.explanation = explanation;
    }
}

