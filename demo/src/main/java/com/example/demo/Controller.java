package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Controller {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField wordField;

    private Map<String, String> imageWordMap;

    public void initialize() {
        imageWordMap = new HashMap<>();
        imageWordMap.put("C:\\Code\\clone\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\dog.jpg", "dog");
        imageWordMap.put("C:\\Code\\clone\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\cat.jpg", "cat");
        loadRandomImage();
    }

    private void loadRandomImage() {
        String[] imagePaths = imageWordMap.keySet().toArray(new String[0]);
        Random random = new Random();
        String randomImagePath = imagePaths[random.nextInt(imagePaths.length)];
        Image image = new Image(randomImagePath);
        imageView.setImage(image);
    }

    public void checkWord(ActionEvent actionEvent) {
        String word = wordField.getText().trim().toLowerCase();
        String imagePath = getImagePath();
        String correctWord = imageWordMap.get(imagePath).toLowerCase();
        if (word.equals(correctWord)) {
            showAlert("Correct!", "Congratulations, you got it right!");
        } else {
            showAlert("Incorrect", "Sorry, the correct word is: " + correctWord);
        }
        wordField.clear();
        loadRandomImage();
    }

    private String getImagePath() {
        Image image = imageView.getImage();
        return image.getUrl();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}