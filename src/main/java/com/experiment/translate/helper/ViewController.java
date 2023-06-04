package com.experiment.translate.helper;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

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
        parent.getChildren().clear();
        parent.getChildren().add(0,children.get(name));
    }
}
