package com.experiment.translate.repository.service;

import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_retrofit.RetrofitBuilder;
import com.experiment.translate.repository.bean.TranslationResponse;

public interface TranslateService {
    @RetrofitBuilder.GET("api/trans/vip/translate?q={fromText}&from=en&to=zh&appid=20230531001695232&salt=12345678&sign={sign}")
    public Observable<TranslationResponse> translate(@RetrofitBuilder.Path("fromText") String fromText, @RetrofitBuilder.Path("sign") String sign);
}
