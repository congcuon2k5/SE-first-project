package com.example.sedictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    @Override
    public void chuyenSangAbout(ActionEvent actionEvent) throws IOException {
        super.chuyenSangAbout(actionEvent);
    }

    @FXML
    private ImageView imageView;

    @FXML
    private TextField wordField;
    private String pathOfView;
    private Map<String, String> imageWordMap = new HashMap<>();
    private List<String> displayedImages = new ArrayList<>();
    private int soCauDung = 0;
    private int soCauSai = 0;

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
        //imageWordMap.put("Image/colors and shapes/rectangle.jpg", "rectangle");
        imageWordMap.put("Image/colors and shapes/trapezoid.jpg", "trapezoid");
        //imageWordMap.put("Image/colors and shapes/parallelogram.jpg", "parallelogram");
        imageWordMap.put("Image/colors and shapes/triangle.jpg", "triangle");
        imageWordMap.put("Image/colors and shapes/lozenge.jpg", "lozenge");
        imageWordMap.put("Image/colors and shapes/circle.jpg", "circle");
        imageWordMap.put("Image/colors and shapes/oval.jpg", "oval");
        imageWordMap.put("Image/colors and shapes/crescent.jpg", "crescent");
        imageWordMap.put("Image/colors and shapes/cone.jpg", "cone");
        loadRandomImage();

        wordField.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                checkWord(null);
            }
        });
        System.out.println("....");
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

        } else {
//            showAlert("Info", "You have completed the game!");
            Alert thongBaoHoanThanh = new Alert(Alert.AlertType.CONFIRMATION);
            thongBaoHoanThanh.setTitle("complete");
            thongBaoHoanThanh.setHeaderText("You have completed the game!\n" + "Number of correct answers is: " + soCauDung + "\n" + "number of incorrect answers is: " + soCauSai);
            thongBaoHoanThanh.setContentText("Do you want to play again?");
            ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("com/example/sedictionary/Picture/377208208_318558817739521_5846789676218966489_n.jpg")));

            // Thiết lập kích thước cho ImageView nếu cần
            imageView.setFitWidth(50); // Thiết lập chiều rộng tùy ý
            imageView.setFitHeight(50); // Thiết lập chiều cao tùy ý

            // Thiết lập hình ảnh tùy chỉnh cho Alert
            thongBaoHoanThanh.setGraphic(imageView);
            for (ButtonType buttonType : thongBaoHoanThanh.getButtonTypes()) {
                Button button = (Button) thongBaoHoanThanh.getDialogPane().lookupButton(buttonType);
                if (buttonType.getText().equals("OK")) {
                    // Thay đổi văn bản của nút OK
                    button.setText("Yes");
                } else if (buttonType.getText().equals("Cancel")) {
                    // Thay đổi văn bản của nút Cancel
                    button.setText("No");
                }
            }
            Optional<ButtonType> ok = thongBaoHoanThanh.showAndWait();
            if (ok.get() == ButtonType.CANCEL) {
                try {
                    quayLaiStart();
                    soCauSai = 0;
                    soCauDung = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ok.get() == ButtonType.OK){
                try {
                    choiLai();
                    soCauSai = 0;
                    soCauDung = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void quayLaiStart() throws IOException {
        Parent themBot = FXMLLoader.load(getClass().getResource("PracticeStart.fxml"));
        Stage stage = (Stage) wordField.getScene().getWindow();
        Scene scene = new Scene(themBot);
        stage.setScene(scene);
        stage.show();
    }
    public void choiLai() throws IOException {
        Parent themBot = FXMLLoader.load(getClass().getResource("Practice.fxml"));
        Stage stage = (Stage) wordField.getScene().getWindow();
        Scene scene = new Scene(themBot);
        stage.setScene(scene);
        stage.show();
    }
    public void ExitGame(ActionEvent actionEvent){
        Alert thoatGame = new Alert(Alert.AlertType.CONFIRMATION);
        thoatGame.setTitle("Warning!");
        thoatGame.setHeaderText("Do you want to exit the game?");
        Optional<ButtonType> ok = thoatGame.showAndWait();
        if (ok.get() == ButtonType.OK){
            try {
                quayLaiStart();
                soCauSai = 0;
                soCauDung = 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void checkWord(ActionEvent actionEvent) {
        String word = wordField.getText().trim().toLowerCase();
        String imagePath = pathOfView;
        String correctWord = imageWordMap.get(imagePath).toLowerCase();
        if (word.equals(correctWord)) {
            showAlert("Correct!", "Congratulations, you got it right!");
            soCauDung++;
        } else {
            showAlert("Incorrect!", "Sorry, the correct word is: " + correctWord);
            soCauSai++;
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
