package com.experiment.translate.controller;

import com.experiment.lib_react.disposable.Disposable;
import com.experiment.lib_react.observer.Observer;
import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.helper.ViewController;
import com.experiment.translate.repository.bean.TranslationResponse;
import com.experiment.translate.viewmodel.TranslateViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends ControlledStage implements Initializable {

    @FXML
    TextArea text_input;
    @FXML
    TextArea text_output;
    @FXML
    VBox translate_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        text_output.setPromptText("3434234324");
        TranslateViewModel vm = (TranslateViewModel)ViewController.getViewModel(MainApp.translateViewID);
        vm.toText.subscribe(new OnNextObserver<TranslationResponse>() {
            @Override
            public void onNext(TranslationResponse translationResponse) {
                System.out.println("controller:"+translationResponse.getFrom());
            }
        });
        translate_btn.setOnMouseClicked(event -> {
            vm.translate(text_input.getText());
        });
    }
}