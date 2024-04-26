module com.example.demospeech {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.demospeech to javafx.fxml;
    exports com.example.demospeech;
}