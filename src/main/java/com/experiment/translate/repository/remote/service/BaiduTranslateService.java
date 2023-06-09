package com.experiment.translate.repository.remote.service;

import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_retrofit.RetrofitBuilder;
import com.experiment.translate.repository.bean.BaiduTranslationResponse;

public interface BaiduTranslateService {
    @RetrofitBuilder.GET("api/trans/vip/translate?q={fromText}&from=en&to=zh&appid=20230531001695232&salt=12345678&sign={sign}")
    public Observable<BaiduTranslationResponse> translate(@RetrofitBuilder.Path("fromText") String fromText, @RetrofitBuilder.Path("sign") String sign);
}
