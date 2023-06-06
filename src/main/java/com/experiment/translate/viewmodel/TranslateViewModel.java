package com.experiment.translate.viewmodel;

import com.experiment.lib_react.ObservableOnSubscribe;
import com.experiment.lib_react.emitter.ObservableEmitter;
import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_react.observable.flow.Flow;
import com.experiment.lib_react.observer.NoActionObserver;
import com.experiment.lib_react.observer.OnNextAction;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.lib_react.scheduler.MainThreadScheduler;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.lib_retrofit.Retrofit;
import com.experiment.translate.util.FileUtil;
import com.experiment.translate.util.SignGenerator;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.repository.remote.retrofit.YoudaoServiceCreator;

import java.util.List;

public class TranslateViewModel implements ViewModel {
    //    public Flow<BaiduTranslationResponse> toText;
    public Flow<YoudaoTranslationResponse> toText = new Flow<YoudaoTranslationResponse>();

    private Retrofit baiduRetrofit = new Retrofit("http://api.fanyi.baidu.com/");
    //    private BaiduTranslateService baiduTranslateService;

    @Override
    public void init() {
//        baiduTranslateService = baiduRetrofit.create(BaiduTranslateService.class);
//        toText = new Flow<BaiduTranslationResponse>();
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
        YoudaoServiceCreator.getYoudaoTranslateService().translate(fromText, sign, curtimeText).doOnNext(new OnNextAction<YoudaoTranslationResponse>() {
            @Override
            public void run(YoudaoTranslationResponse youdaoTranslationResponse) {
                try {
                    youdaoTranslationResponse.generateAndSetLocalVoicePath();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).subscribeOn(new NewThreadScheduler()).observeOn(new MainThreadScheduler()).subscribe(new OnNextObserver<YoudaoTranslationResponse>() {

            @Override
            public void onNext(YoudaoTranslationResponse s) {
                toText.onNext(s);
                //新开一个线程,删除temp目录之前的录音文件
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> observableEmitter) {
                        FileUtil.deleteFilesExcept(FileUtil.TEMP_FILE, List.of(s.getSpeakUrl(), s.getTSpeakUrl()));
                    }
                }).subscribeOn(new NewThreadScheduler()).subscribe(new NoActionObserver<Integer>() {
                });
            }
        });
    }
}
