package com.experiment.translate.controller;

import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.helper.ViewController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController extends ControlledStage implements Initializable {
    public static String translateViewID = "TranslateView";
    public static String translateViewRES = "/translate-view.fxml";
    public static String reciteViewID = "ReciteView";
    public static String reciteViewRES = "/recite-view.fxml";
    public static String profileViewID = "ProfileView";
    public static String profileViewRES = "/profile-view.fxml";
    @FXML
    ImageView img_close;
    @FXML
    ImageView img_expand;
    @FXML
    ImageView img_reduce;
    @FXML
    ImageView img_translate;
    @FXML
    ImageView img_recite;
    @FXML
    ImageView img_profile;
    @FXML
    Pane container;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewController viewController = new ViewController(container);
        viewController.addChild(translateViewID,translateViewRES);
        viewController.addChild(reciteViewID,reciteViewRES);
        viewController.addChild(profileViewID,profileViewRES);
        viewController.loadChild(translateViewID);
        img_close.setOnMouseClicked(mouseEvent -> {
            //关闭窗口，相当于点击窗口关闭图标
            myController.getStage(MainApp.baseViewID).close();
        });
        img_expand.setOnMouseClicked(mouseEvent -> {
            //最大化窗口，相当于点击窗口最大化图标
            myController.getStage(MainApp.baseViewID).setMaximized(true);
        });
        img_reduce.setOnMouseClicked(mouseEvent -> {
            //最小化窗口，相当于点击窗口上的最小化图标
            myController.getStage(MainApp.baseViewID).setIconified(true);
        });
        img_translate.setOnMouseClicked(mouseEvent -> {
            viewController.loadChild(translateViewID);
        });
        img_recite.setOnMouseClicked(mouseEvent -> {
            viewController.loadChild(reciteViewID);
        });
        img_profile.setOnMouseClicked(mouseEvent -> {
            viewController.loadChild(profileViewID);
        });

    }


}