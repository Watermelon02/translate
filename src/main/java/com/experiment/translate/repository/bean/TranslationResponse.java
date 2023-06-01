package com.experiment.translate.repository.bean;

import java.util.List;

public class TranslationResponse {
    private String from;
    private String to;
    private List<Translation> trans_result;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<Translation> getTransResult() {
        return trans_result;
    }

    public void setTransResult(List<Translation> transResult) {
        this.trans_result = transResult;
    }

    public static class Translation {
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}

