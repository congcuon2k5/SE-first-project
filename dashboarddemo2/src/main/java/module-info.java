module com.example.dashboarddemo2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens com.example.dashboarddemo2 to javafx.fxml;
    exports com.example.dashboarddemo2;
}