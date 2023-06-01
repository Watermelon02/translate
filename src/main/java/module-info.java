module com.experiment.translate {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.google.gson;

    opens com.experiment.translate to javafx.fxml;
    exports com.experiment.translate;
    exports com.experiment.translate.controller;
    opens com.experiment.translate.controller to javafx.fxml;
    exports com.experiment.lib_connect;
    opens com.experiment.lib_connect to javafx.fxml;
    exports com.experiment.translate.customview;
    opens com.experiment.translate.customview to javafx.fxml;
    //调整模块的访问控制：根据异常提示，似乎是因为模块之间的访问控制导致的问题。可以在module-info.java文件中将需要访问com.experiment.translate.repository.bean的模块添加到exports列表中
    //解决Gson解析问题
    opens com.experiment.translate.repository.bean to com.google.gson;
    exports com.experiment.translate.repository.bean to com.google.gson;
}