package com.experiment.translate.repository.remote.retrofit;

import com.experiment.lib_retrofit.Retrofit;
import com.experiment.lib_retrofit.RetrofitBuilder;
import com.experiment.translate.repository.remote.service.YoudaoTranslateService;

public class YoudaoServiceCreator {
    private static YoudaoServiceCreator instance;
    private Retrofit youdaoRetrofit;
    private static YoudaoTranslateService youdaoTranslateService;

    private YoudaoServiceCreator() {
        youdaoRetrofit = new RetrofitBuilder()
                .baseUrl("https://openapi.youdao.com/")
                // 添加其他配置，如转换器、调用适配器等
                .build();
        youdaoTranslateService = youdaoRetrofit.create(YoudaoTranslateService.class);
    }

    public static YoudaoServiceCreator getInstance() {
        if (instance == null) {
            instance = new YoudaoServiceCreator();
        }
        return instance;
    }

    public Retrofit getYoudaoRetrofit() {
        return youdaoRetrofit;
    }

    public static YoudaoTranslateService getYoudaoTranslateService() {
        if (youdaoTranslateService == null){
            youdaoTranslateService = getInstance().youdaoRetrofit.create(YoudaoTranslateService.class);
        }
        return youdaoTranslateService;
    }
}

