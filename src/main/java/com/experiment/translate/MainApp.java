package com.experiment.translate;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public static String loginViewID = "LoginView";
    public static String loginViewRes = "login-view.fxml";

    public static String baseViewID = "BaseView";
    public static String baseViewRes = "base-view.fxml";
    public static String baseViewCss = "/css/base-view.css";


    private StageController stageController;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //新建一个StageController控制器
        stageController = new StageController();

        //将主舞台交给控制器处理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        //加载多个舞台，每个界面一个舞台
        stageController.loadStage(loginViewID, loginViewRes,StageStyle.UNDECORATED);
        stageController.loadStage(baseViewID, baseViewRes,baseViewCss,StageStyle.UNDECORATED);

        //显示MainView舞台
        stageController.setStage(loginViewID);
    }


    public static void main(String[] args) {
        launch(args);
    }
}