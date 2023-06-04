package com.experiment.translate.customview;

import com.experiment.translate.repository.bean.Word;
import javafx.scene.control.ListCell;

// 自定义的 ListCell
public class WordListCell extends ListCell<Word> {
    @Override
    protected void updateItem(Word item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            // 当子项为空或者没有内容时，设置为空内容
            setText(null);
            setGraphic(null);
        } else {
            // 自定义子项的外观和内容
            setText(item.getWord_id());
            getStyleClass().add("list-view-item");
//            setStyle("-fx-background-color: white"); // 设置样式
        }
    }
}
