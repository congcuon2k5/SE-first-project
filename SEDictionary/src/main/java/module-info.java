module com.example.sedictionary {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.sedictionary to javafx.fxml;
    exports com.example.sedictionary;
}