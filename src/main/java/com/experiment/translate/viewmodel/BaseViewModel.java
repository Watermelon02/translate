package com.experiment.translate.viewmodel;

import com.experiment.lib_react.observable.flow.Flow;
import com.experiment.lib_react.observable.flow.StateFlow;
import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;

import java.util.List;

public class BaseViewModel implements ViewModel {
    public final StateFlow<WordSet> currentWordSet = new StateFlow<>(null);
    //当前选中的单词
    public final Flow<Word> currentWord = new Flow<>();
    public final Flow<List<WordSet>> currentWordSetList = new Flow<>();
    @Override
    public void init() {

    }

}
