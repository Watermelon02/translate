package com.experiment.translate.viewmodel;

import com.experiment.lib_react.observable.flow.Flow;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.lib_react.scheduler.MainThreadScheduler;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.lib_retrofit.Retrofit;
import com.experiment.translate.helper.SignGenerator;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.repository.remote.retrofit.YoudaoServiceCreator;

public class TranslateViewModel implements ViewModel {
    //    public Flow<BaiduTranslationResponse> toText;
    public Flow<YoudaoTranslationResponse> toText;

    private Retrofit baiduRetrofit = new Retrofit("http://api.fanyi.baidu.com/");
//    private BaiduTranslateService baiduTranslateService;


    @Override
    public void init() {
//        baiduTranslateService = baiduRetrofit.create(BaiduTranslateService.class);
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
        YoudaoServiceCreator.getYoudaoTranslateService().translate(fromText, sign, curtimeText).subscribeOn(new NewThreadScheduler()).observeOn(new MainThreadScheduler()).subscribe(new OnNextObserver<YoudaoTranslationResponse>() {

            @Override
            public void onNext(YoudaoTranslationResponse s) {
                toText.onNext(s);
            }
        });
    }
}
