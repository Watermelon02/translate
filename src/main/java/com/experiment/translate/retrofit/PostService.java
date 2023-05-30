package com.experiment.translate.retrofit;


import com.experiment.translate.connect.MCall;

interface PostService {
    @MRetrofitBuilder.POST("user/login/?username=xigua&password=123456")
    public MCall post();
}
