module com.example.restaurante.restaurante {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires java.desktop;

    opens com.example.restaurante to javafx.fxml;
    exports com.example.restaurante;
    exports com.example.restaurante.dao;
    opens com.example.restaurante.dao to javafx.fxml;
    exports com.example.restaurante.model;
    opens com.example.restaurante.model to javafx.fxml;
}