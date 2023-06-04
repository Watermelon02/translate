package com.experiment.translate.controller;

import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.customview.AddWordDialog;
import com.experiment.translate.customview.WordListCell;
import com.experiment.translate.helper.ViewModelController;
import com.experiment.translate.repository.bean.Word;
import com.experiment.translate.repository.bean.WordSet;
import com.experiment.translate.viewmodel.VocabularyViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class VocabularyController extends ControlledStage implements Initializable {
    @FXML
    ChoiceBox<String> vocabulary_word_set_choice;
    @FXML
    TextFlow text_near_synonym;

    @FXML
    ListView<Word> word_list;

    @FXML
    Text vocabulary_text_word_id;
    @FXML
    Text vocabulary_text_word_phonetic;
    @FXML
    TextField vocabulary_text_word_explanation;

    @FXML
    Pane btn_next;
    @FXML
    Pane btn_last;
    @FXML
    Pane btn_add;
    @FXML
    Pane btn_remove;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initWordListView(word_list);
        VocabularyViewModel viewModel = (VocabularyViewModel) ViewModelController.getInstance().getViewModel(MainApp.vocabularyViewID);
        //界面初始化时获取要背的单词列表信息
        viewModel.wordSetList.subscribe(new OnNextObserver<List<WordSet>>() {
            @Override
            public void onNext(List<WordSet> wordSets) {
                handleWordSetListChange(wordSets, viewModel);
            }
        });
        viewModel.currentWord.subscribe(new OnNextObserver<Word>() {
            @Override
            public void onNext(Word word) {
                handleWordChange(word);
            }
        });
        viewModel.currentWordSet.subscribe(new OnNextObserver<WordSet>() {
            @Override
            public void onNext(WordSet wordSet) {
                handleWordSetChange(wordSet, viewModel);
            }
        });
        viewModel.fetchWordSet();
    }

    private void initWordListView(ListView<Word> word_list) {
        word_list.setCellFactory(new Callback<ListView<Word>, ListCell<Word>>() {
            @Override
            public ListCell<Word> call(ListView<Word> listView) {
                return new WordListCell();
            }
        });
    }

    public void handleWordSetListChange(List<WordSet> wordSets, VocabularyViewModel viewModel) {
        List<String> setNames = new ArrayList<>();
        for (WordSet wordSet : wordSets) {
            setNames.add(wordSet.getSetName());
        }
        // 设置选项列表
        vocabulary_word_set_choice.setItems(FXCollections.observableArrayList(setNames));
        vocabulary_word_set_choice.setValue(wordSets.get(0).getSetName());

        // 选择项改变时的监听事件
        vocabulary_word_set_choice.setOnAction(event -> {
            for (int i = 0; i < wordSets.size(); i++) {
                WordSet wordSet = wordSets.get(i);
                if (wordSet.getSetName().equals(vocabulary_word_set_choice.getValue())) {
                    viewModel.currentWordSet.onNext(wordSet);
                }
            }
        });
        viewModel.currentWordSet.onNext(wordSets.get(0));
    }

    /**
     * 刷新ListView绑定的数据为选中的WordSet
     */
    public void handleWordSetChange(WordSet wordSet, VocabularyViewModel viewModel) {
        ObservableList<Word> wordObservableList = FXCollections.observableArrayList(wordSet.getWordList());
        word_list.setItems(wordObservableList);
        word_list.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            // 处理单词选择事件
            viewModel.currentWord.onNext(word_list.getSelectionModel().getSelectedItem());
        });
        btn_last.setOnMouseClicked(event -> {
            int selectIndex = word_list.getSelectionModel().getSelectedIndex();
            if (selectIndex - 1 >= 0) {
                word_list.getSelectionModel().select(selectIndex - 1);
            }
        });
        btn_next.setOnMouseClicked(event -> {
            int selectIndex = word_list.getSelectionModel().getSelectedIndex();
            if (selectIndex <= wordSet.getWordList().size()) {
                word_list.getSelectionModel().select(selectIndex + 1);
            }
        });
        btn_add.setOnMouseClicked(event -> {
            AddWordDialog dialog = new AddWordDialog(input -> {
                viewModel.fetchWord(input, wordSet);
            });
            dialog.showAndWait();
        });
        //点击减号按钮删除单词本中的单词
        btn_remove.setOnMouseClicked(event -> {
            int selectIndex = word_list.getSelectionModel().getSelectedIndex();
            if (selectIndex == -1) selectIndex = 1;//防止为选中时报错
            wordObservableList.remove(selectIndex);
            viewModel.removeWordFromWordSet(selectIndex,wordSet);
        });
        viewModel.newWord.subscribe(new OnNextObserver<Word>() {
            @Override
            public void onNext(Word word) {
                wordObservableList.add(word);
            }
        });
        viewModel.currentWord.onNext(wordSet.getWordList().get(0));
    }

    /**
     * 刷新显示的单词已经相关的其他视图信息
     */
    public void handleWordChange(Word word) {
        if (word != null) {
            vocabulary_text_word_id.setText(word.getWord_id());
            vocabulary_text_word_phonetic.setText(word.getBasicPhonetic());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.getExplanation().size(); i++) {
                sb.append(word.getExplanation().get(i));
                sb.append("\n");
            }
            vocabulary_text_word_explanation.setText(sb.toString());
        }
    }

}