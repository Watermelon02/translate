package com.experiment.translate.controller;

import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.helper.ViewController;
import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;
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
    private int index = 0;
    //当前选中的单词集
    private WordSet currentWordSet;
    //当前选中的单词
    private Word currentWord;

    public void handleWordListChange(List<WordSet> wordSets) {
        List<String> setNames = new ArrayList<>();
        for (WordSet wordSet : wordSets) {
            setNames.add(wordSet.getSetName());
        }
        // 设置选项列表
        word_set_choice.setItems(FXCollections.observableArrayList(setNames));
        refreshWord(wordSets.get(0));
        // 选择项改变时的监听事件
        word_set_choice.setOnAction(event -> {
            index = 0;
            for (int i = 0; i < wordSets.size(); i++) {
                WordSet wordSet = wordSets.get(i);
                if (wordSet.getSetName().equals(word_set_choice.getValue())) {
                    btn_forget.setOpacity(1);
                    btn_remember.setOpacity(1);
                    currentWordSet = wordSet;
                    refreshWord(currentWordSet);
                }
            }
        });
        btn_remember.setOnMouseClicked(event -> {
            index++;
            if (index+1<=currentWordSet.getWordList().size()){
                refreshWord(currentWordSet);
            }else {//背完当前单词集
                text_num_of_words.setText("0");
                text_word_id.setText("Congratulations！");
                text_word_phonetic.setText("完成任务");
                text_word_explanation.setText("");
                btn_forget.setOpacity(0);
                btn_remember.setOpacity(0);
            }
        });
        btn_forget.setOnMouseClicked(event -> {
            text_word_phonetic.setText(currentWord.getBasicPhonetic());
            StringBuilder sb = new StringBuilder();
            for (int i = 0;i<currentWord.getExplanation().size();i++){
                sb.append(currentWord.getExplanation().get(i));
                sb.append(" ");
            }
            text_word_explanation.setText(sb.toString());
        });
    }

    /**
     * 刷新显示的单词已经相关的其他视图信息
     */
    public void refreshWord(WordSet wordSet) {
        int numsOfWordsLast = wordSet.getWordList().size() - index;
        currentWord = wordSet.getWordList().get(index);
        word_set_choice.setValue(wordSet.getSetName());
        text_num_of_words.setText(String.valueOf(numsOfWordsLast));
        text_word_id.setText(currentWord.getWord_id());
        text_word_phonetic.setText("");
        text_word_explanation.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ReciteViewModel viewModel = (ReciteViewModel) ViewController.getViewModel(MainApp.reciteViewID);
        //界面初始化时获取要背的单词列表信息
        viewModel.fetchWordSet().subscribe(new OnNextObserver<List<WordSet>>() {
            @Override
            public void onNext(List<WordSet> wordSets) {
                currentWordSet = wordSets.get(0);
                handleWordListChange(wordSets);
            }

            @Override
            public void onError(Throwable throwable) {
                super.onError(throwable);
                    throwable.printStackTrace();
            }
        });
    }
}