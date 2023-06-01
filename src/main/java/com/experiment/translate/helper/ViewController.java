package com.experiment.translate.helper;

import com.experiment.translate.viewmodel.ViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;
import java.util.HashMap;

public class ViewController {
    private Pane parent;
    //建立一个专门存储Pane的Map，用于缓存子view
    private static HashMap<String, Pane> children = new HashMap<String, Pane>();
    //建立一个专门存储viewModel的Map，用于缓存子view的viewModel
    private static HashMap<String, ViewModel> childrenViewModel = new HashMap<String, ViewModel>();

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

    // 加载child.fxml作为子节点
    public void addChild(String name, String childFXML, ViewModel viewModel){
        try {
            viewModel.init();
            childrenViewModel.put(name,viewModel);
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

    public static ViewModel getViewModel(String name){
        return childrenViewModel.get(name);
    }
}
