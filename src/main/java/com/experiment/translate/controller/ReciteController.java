package com.experiment.translate.controller;

import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.util.ViewModelController;
import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;
import com.experiment.translate.viewmodel.BaseViewModel;
import com.experiment.translate.viewmodel.ReciteViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ReciteController extends ControlledStage implements Initializable {
    @FXML
    ChoiceBox<String> word_set_choice;
    @FXML
    Text text_num_of_words;
    @FXML
    Text text_word_id;
    @FXML
    Text text_word_phonetic;
    @FXML
    Text text_word_explanation;
    @FXML
    VBox btn_remember;
    @FXML
    VBox btn_forget;
    private ReciteViewModel reciteViewModel;
    private BaseViewModel baseViewModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reciteViewModel = (ReciteViewModel) ViewModelController.getInstance().getViewModel(MainApp.reciteViewID);
        baseViewModel = (BaseViewModel) ViewModelController.getInstance().getViewModel(MainApp.baseViewID);

        //界面初始化时获取要背的单词列表信息
        reciteViewModel.fetchWordSetList();
        baseViewModel.currentWordSetList.subscribe(new OnNextObserver<List<WordSet>>() {
            @Override
            public void onNext(List<WordSet> wordSets) {
                handleWordListChange(wordSets);
            }
        });
        baseViewModel.currentWordSet.subscribe(new OnNextObserver<WordSet>() {
            @Override
            public void onNext(WordSet wordSet) {
                handleWordSetChange(wordSet);
            }
        });
        baseViewModel.currentWord.subscribe(new OnNextObserver<Word>() {
            @Override
            public void onNext(Word word) {
                handleWordChange(word);
            }
        });
        reciteViewModel.index.subscribe(new OnNextObserver<Integer>() {
            @Override
            public void onNext(Integer index) {
                int totalWordNum = baseViewModel.currentWordSet.getValue().getWordList().size();
                text_num_of_words.setText(String.valueOf(totalWordNum - index-1));
            }
        });
    }

    public void handleWordListChange(List<WordSet> wordSets) {
        List<String> setNames = new ArrayList<>();
        for (WordSet wordSet : wordSets) {
            setNames.add(wordSet.getSetName());
        }
        word_set_choice.setValue(wordSets.get(0).getSetName());
        // 设置选项列表
        word_set_choice.setItems(FXCollections.observableArrayList(setNames));
        // 选择项改变时的监听事件
        word_set_choice.setOnAction(event -> {
            for (int i = 0; i < wordSets.size(); i++) {
                WordSet wordSet = wordSets.get(i);
                if (wordSet.getSetName().equals(word_set_choice.getValue())) {
                    baseViewModel.currentWordSet.onNext(wordSet);
                }
            }
        });
        baseViewModel.currentWordSet.onNext(wordSets.get(0));
    }

    public void handleWordSetChange(WordSet wordSet) {
        reciteViewModel.index.setValue(0);
        word_set_choice.setValue(wordSet.getSetName());
        btn_remember.setOnMouseClicked(event -> {
            int index = reciteViewModel.index.getValue();
            if (index + 1 < wordSet.getWordList().size()) {
                reciteViewModel.index.setValue(index + 1);
                baseViewModel.currentWord.onNext(wordSet.getWordList().get(index + 1));
            } else {//背完当前单词集
                text_word_id.setText("Congratulations！");
                text_word_phonetic.setText("完成任务");
                text_word_explanation.setText("");
                btn_forget.setOpacity(0);
                btn_remember.setOpacity(0);
            }
        });
        btn_forget.setOpacity(1);
        btn_remember.setOpacity(1);
        baseViewModel.currentWord.onNext(wordSet.getWordList().get(reciteViewModel.index.getValue()));
    }

    /**
     * 刷新显示的单词已经相关的其他视图信息
     */
    public void handleWordChange(Word word) {
        if (word!=null){
            text_word_id.setText(word.getWord_id());
            text_word_phonetic.setText("");
            text_word_explanation.setText("");
            btn_forget.setOnMouseClicked(event -> {
                text_word_phonetic.setText(word.getBasicPhonetic());
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < word.getExplanation().size(); i++) {
                    sb.append(word.getExplanation().get(i));
                    sb.append(" ");
                }
                text_word_explanation.setText(sb.toString());
            });
        }
    }
}