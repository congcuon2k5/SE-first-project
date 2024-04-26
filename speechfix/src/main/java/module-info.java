module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires jsapi;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}