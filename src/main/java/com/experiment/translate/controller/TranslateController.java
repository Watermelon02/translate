package com.experiment.translate.controller;

import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.helper.ViewController;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.viewmodel.TranslateViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends ControlledStage implements Initializable {

    @FXML
    TextArea text_input;
    @FXML
    TextArea text_output;
    @FXML
    VBox translate_btn;

    @FXML
    VBox from_btn;

    @FXML
    VBox to_btn;

    MediaView fromMediaView;
    MediaView toMediaView;
    MediaPlayer fromMediaPlayer;
    MediaPlayer toMediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateViewModel vm = (TranslateViewModel) ViewController.getViewModel(MainApp.translateViewID);
//        vm.toText.subscribe(new OnNextObserver<BaiduTranslationResponse>() {
//            @Override
//            public void onNext(BaiduTranslationResponse baiduTranslationResponse) {
//                System.out.println("controller:"+ baiduTranslationResponse.getFrom());
//            }
//        });
        translate_btn.setOnMouseClicked(event -> {
            vm.translate(text_input.getText());
        });
        from_btn.setOnMouseClicked(event -> {
            if (fromMediaPlayer != null) {
                System.out.println("play");
                fromMediaPlayer.play();
            }
        });
        to_btn.setOnMouseClicked(event -> {
            if (toMediaPlayer != null) {
                toMediaPlayer.play();
            }
        });
        vm.toText.subscribe(new OnNextObserver<YoudaoTranslationResponse>() {
            @Override
            public void onNext(YoudaoTranslationResponse youdaoTranslationResponse) {
                if (fromMediaPlayer != null) {
                    fromMediaPlayer.stop();
                }
                if (toMediaPlayer != null) {
                    toMediaPlayer.stop();
                }
                Media fromMedia = new Media(youdaoTranslationResponse.getSpeakUrl());
                System.out.println(youdaoTranslationResponse.getSpeakUrl());
                fromMediaPlayer = new MediaPlayer(fromMedia);
                fromMediaPlayer.play();
                Media toMedia = new Media(youdaoTranslationResponse.getTSpeakUrl());
                toMediaPlayer = new MediaPlayer(toMedia);
                text_output.setText(youdaoTranslationResponse.getTranslation().get(0));
            }
        });
    }
}