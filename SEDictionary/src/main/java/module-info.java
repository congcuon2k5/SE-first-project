module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.net.http;
    requires json.simple;
    requires javafx.web;
    requires jsapi;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.sedictionary to javafx.fxml;
    exports com.example.sedictionary;
}