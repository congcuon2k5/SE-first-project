package com.example.sedictionary;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class TranslateController extends mainSence {
    @Override
    public void chuyenSangTraTu(ActionEvent event) throws IOException {
        super.chuyenSangTraTu(event);
    }

    @Override
    public void chuyenSangThemBotTu(ActionEvent actionEvent) throws IOException {
        super.chuyenSangThemBotTu(actionEvent);
    }

    @Override
    public void quayLaiTrangChinh(MouseEvent mouseEvent) throws IOException {
        super.quayLaiTrangChinh(mouseEvent);
    }

    @FXML
    private TextArea search;
    @FXML
    private TextArea translationResult;
    @FXML
    private Label tiengAnh;
    @FXML
    private Label tiengViet;
    private String a = "en";
    private String b = "vi";


    public String translate(String target) throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-translate113.p.rapidapi.com/api/v1/translator/text"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "e54d2f58c1msh485809fba2c35e2p190c06jsnac0dee6572dd")
                .header("X-RapidAPI-Host", "google-translate113.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("from=" + a + "&to=" + b + "&text=" + target))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.body());
        return (String) jsonObject.get("trans");
    }

    public void trans() throws IOException, ParseException, InterruptedException {
        String text = search.getText();
        String translationResultt = translate(text);
        translationResult.clear();
        translationResult.setText(translationResultt);
    }

    public void keyPressed(KeyEvent keyEvent) throws IOException, ParseException, InterruptedException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            trans();
        }
    }

    public void translateButtonClicked(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        trans();
    }
    public void swich(ActionEvent actionEvent) {
        String s = a;
        a = b;
        b = s;
        String a1 = search.getText();
        String b1 = translationResult.getText();
        search.setText(b1);
        translationResult.setText(a1);
        String a2 = tiengAnh.getText();
        String b2 = tiengViet.getText();
        tiengAnh.setText(b2);
        tiengViet.setText(a2);
    }
}