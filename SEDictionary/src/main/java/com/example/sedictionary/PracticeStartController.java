package com.example.sedictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PracticeStartController extends mainSence {
    @FXML
    private Button start;

    @Override
    public void chuyenSangAbout(ActionEvent actionEvent) throws IOException {
        super.chuyenSangAbout(actionEvent);
    }

    @Override
    public void quayLaiTrangChinh(MouseEvent mouseEvent) throws IOException {
        super.quayLaiTrangChinh(mouseEvent);
    }

    @Override
    public void chuyenSangTraTu(ActionEvent event) throws IOException {
        super.chuyenSangTraTu(event);
    }

    @Override
    public void chuyenSangThemBotTu(ActionEvent actionEvent) throws IOException {
        super.chuyenSangThemBotTu(actionEvent);
    }

    @Override
    public void chuyenSangTranslate(ActionEvent actionEvent) throws IOException {
        super.chuyenSangTranslate(actionEvent);
    }
    public void start(ActionEvent actionEvent) throws IOException {
        Parent startGame = FXMLLoader.load(getClass().getResource("Practice.fxml"));
        Stage b = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(startGame);
        b.setScene(c);
        b.show();
    }
}
