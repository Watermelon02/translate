package com.experiment.translate.retrofit;


import com.experiment.translate.connect.MCall;

interface GetService {
    @MRetrofitBuilder.GET("/user/lg/userinfo/json")
    public MCall get() ;
}