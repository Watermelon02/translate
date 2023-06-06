package com.experiment.translate.repository.remote.retrofit;

import com.experiment.lib_retrofit.Retrofit;
import com.experiment.lib_retrofit.RetrofitBuilder;
import com.experiment.translate.repository.remote.service.MyServerService;

public class MyServerCreator {
    private static MyServerCreator instance;
    private final Retrofit myServerRetrofit;
    private static MyServerService myServerService;

    private MyServerCreator() {
        myServerRetrofit = new RetrofitBuilder()
                .baseUrl("https://localhost:8089/")
                // 添加其他配置，如转换器、调用适配器等
                .build();
        myServerService = myServerRetrofit.create(MyServerService.class);
    }

    public static MyServerCreator getInstance() {
        if (instance == null) {
            instance = new MyServerCreator();
        }
        return instance;
    }

    public Retrofit getMyServerRetrofit() {
        return myServerRetrofit;
    }

    public static MyServerService getMyServerService() {
        if (myServerService == null){
            myServerService = getInstance().myServerRetrofit.create(MyServerService.class);
        }
        return myServerService;
    }
}

