package com.example.sedictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PracticeController extends mainSence {
    @Override
    public void quayLaiTrangChinh(MouseEvent mouseEvent) throws IOException {
        super.quayLaiTrangChinh(mouseEvent);
    }

    @Override
    public void chuyenSangThemBotTu(ActionEvent actionEvent) throws IOException {
        super.chuyenSangThemBotTu(actionEvent);
    }

    @Override
    public void chuyenSangTranslate(ActionEvent actionEvent) throws IOException {
        super.chuyenSangTranslate(actionEvent);
    }

    @Override
    public void chuyenSangTraTu(ActionEvent event) throws IOException {
        super.chuyenSangTraTu(event);
    }
}
