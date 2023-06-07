package com.experiment.translate.viewmodel;

import com.experiment.lib_react.ObservableOnSubscribe;
import com.experiment.lib_react.emitter.ObservableEmitter;
import com.experiment.lib_react.observable.flow.Flow;
import com.experiment.lib_react.observable.Observable;
import com.experiment.lib_react.observable.flow.StateFlow;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.lib_react.scheduler.MainThreadScheduler;
import com.experiment.lib_react.scheduler.NewThreadScheduler;
import com.experiment.translate.MainApp;
import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;
import com.experiment.translate.repository.local.database.TranslateDatabase;
import com.experiment.translate.util.ViewModelController;

import java.util.List;

public class ReciteViewModel implements ViewModel {
    private final TranslateDatabase database = TranslateDatabase.getInstance();
    //当前选中的单词集
    public final StateFlow<Integer> index = new StateFlow<>(0);
    private BaseViewModel baseViewModel;


    @Override
    public void init() {
        baseViewModel = (BaseViewModel) ViewModelController.getInstance().getViewModel(MainApp.baseViewID);
    }

    public void fetchWordSetList() {
        Observable.create(new ObservableOnSubscribe<List<WordSet>>() {
            @Override
            public void subscribe(ObservableEmitter<List<WordSet>> observableEmitter) {
                List<WordSet> wordSets = database.getUserDAO().getWordSetByUserId(MainApp.userId);
                observableEmitter.onNext(wordSets);
            }
        }).subscribeOn(new NewThreadScheduler()).observeOn(new MainThreadScheduler()).subscribe(new OnNextObserver<List<WordSet>>() {
            @Override
            public void onNext(List<WordSet> wordSets) {
                baseViewModel.currentWordSetList.onNext(wordSets);
            }
        });
    }
}
