module com.example.gameofse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gameofse to javafx.fxml;
    exports com.example.gameofse;
}