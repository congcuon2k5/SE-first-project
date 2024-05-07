package com.example.sedictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.*;

public class PracticeController extends mainSence {
    @Override
    public void chuyenSangTraTu(ActionEvent event) throws IOException {
        super.chuyenSangTraTu(event);
    }

    @Override
    public void quayLaiTrangChinh(MouseEvent mouseEvent) throws IOException {
        super.quayLaiTrangChinh(mouseEvent);
    }

    @Override
    public void chuyenSangTranslate(ActionEvent actionEvent) throws IOException {
        super.chuyenSangTranslate(actionEvent);
    }

    @Override
    public void chuyenSangThemBotTu(ActionEvent actionEvent) throws IOException {
        super.chuyenSangThemBotTu(actionEvent);
    }

    @FXML
    private ImageView imageView;

    @FXML
    private TextField wordField;
    private String pathOfView;
/*
    private Map<String, String> imageWordMap = new HashMap<>();
    private List<String> displayedImages = new ArrayList<>();*/

    public void initialize() {

        imageWordMap.put("Image/animal/zebra.jpg", "zebra");
        imageWordMap.put("Image/animal/cat.jpg", "cat");
        imageWordMap.put("Image/animal/rabbit.jpg", "rabbit");
        imageWordMap.put("Image/animal/camel.jpg", "camel");
        imageWordMap.put("Image/animal/sheep.jpg", "sheep");
        imageWordMap.put("Image/animal/cow.jpg", "cow");
        imageWordMap.put("Image/animal/duck.jpg", "duck");
        imageWordMap.put("Image/animal/parrot.jpg", "parrot");
        imageWordMap.put("Image/animal/lizard.jpg", "lizard");
        imageWordMap.put("Image/animal/giraffe.jpg", "giraffe");

        imageWordMap.put("Image/musical instrument/guitar.jpg", "guitar");
        imageWordMap.put("Image/musical instrument/piano.jpg", "piano");
        imageWordMap.put("Image/musical instrument/violin.jpg", "violin");
        imageWordMap.put("Image/musical instrument/drums.jpg", "drums");
        imageWordMap.put("Image/musical instrument/flute.jpg", "flute");
        imageWordMap.put("Image/musical instrument/saxophone.jpg", "saxophone");
        imageWordMap.put("Image/musical instrument/cymbals.jpg", "cymbals");
        imageWordMap.put("Image/musical instrument/cello.jpg", "cello");
        imageWordMap.put("Image/musical instrument/harp.jpg", "harp");
        imageWordMap.put("Image/musical instrument/maracas.jpg", "maracas");

        imageWordMap.put("Image/colors and shapes/square.jpg", "square");
        imageWordMap.put("Image/colors and shapes/rectangle.jpg", "rectangle");
        imageWordMap.put("Image/colors and shapes/trapezoid.jpg", "trapezoid");
        imageWordMap.put("Image/colors and shapes/parallelogram.jpg", "parallelogram");
        imageWordMap.put("Image/colors and shapes/triangle.jpg", "triangle");
        imageWordMap.put("Image/colors and shapes/lozenge.jpg", "lozenge");
        imageWordMap.put("Image/colors and shapes/circle.jpg", "circle");
        imageWordMap.put("Image/colors and shapes/oval.jpg", "oval");
        imageWordMap.put("Image/colors and shapes/crescent.jpg", "crescent");
        imageWordMap.put("Image/colors and shapes/cone.jpg", "cone");
        loadRandomImage();
        System.out.println("please");

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
                Image image = new Image(getClass().getResourceAsStream(randomImagePath));
                pathOfView = randomImagePath;
                imageView.setImage(image);

                displayedImages.add(randomImagePath);
                System.out.println(randomImagePath);
        } else {
            showAlert("Info", "You have completed the game!");
        }
    }


    public void checkWord(ActionEvent actionEvent) {
        String word = wordField.getText().trim().toLowerCase();
        String imagePath = pathOfView;
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
