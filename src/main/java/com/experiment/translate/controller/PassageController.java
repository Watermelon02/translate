package com.experiment.translate.controller;

import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.MainApp;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.util.ViewModelController;
import com.experiment.translate.viewmodel.TranslateViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class PassageController implements Initializable {
    @FXML
    TextArea passage_text_input;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TranslateViewModel translateViewModel = (TranslateViewModel) ViewModelController.getInstance().getViewModel(MainApp.translateViewID);
        translateViewModel.toText.subscribe(new OnNextObserver<YoudaoTranslationResponse>() {
            @Override
            public void onNext(YoudaoTranslationResponse youdaoTranslationResponse) {
                StringBuilder builder = new StringBuilder();
                for (String str : youdaoTranslationResponse.getTranslation()) {
                    builder.append(str).append("\n");
                }
                passage_text_input.setText(builder.toString());
            }
        });

    }
}
