module com.experiment.translate {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.experiment.translate to javafx.fxml;
    exports com.experiment.translate;
    exports com.experiment.translate.controller;
    opens com.experiment.translate.controller to javafx.fxml;
    exports com.experiment.translate.connect;
    opens com.experiment.translate.connect to javafx.fxml;
    exports com.experiment.translate.customview;
    opens com.experiment.translate.customview to javafx.fxml;
}