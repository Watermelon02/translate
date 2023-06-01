package com.experiment.translate.controller;

import com.experiment.translate.ControlledStage;
import com.experiment.translate.MainApp;
import com.experiment.translate.helper.ViewController;
import com.experiment.translate.viewmodel.TranslateViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.experiment.translate.MainApp.*;

public class BaseController extends ControlledStage implements Initializable {

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
    ImageView img_vocabulary;
    @FXML
    Pane container;
    //侧边栏被选中的ImageView
    private ImageView selectedImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewController viewController = new ViewController(container);
        viewController.addChild(translateViewID, translateViewRES,new TranslateViewModel());
        viewController.addChild(reciteViewID, reciteViewRES);
        viewController.addChild(profileViewID, profileViewRES);
        viewController.addChild(profileViewID, profileViewRES);
        viewController.addChild(vocabularyViewID, vocabularyViewRes);
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
            handleImageClick(img_translate);
        });
        img_recite.setOnMouseClicked(mouseEvent -> {
            viewController.loadChild(reciteViewID);
            handleImageClick(img_recite);
        });
        img_profile.setOnMouseClicked(mouseEvent -> {
            viewController.loadChild(profileViewID);
            handleImageClick(img_profile);
        });
        img_vocabulary.setOnMouseClicked(mouseEvent -> {
            viewController.loadChild(vocabularyViewID);
            handleImageClick(img_vocabulary);
        });
    }

    private void handleImageClick(ImageView imageView) {
        if (selectedImage != null) {
            selectedImage.getStyleClass().remove("selected-image");
        }

        selectedImage = imageView;
        selectedImage.getStyleClass().add("selected-image");
    }
}