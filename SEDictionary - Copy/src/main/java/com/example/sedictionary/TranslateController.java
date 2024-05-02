package com.example.sedictionary;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class TranslateController {
    @FXML
    private WebView meaning;
    @FXML
    private TextArea search;
    @FXML
    private TextArea translationResultTextField;
    @FXML


    public String translate(String target) throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-translate113.p.rapidapi.com/api/v1/translator/text"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "e54d2f58c1msh485809fba2c35e2p190c06jsnac0dee6572dd")
                .header("X-RapidAPI-Host", "google-translate113.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("from=en&to=vi&text=" + target))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.body());
        return (String) jsonObject.get("trans");
    }
    public String translate2(String target) throws IOException, InterruptedException, ParseException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-translate113.p.rapidapi.com/api/v1/translator/text"))
                .header("content-type", "application/x-www-form-urlencoded")
                .header("X-RapidAPI-Key", "e54d2f58c1msh485809fba2c35e2p190c06jsnac0dee6572dd")
                .header("X-RapidAPI-Host", "google-translate113.p.rapidapi.com")
                .method("POST", HttpRequest.BodyPublishers.ofString("from=en&to=vi&text=" + target))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.body());
        return (String) jsonObject.get("trans");
    }

    public void trans() throws IOException, ParseException, InterruptedException {
        String text = search.getText();
        String translationResult = translate2(text);
        translationResultTextField.clear();
        translationResultTextField.setText(translationResult);
    }

    public void keyPressed(KeyEvent keyEvent) throws IOException, ParseException, InterruptedException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            trans();
        }
    }

    public void translateButtonClicked(ActionEvent actionEvent) throws IOException, ParseException, InterruptedException {
        trans();
    }
}