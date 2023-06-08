package com.experiment.translate.controller;

import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.MainApp;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.util.ViewModelController;
import com.experiment.translate.viewmodel.TranslateViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class WordController implements Initializable {
    @FXML
    Text word_text_word_id;
    @FXML
    Text word_text_word_phonetic;
    @FXML
    Text word_text_word_explanation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateViewModel translateViewModel = (TranslateViewModel) ViewModelController.getInstance().getViewModel(MainApp.translateViewID);
        translateViewModel.toText.subscribe(new OnNextObserver<YoudaoTranslationResponse>() {
            @Override
            public void onNext(YoudaoTranslationResponse response) {
                word_text_word_id.setText(response.getQuery());
                word_text_word_phonetic.setText(response.getBasic().getPhonetic());
                StringBuilder sb = new StringBuilder();
                for (String str: response.getBasic().getExplains()){
                    sb.append(str).append("\n");
                }
                word_text_word_explanation.setText(sb.toString());
            }
        });
    }
}
