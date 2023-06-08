package com.experiment.translate.controller;

import com.experiment.lib_react.observer.OnNextObserver;
import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.repository.bean.YoudaoTranslationResponse;
import com.experiment.translate.util.ViewController;
import com.experiment.translate.util.ViewModelController;
import com.experiment.translate.viewmodel.TranslateViewModel;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;

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
    Text text_translate_mode;
    @FXML
    VBox from_btn;

    @FXML
    HBox to_btn;
    @FXML
    ChoiceBox<String> translate_choice;
    @FXML
    VBox translate_parent;

    MediaPlayer fromMediaPlayer;
    MediaPlayer toMediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TranslateViewModel vm = (TranslateViewModel) ViewModelController.getInstance().getViewModel(MainApp.translateViewID);
//        vm.toText.subscribe(new OnNextObserver<BaiduTranslationResponse>() {
//            @Override
//            public void onNext(BaiduTranslationResponse baiduTranslationResponse) {
//                System.out.println("controller:"+ baiduTranslationResponse.getFrom());
//            }
//        });
        ViewController.replaceChildIntoTranslate(translate_parent, vm.translateMode.getValue());
        translate_btn.setOnMouseClicked(event -> {
            vm.translate(text_input.getText());
        });
        from_btn.setOnMouseClicked(event -> {
            fromMediaPlayer.stop();
            if (fromMediaPlayer != null) {
                fromMediaPlayer.play();
            }
        });
        to_btn.setOnMouseClicked(event -> {
            toMediaPlayer.stop();
            if (toMediaPlayer != null) {
                toMediaPlayer.play();
            }
        });
        text_translate_mode.setOnMouseClicked(event -> {
            vm.changeTranslateMode();
        });
        // 设置选项列表
        translate_choice.setItems(FXCollections.observableArrayList(vm.translateModeList));
        translate_choice.setValue(vm.translateModeList.get(0));
        // 选择项改变时的监听事件
        translate_choice.setOnAction(event -> {
            vm.translateMode.setValue(translate_choice.getValue());
            ViewController.replaceChildIntoTranslate(translate_parent, vm.translateMode.getValue());
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
                fromMediaPlayer = new MediaPlayer(fromMedia);
                Media toMedia = new Media(youdaoTranslationResponse.getTSpeakUrl());
                toMediaPlayer = new MediaPlayer(toMedia);
//                if (vm.translateMode.getValue().equals(TranslateViewModel.PASSAGE_MODE)) {//展示文章
//                    ViewController.addChildIntoTranslate(translate_parent, vm.translateMode.getValue());
//                } else if (vm.translateMode.getValue().equals(TranslateViewModel.WORD_MODE) && youdaoTranslationResponse.getBasic() != null) {//展示单词相关文本
//                    ViewController.addChildIntoTranslate(translate_parent, vm.translateMode.getValue());
////                    YoudaoTranslationResponse.Basic basic = youdaoTranslationResponse.getBasic();
////                    StringBuilder sb = new StringBuilder();
////                    sb.append()
//                }
                text_output.setText(youdaoTranslationResponse.getTranslation().get(0));
            }
        });
        vm.translateLanguage.subscribe(new OnNextObserver<String>() {
            @Override
            public void onNext(String s) {
                text_translate_mode.setText(s);
            }
        });
    }
}