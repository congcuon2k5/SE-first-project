package com.example.sedictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class manHinhChinhControler extends mainSence {
    public void chuyenSangTraTu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("traTu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void chuyenSangThemBotTu(ActionEvent actionEvent) throws IOException {
        Parent d = FXMLLoader.load(getClass().getResource("themBotTu.fxml"));
        Stage b = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(d);
        b.setScene(c);
        b.show();
    }
    public void chuyenSangTranslate(ActionEvent actionEvent) throws IOException {
        Parent a = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        Stage b = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(a);
        b.setScene(c);
        b.show();
    }
}
