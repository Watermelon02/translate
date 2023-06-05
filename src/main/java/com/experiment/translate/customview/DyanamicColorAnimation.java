package com.experiment.translate.customview;

import javafx.animation.Transition;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

public class DyanamicColorAnimation extends Transition {

    private Region target;
    private Color startColor;
    private Color endColor;

    public DyanamicColorAnimation(Duration duration, Region target, Color startColor, Color endColor) {
        setCycleDuration(duration);
        this.target = target;
        this.startColor = startColor;
        this.endColor = endColor;
    }

    @Override
    protected void interpolate(double fraction) {
        // 更新目标对象的属性
        Color color1 = calculateColor(startColor, fraction/2+0.5);
        Color color2 = calculateColor(endColor, fraction/2+0.5);

        Background background = createBackground(color1, color2);
        target.setBackground(background);
    }

    private Color calculateColor(Color color, double fraction) {
        double red = color.getRed() * fraction;
        double green = color.getGreen() * fraction;
        double blue = color.getBlue() * fraction;
        double opacity = color.getOpacity() * fraction;

        return Color.color(red, green, blue, opacity);
    }

    private Background createBackground(Color color1, Color color2) {
        return Background.fill(new LinearGradient(
                0, 0,          // 起点坐标
                1, 1,          // 终点坐标
                true,         // 使用比例
                CycleMethod.NO_CYCLE, // 不循环
                new Stop(0, color1), // 起点位置和颜色
                new Stop(1, color2) // 终点位置和颜色
        ));
    }
}



