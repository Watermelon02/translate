package com.experiment.translate.controller;

import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.customview.DyanamicColorAnimation;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

import static com.experiment.translate.MainApp.*;

public class LoginController extends ControlledStage implements Initializable {
    @FXML
    TextField input_account;
    @FXML
    PasswordField input_password;
    @FXML
    VBox img_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        input_account.setPromptText("请输入账号");
        input_password.setPromptText("请输入密码");
        img_login.setOnMouseClicked(mouseEvent -> {
            MainApp.stageController.loadStage(baseViewID, baseViewRes, baseViewCss, StageStyle.UNDECORATED);
            myController.setStage(baseViewID);
            myController.getStage(MainApp.loginViewID).close();
        });
        img_login.setClip(new Circle(img_login.getPrefWidth()/2,img_login.getPrefHeight()/2,30));
        DyanamicColorAnimation animation = new DyanamicColorAnimation(Duration.seconds(4), img_login, Color.valueOf("#A6FFCB"), Color.valueOf("#1FA2FF"));
        animation.setCycleCount(Animation.INDEFINITE);
        animation.setAutoReverse(true);
        // 启动动画
        animation.play();
    }


}