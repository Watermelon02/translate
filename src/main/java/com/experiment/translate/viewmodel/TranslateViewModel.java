package com.experiment.translate.viewmodel;

import com.experiment.lib_react.observable.Flow;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.lib_retrofit.Retrofit;
import com.experiment.translate.helper.SignGenerator;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.repository.remote.service.BaiduTranslateService;
import com.experiment.translate.repository.remote.service.YoudaoTranslateService;

public class TranslateViewModel implements ViewModel {
    //    public Flow<BaiduTranslationResponse> toText;
    public Flow<YoudaoTranslationResponse> toText;

    private Retrofit baiduRetrofit = new Retrofit("http://api.fanyi.baidu.com/");
    private Retrofit youdaoRetrofit = new Retrofit("https://openapi.youdao.com/");
    private BaiduTranslateService baiduTranslateService;
    private YoudaoTranslateService youdaoTranslateService;


    @Override
    public void init() {
        baiduTranslateService = baiduRetrofit.create(BaiduTranslateService.class);
        youdaoTranslateService = youdaoRetrofit.create(YoudaoTranslateService.class);
//        toText = new Flow<BaiduTranslationResponse>();
        toText = new Flow<YoudaoTranslationResponse>();
    }

    public void translate(String fromText) {
//        String sign = SignGenerator.generateBaiduSign(fromText);
//        baiduTranslateService.translate(fromText, sign).subscribeOn(new NewThreadScheduler()).subscribe(new OnNextObserver<TranslationResponse>() {
//
//            @Override
//            public void onNext(TranslationResponse s) {
//                toText.onNext(s);
//            }
//        });
        long curtime = System.currentTimeMillis() / 1000; // 获取当前时间戳（秒级）
        String curtimeText = String.valueOf(curtime);
        String sign = SignGenerator.generateYoudaoSign(fromText, curtimeText);
        youdaoTranslateService.translate(fromText, sign, curtimeText).subscribeOn(new NewThreadScheduler()).subscribe(new OnNextObserver<YoudaoTranslationResponse>() {

            @Override
            public void onNext(YoudaoTranslationResponse s) {
                toText.onNext(s);
            }
        });
    }
}
