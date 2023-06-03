package com.experiment.translate.controller;

import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

import static com.experiment.translate.MainApp.*;

public class LoginController extends ControlledStage implements Initializable {
    @FXML
    TextField input_account;
    @FXML
    PasswordField input_password;
    @FXML
    ImageView img_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_account.setPromptText("请输入账号");
        input_password.setPromptText("请输入密码");
        img_login.setOnMouseClicked(mouseEvent -> {
            MainApp.stageController.loadStage(baseViewID, baseViewRes, baseViewCss, StageStyle.UNDECORATED);
            myController.setStage(baseViewID);
            myController.getStage(MainApp.loginViewID).close();
        });
    }
}