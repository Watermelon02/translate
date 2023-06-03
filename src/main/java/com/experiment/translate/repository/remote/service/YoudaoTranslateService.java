package com.experiment.translate.repository.remote.service;

import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_retrofit.RetrofitBuilder;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;

public interface YoudaoTranslateService {
    @RetrofitBuilder.GET("api?q={fromText}&from=en&to=zh-CHS&appKey=12ec819274fd65c7&salt=12345678&sign={sign}&signType=v3&curtime={curtime}")
    public Observable<YoudaoTranslationResponse> translate(@RetrofitBuilder.Path("fromText") String fromText, @RetrofitBuilder.Path("sign") String sign, @RetrofitBuilder.Path("curtime") String curTime);
}
