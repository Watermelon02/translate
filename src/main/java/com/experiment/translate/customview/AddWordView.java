package com.experiment.translate.customview;

import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AddWordView extends VBox {
    private TextArea wordInput;
    private ImageView closeButton;
    private ImageView addButton;

    public AddWordView() {
        setPrefHeight(200.0);
        setPrefWidth(200.0);

        HBox hBox = new HBox();
        hBox.setPrefHeight(40.0);
        hBox.setPrefWidth(200.0);
        closeButton = new ImageView();
        closeButton.setFitHeight(20.0);
        closeButton.setFitWidth(23.0);
        closeButton.setPickOnBounds(true);
        closeButton.setPreserveRatio(true);
        Image closeImage = new Image(getClass().getResourceAsStream("/com.experiment.translate.img/关闭_o.png"));
        closeButton.setImage(closeImage);

        Text labelText = new Text("手动添加单词");
        labelText.setStrokeType(StrokeType.OUTSIDE);
        labelText.setStrokeWidth(0.0);
        labelText.setTextAlignment(TextAlignment.CENTER);
        labelText.setWrappingWidth(160.21630859375);
        Font labelFont = new Font(15.0);
        labelText.setFont(labelFont);

        addButton = new ImageView();
        addButton.setFitHeight(20.0);
        addButton.setFitWidth(23.0);
        addButton.setPickOnBounds(true);
        addButton.setPreserveRatio(true);
        Image addButton = new Image(getClass().getResourceAsStream("/com.experiment.translate.img/加号.png"));
        this.addButton.setImage(addButton);

        hBox.getChildren().addAll(closeButton, labelText, this.addButton);

        wordInput = new TextArea();
        wordInput.setPrefHeight(160.0);
        wordInput.setPrefWidth(200.0);
        wordInput.setPromptText("请输入新增的单词，用逗号隔开");

        getChildren().addAll(hBox, wordInput);
    }

    public ImageView getCloseButton() {
        return closeButton;
    }

    public TextArea getWordInput() {
        return wordInput;
    }

    public ImageView getAddButton() {
        return addButton;
    }
}

