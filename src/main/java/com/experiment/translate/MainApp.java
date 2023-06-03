package com.experiment.translate;

import com.experiment.translate.viewmodel.TranslateViewModel;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public static String loginViewID = "LoginView";
    public static String loginViewRes = "login-view.fxml";

    public static String baseViewID = "BaseView";
    public static String baseViewRes = "base-view.fxml";
    public static String baseViewCss = "/css/base-view.css";
    public static String translateViewID = "TranslateView";
    public static String translateViewRES = "/translate-view.fxml";
    public static String reciteViewID = "ReciteView";
    public static String reciteViewRES = "/recite-view.fxml";
    public static String profileViewID = "ProfileView";
    public static String profileViewRES = "/profile-view.fxml";
    public static String vocabularyViewID = "VocabularyView";
    public static String vocabularyViewRes = "/vocabulary-view.fxml";

    public static StageController stageController;
    public static long userId = 1;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //新建一个StageController控制器
        stageController = new StageController();

        //将主舞台交给控制器处理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        //加载登录stage，每个界面一个舞台
        stageController.loadStage(loginViewID, loginViewRes, StageStyle.UNDECORATED);

        //显示loginView舞台
        stageController.setStage(loginViewID);
    }


    public static void main(String[] args) {
        launch(args);
    }
}