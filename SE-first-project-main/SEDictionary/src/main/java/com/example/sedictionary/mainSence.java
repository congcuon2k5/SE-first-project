package com.example.sedictionary;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class mainSence extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(mainSence.class.getResource("manHinhChinh.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("SE team");
        stage.setScene(scene);
        stage.show();

        docFileAnh();
        docFileViet();
        docFileThemTu();
    }

    public static void main(String[] args) {
        launch();
    }
    public static Map<String, String> EngData = new HashMap<>();
    public static Map<String, String> VieData = new HashMap<>();
    public static Map<String, String> listThem = new HashMap<>();
    public void docFileAnh() throws IOException {
        FileReader doc = new FileReader("data/E_V.txt");
        BufferedReader br = new BufferedReader(doc);
        String dong;
        while ((dong = br.readLine()) != null) {
            String[] parts = dong.split("<html>");
            EngData.put(parts[0], parts[1]);
        }
    }
    public void docFileViet() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("data/V_E.txt"));
        String dong;
        while ((dong = br.readLine()) != null) {
            String[] parts = dong.split("<html>");
            VieData.put(parts[0], parts[1]);
        }
    }
    public void docFileThemTu() throws IOException {
        FileReader themBotTu = new FileReader("data/ThemTu.txt");
        BufferedReader br = new BufferedReader(themBotTu);
        String dong;
        while ((dong = br.readLine()) != null){
            String[] part = dong.split("<html>");
            listThem.put(part[0], part[1]);
        }
    }
}
