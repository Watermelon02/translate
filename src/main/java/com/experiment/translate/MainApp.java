package com.experiment.translate;
/**
 * Created by CatScan on 2016/6/19.
 */

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {
    public static String loginViewID = "LoginView";
    public static String loginViewRes = "login-view.fxml";

    public static String baseViewID = "BaseView";
    public static String baseViewRes = "base-view.fxml";

    private StageController stageController;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        //新建一个StageController控制器
        stageController = new StageController();

        //将主舞台交给控制器处理
        stageController.setPrimaryStage("primaryStage", primaryStage);

        //加载多个舞台，每个界面一个舞台
        stageController.loadStage(loginViewID, loginViewRes);
        stageController.loadStage(baseViewID, baseViewRes, StageStyle.UNDECORATED);
        //显示MainView舞台
        stageController.setStage(loginViewID);
    }


    public static void main(String[] args) {
        launch(args);
    }
}