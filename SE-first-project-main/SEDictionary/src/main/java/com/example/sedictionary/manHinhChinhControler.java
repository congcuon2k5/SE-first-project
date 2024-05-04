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
        Parent traTu = FXMLLoader.load(getClass().getResource("traTu.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(traTu);
        stage.setScene(scene);
        stage.show();
    }
    public void chuyenSangThemBotTu(ActionEvent actionEvent) throws IOException {
        Parent themBot = FXMLLoader.load(getClass().getResource("themBotTu.fxml"));
        Stage b = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(themBot);
        b.setScene(c);
        b.show();
    }
    public void chuyenSangTranslate(ActionEvent actionEvent) throws IOException {
        Parent Translate = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        Stage b = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(Translate);
        b.setScene(c);
        b.show();
    }
}
