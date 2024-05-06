package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;



public class Controller {

    @FXML
    private ImageView imageView;

    @FXML
    private TextField wordField;

    private Map<String, String> imageWordMap;
    private List<String> displayedImages = new ArrayList<>();

    public void initialize() {
        imageWordMap = new HashMap<>();

        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\zebra.jpg", "zebra");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\cat.jpg", "cat");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\rabbit.jpg", "rabbit");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\camel.jpg", "camel");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\sheep.jpg", "sheep");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\cow.jpg", "cow");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\duck.jpg", "duck");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\parrot.jpg", "parrot");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\lizard.jpg", "lizard");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\animal\\giraffe.jpg", "giraffe");

        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\guitar.jpg", "guitar");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\piano.jpg", "piano");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\violin.jpg", "violin");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\drums.jpg", "drums");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\flute.jpg", "flute");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\saxophone.jpg", "saxophone");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\cymbals.jpg", "cymbals");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\cello.jpg", "cello");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\harp.jpg", "harp");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\musical instrument\\maracas.jpg", "maracas");

        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\square.jpg", "square");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\rectangle.jpg", "rectangle");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\trapezoid.jpg", "trapezoid");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\parallelogram.jpg", "parallelogram");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\triangle.jpg", "triangle");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\lozenge.jpg", "lozenge");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\circle.jpg", "circle");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\oval.jpg", "oval");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\crescent.jpg", "crescent");
        imageWordMap.put("C:\\Code\\SE-first-project\\demo\\src\\main\\resources\\com\\example\\demo\\Image\\colors and shapes\\cone.jpg", "cone");

        loadRandomImage();

        wordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                checkWord(null);
            }
        });
    }

    private void loadRandomImage() {
        String[] imagePaths = imageWordMap.keySet().toArray(new String[0]);

        List<String> remainingImages = new ArrayList<>();
        for (String path : imagePaths) {
            if (!displayedImages.contains(path)) {
                remainingImages.add(path);
            }
        }

        if (!remainingImages.isEmpty()) {
            Random random = new Random();
            String randomImagePath = remainingImages.get(random.nextInt(remainingImages.size()));
            Image image = new Image(randomImagePath);
            imageView.setImage(image);

            displayedImages.add(randomImagePath);
        } else {
            showAlert("Info", "You have completed the game!");
        }
    }


    public void checkWord(ActionEvent actionEvent) {
        String word = wordField.getText().trim().toLowerCase();
        String imagePath = getImagePath();
        String correctWord = imageWordMap.get(imagePath).toLowerCase();
        if (word.equals(correctWord)) {
            showAlert("Correct!", "Congratulations, you got it right!");
        } else {
            showAlert("Incorrect!", "Sorry, the correct word is: " + correctWord);
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