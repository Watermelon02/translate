package com.experiment.translate.controller;

import com.experiment.translate.ControlledStage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TranslateController extends ControlledStage implements Initializable {

    @FXML
    TextArea textInput;
    @FXML
    TextArea textOutput;
    @FXML
    VBox translate_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textOutput.setPromptText("");
        translate_btn.setOnMouseClicked(mouseEvent -> {

        });
    }
}