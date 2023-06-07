package com.experiment.translate.viewmodel;

import com.experiment.lib_react.observable.flow.Flow;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.lib_react.scheduler.MainThreadScheduler;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.translate.repository.bean.Result;
import com.experiment.translate.repository.bean.User;
import com.experiment.translate.repository.remote.retrofit.MyServerCreator;

public class LoginViewModel implements ViewModel {

    public final Flow<User> user = new Flow<>();

    @Override
    public void init() {

    }

    public void login(String account, String password) {
        MyServerCreator
                .getMyServerService()
                .login(account, password)
                .subscribeOn(new NewThreadScheduler())
                .observeOn(new MainThreadScheduler())
                .subscribe(new OnNextObserver<User>() {
                    @Override
                    public void onNext(User userResult) {
                        user.onNext(userResult);

                    }
                });
    }
}
