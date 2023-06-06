package com.experiment.translate.repository.bean;

import com.experiment.lib_connect.Connect;
import com.experiment.translate.util.FileUtil;

import java.util.List;

import static com.experiment.translate.util.FileUtil.FILE_DESCRIPTOR;

public class YoudaoTranslationResponse {
    private String errorCode;
    private String query;
    private String isDomainSupport;
    private List<String> translation;
    private Basic basic;
    private List<WebDefinition> web;
    private Dict dict;
    private WebDict webdict;
    private String l;
    private String tSpeakUrl;
    private String speakUrl;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getIsDomainSupport() {
        return isDomainSupport;
    }

    public void setIsDomainSupport(String isDomainSupport) {
        this.isDomainSupport = isDomainSupport;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public List<WebDefinition> getWeb() {
        return web;
    }

    public void setWeb(List<WebDefinition> web) {
        this.web = web;
    }

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }

    public WebDict getWebdict() {
        return webdict;
    }

    public void setWebdict(WebDict webdict) {
        this.webdict = webdict;
    }

    public String getL() {
        return l;
    }

    public void setL(String l) {
        this.l = l;
    }

    public String getTSpeakUrl() {
        return tSpeakUrl;
    }

    public void setTSpeakUrl(String tSpeakUrl) {
        this.tSpeakUrl = tSpeakUrl;
    }

    public String getSpeakUrl() {
        return speakUrl;
    }

    public void setSpeakUrl(String speakUrl) {
        this.speakUrl = speakUrl;
    }

    public static class Basic {
        private String phonetic;
        private String ukPhonetic;
        private String usPhonetic;
        private String ukSpeech;
        private String usSpeech;
        private List<String> explains;

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        public String getUkPhonetic() {
            return ukPhonetic;
        }

        public void setUkPhonetic(String ukPhonetic) {
            this.ukPhonetic = ukPhonetic;
        }

        public String getUsPhonetic() {
            return usPhonetic;
        }

        public void setUsPhonetic(String usPhonetic) {
            this.usPhonetic = usPhonetic;
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

        public List<String> getExplains() {
            return explains;
        }

        public void setExplains(List<String> explains) {
            this.explains = explains;
        }
    }

    public static class WebDefinition {
        private String key;
        private List<String> value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }
    }

    public static class Dict {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class WebDict {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    /**因为查询直接返回的bean中的语音只能播放一次，所以要存储到本地，该方法存储到本地后将语音路径设置为本地路径*/
    public void generateAndSetLocalVoicePath(){
        byte[] fromTextVoice = Connect.getFile(speakUrl);
        byte[] toTextVoice = Connect.getFile(tSpeakUrl);
        String fromTextVoicePath = FileUtil.saveFile(fromTextVoice, FileUtil.TEMP_FILE);
        String toTextVoicePath = FileUtil.saveFile(toTextVoice, FileUtil.TEMP_FILE);
        setSpeakUrl(FILE_DESCRIPTOR + fromTextVoicePath);
        setTSpeakUrl(FILE_DESCRIPTOR + toTextVoicePath);
    }

    /**因为查询直接返回的bean中的语音只能播放一次，所以要存储到本地，该方法存储到本地后将语音路径设置为本地路径*/
    public void generateAndSetLocalWordVoicePath(){
        byte[] usVoice = Connect.getFile(speakUrl);
        String usVoicePath = FileUtil.saveFile(usVoice, FileUtil.VOICE_FILE);
        basic.setUkSpeech(FILE_DESCRIPTOR + usVoicePath);
        basic.setUsSpeech(FILE_DESCRIPTOR + usVoicePath);
    }
}

