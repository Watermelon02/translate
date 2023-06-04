package com.experiment.translate.customview;

import javafx.scene.control.DialogPane;
import javafx.scene.control.TextInputDialog;

public class AddWordDialog extends TextInputDialog {

    public AddWordDialog(AddWordHandler addWordHandler) {
        AddWordView addWordView = new AddWordView();
        // 创建自定义的 DialogPane 对象
        DialogPane customDialogPane = new DialogPane();
        customDialogPane.setContent(addWordView);
        // 创建对话框并设置自定义的 DialogPane
        setDialogPane(customDialogPane);
        // 设置按钮的事件处理程序
        addWordView.getAddButton().setOnMouseClicked(event -> {
            // 获取输入框的值
            String inputValue = addWordView.getWordInput().getText();
            // 将值传递给 Controller 进行其他处理
            processInput(inputValue, addWordHandler);
            // 关闭对话框
            getDialogPane().getScene().getWindow().hide();
        });
        addWordView.getCloseButton().setOnMouseClicked(event -> {
            getDialogPane().getScene().getWindow().hide();
        });
    }



    // 定义一个函数式接口
    @FunctionalInterface
    public
    interface AddWordHandler {
        void handleWord(String input);
    }

    // 定义一个方法，接收一个函数式接口作为参数
    public static void processInput(String input, AddWordHandler function) {
        // 调用函数式接口的方法
        function.handleWord(input);
    }
}
