package com.experiment.translate.viewmodel;

import com.experiment.lib_react.disposable.Disposable;
import com.experiment.lib_react.observable.Flow;
import com.experiment.lib_react.observer.Observer;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.lib_retrofit.Retrofit;
import com.experiment.translate.helper.TranslateHelper;
import com.experiment.translate.repository.bean.TranslationResponse;
import com.experiment.translate.repository.service.TranslateService;

public class TranslateViewModel implements ViewModel {
    public Flow<TranslationResponse> toText;
    private Retrofit retrofit = new Retrofit("http://api.fanyi.baidu.com/");
    private TranslateService translateService;


    @Override
    public void init() {
        translateService = retrofit.create(TranslateService.class);
        toText = new Flow<TranslationResponse>();
    }

    public void translate(String fromText) {
        String sign = TranslateHelper.generateSign(fromText);
        translateService.translate(fromText, sign).subscribeOn(new NewThreadScheduler()).subscribe(new OnNextObserver<TranslationResponse>() {

            @Override
            public void onNext(TranslationResponse s) {
                toText.onNext(s);
            }
        });

    }
}
