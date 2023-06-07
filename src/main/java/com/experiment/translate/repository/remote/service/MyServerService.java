package com.experiment.translate.repository.remote.service;

import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_retrofit.RetrofitBuilder;
import com.experiment.translate.repository.bean.Result;
import com.experiment.translate.repository.bean.User;
import com.experiment.translate.repository.bean.Word;

public interface MyServerService {
    @RetrofitBuilder.GET("word/get?word_id={word_id}&word_set_id={word_set_id}")
    public Observable<Word> getWord(@RetrofitBuilder.Path("word_id") String word_id,@RetrofitBuilder.Path("word_set_id")long word_set_id);

    @RetrofitBuilder.GET("user/login?account={account}&password={password}&rememberMe=true")
    public Observable<User> login(@RetrofitBuilder.Path("account") String account, @RetrofitBuilder.Path("password")String password);
}
