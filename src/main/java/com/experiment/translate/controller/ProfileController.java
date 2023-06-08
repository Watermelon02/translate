package com.experiment.translate.controller;

import com.experiment.translate.ControlledStage;
import com.experiment.translate.repository.bean.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfileController extends ControlledStage implements Initializable {
    @FXML
    private ImageView profileImage;
    @FXML
    private Text text_name;
    @FXML
    private ImageView nameImage;
    @FXML
    private Text text_level;
    @FXML
    private Text text_days;
    @FXML
    private Text text_vocabulary;
    @FXML
    private Text text_id;







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        profileImage.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择头像文件");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("图像文件", "*.png", "*.jpg", "*.jpeg"));
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                try {
                    String selectedFilePath = selectedFile.getAbsolutePath();
                    Image newImage = new Image(selectedFilePath);
                    updateImage(newImage);
                }catch (Exception e){
                    e.printStackTrace();
                }

                // 用户选择了头像文件，可以进行后续操作
            }

        });
        nameImage.setOnMouseClicked(mouseEvent -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("更换昵称");
            dialog.setHeaderText("请输入新的昵称：");
            Optional<String> result = dialog.showAndWait();

// 检查用户是否输入了新的昵称并处理结果
            if (result.isPresent()) {
                String newNickname = result.get();
                // 执行昵称更新操作
                updateNickname(newNickname);
            }

        });

    }
    private void updateNickname(String newNickname) {
        // 将新昵称应用于用户对象或数据模型
        User user = new User();
        user.setName(newNickname);

        // 更新界面上显示昵称的元素
        text_name.setText(newNickname);
    }
    private void updateImage(Image newImage) {
        // 将新昵称应用于用户对象或数据模型
        User user = new User();
        user.setImage(newImage);

        // 更新界面上显示昵称的元素
        profileImage.setImage(newImage);
    }



}