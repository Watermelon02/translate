package com.experiment.translate.helper;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;

public class ViewController {
    private Pane parent;
    //建立一个专门存储Pane的Map，用于缓存子view
    private static final HashMap<String, Pane> children = new HashMap<String, Pane>();

    private ViewController(){}
    public ViewController(Pane parent){
        this.parent = parent;
    }

    // 加载child.fxml作为子节点
    public void addChild(String name,String childFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(childFXML));
            Pane child = loader.load();
            children.put(name,child);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadChild(String name) {
        if (parent.getChildren().size()>0){
            FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.25), parent);
            // 设置初始不透明度和目标不透明度
            fadeTransition.setFromValue(1.0);
            fadeTransition.setToValue(0.0);

            // 设置动画结束后恢复初始状态
            fadeTransition.setOnFinished(event -> {
                parent.getChildren().clear();
                parent.getChildren().add(0,children.get(name));
                FadeTransition appearTransition = new FadeTransition(Duration.seconds(0.25), parent);
                // 设置初始不透明度和目标不透明度
                appearTransition.setFromValue(0);
                appearTransition.setToValue(1);
                appearTransition.play();
            });
            fadeTransition.play();
        }else {
            parent.getChildren().add(0,children.get(name));
        }

    }
}
