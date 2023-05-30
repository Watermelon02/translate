package com.experiment.translate.view;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
/**自定义的ImageView,当鼠标经过时变暗，鼠标移开时恢复*/
public class ImageButton extends ImageView {
    public ImageButton(){
        addImageHover();
    }
    private void addImageHover(){
        // 创建一个颜色调整效果
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(-2); // 设置亮度
        this.setOnMouseEntered(mouseEvent -> {
            this.setEffect(colorAdjust);
        });

        // 鼠标移出时恢复原始效果
        this.setOnMouseExited(mouseEvent -> {
            this.setEffect(null);
        });
    }
}
