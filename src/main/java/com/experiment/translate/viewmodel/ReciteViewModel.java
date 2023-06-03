package com.experiment.translate.viewmodel;

import com.experiment.lib_react.ObservableOnSubscribe;
import com.experiment.lib_react.emitter.ObservableEmitter;
import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.translate.MainApp;
import com.experiment.translate.repository.bean.WordSet;
import com.experiment.translate.repository.local.database.TranslateDatabase;

import java.util.List;

public class ReciteViewModel implements ViewModel {
    TranslateDatabase database = TranslateDatabase.getInstance();

    @Override
    public void init() {
    }

    public Observable<List<WordSet>> fetchWordSet() {
        return Observable.create(new ObservableOnSubscribe<List<WordSet>>() {
            @Override
            public void subscribe(ObservableEmitter<List<WordSet>> observableEmitter) {
                List<WordSet> wordSets = database.getUserDAO().getWordSetByUserId(MainApp.userId);
                observableEmitter.onNext(wordSets);
            }
        }).subscribeOn(new NewThreadScheduler());
    }
}
