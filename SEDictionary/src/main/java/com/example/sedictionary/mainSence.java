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
        docFile();
    }

    public static void main(String[] args) {
        launch();
    }
    @FXML
    ListView<String> danhSachTuDuocThem;
    @FXML
    WebView nghiamoi;
    @FXML
    private ListView<String> listView;
    public static Map<String, String> EngData = new HashMap<>();
    public static Map<String, String> VieData = new HashMap<>();
    public void docFileAnh() throws IOException {
        FileReader doc = new FileReader("data/E_V.txt");
        BufferedReader br = new BufferedReader(doc);
        String dong;
        while ((dong = br.readLine()) != null) {
            String[] parts = dong.split("<html>");
//            String tu = parts[0];
//            String nghia = "<html>" + parts[1];
            EngData.put(parts[0], parts[1]);
        }
    }
    public void docFileViet() throws IOException {
        FileReader doc = new FileReader("data/V_E.txt");
        BufferedReader br = new BufferedReader(doc);
        String dong;
        while ((dong = br.readLine()) != null) {
            String[] parts = dong.split("<html>");
            String tu = parts[0];
            String nghia = "<html>" + parts[1];
            VieData.put(parts[0], parts[1]);
        }
    }
    public static Map<String, String> listThem = new HashMap<>();
    public void docFile() throws IOException {
        FileReader fr = new FileReader("data/testThemTu.txt");
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null){
            String[] part = line.split("<html>");
            listThem.put(part[0], part[1]);
        }
    }
}
