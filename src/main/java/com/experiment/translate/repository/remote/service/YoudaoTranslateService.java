package com.experiment.translate.repository.remote.service;

import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_retrofit.RetrofitBuilder;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;

public interface YoudaoTranslateService {
    @RetrofitBuilder.GET("api?q={fromText}&from=en&to=zh-CHS&appKey=12ec819274fd65c7&salt=12345678&sign={sign}&signType=v3&curtime={curtime}")
    public Observable<YoudaoTranslationResponse> translateEnglish2Chinese(@RetrofitBuilder.Path("fromText") String fromText, @RetrofitBuilder.Path("sign") String sign, @RetrofitBuilder.Path("curtime") String curTime);
    @RetrofitBuilder.GET("api?q={fromText}&from=zh-CHS&to=en&appKey=12ec819274fd65c7&salt=12345678&sign={sign}&signType=v3&curtime={curtime}")
    public Observable<YoudaoTranslationResponse> translateChinese2English(@RetrofitBuilder.Path("fromText") String fromText, @RetrofitBuilder.Path("sign") String sign, @RetrofitBuilder.Path("curtime") String curTime);
//    @RetrofitBuilder.POST("ttsapi?q={path}")
//    public byte[] getTempVoice(@RetrofitBuilder.Path("path") String path);

    @RetrofitBuilder.POST("ttsapi?q={fromText}&appKey=12ec819274fd65c7&salt=12345678&sign={sign}&voiceName=youxiaoqin")
    public byte[] getVoice(@RetrofitBuilder.Path("fromText") String fromText, @RetrofitBuilder.Path("sign") String sign);
}
