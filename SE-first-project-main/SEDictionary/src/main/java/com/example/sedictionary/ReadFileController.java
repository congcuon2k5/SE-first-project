package com.example.sedictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.simple.parser.ParseException;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.net.URL;

public class ReadFileController extends mainSence {
    public void quayLaiTrangChinh(ActionEvent actionEvent) throws IOException {
        Parent manHinhChinh = FXMLLoader.load(getClass().getResource("manHinhChinh.fxml"));
        Stage b = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(manHinhChinh);
        b.setScene(c);
        b.show();
    }

    @FXML
    private ListView<String> listTuCanTra;
    @FXML
    private WebView nghiaCuaTu;
    @FXML
    private TextField tuCanTra;
    private String tuDaTra;
    private Map<String, String> listTuDaTra = new HashMap<>();

    /**
     * hàm gán vào button để tìm từ có trong textfield.
     * @param actionEvent an
     */
    public void search(ActionEvent actionEvent) {
        String a = tuCanTra.getText();
        if (EngData.get(a) != null) {
            String b = EngData.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
            listTuDaTra.put(a, b);
        } else if (VieData.get(a) != null) {
            String b = VieData.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
            listTuDaTra.put(a, b);
        } else if (listThem.get(a) != null) {
            String b = listThem.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
            listTuDaTra.put(a, b);
        }
        else {
            thongBaoNhapSaiTu(actionEvent);
        }
        tuDaTra = a;
    }

    /**
     * hàm giống hàm trên nhma chỉ cần enter khong cần click chuột.
     * @param keyEvent an
     * @throws IOException
     * @throws ParseException
     * @throws InterruptedException
     */
    public void nhanEnter(KeyEvent keyEvent) throws IOException, ParseException, InterruptedException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ActionEvent a = new ActionEvent();
            search(a);
        }
        tuDaTra = tuCanTra.getText();
    }

    /**
     * hàm để gợi ý từ từ trong textfield vào listview và có cả những từ đã tra.
     * @param keyEvent an
     */
    public void goiYTu(KeyEvent keyEvent) {
        String b = tuCanTra.getText();
        if (b.isEmpty()) {
            listTuCanTra.getItems().clear();
            List<String> cacTuDaTra = (List<String>) listTuDaTra.keySet().stream().filter(listTuDaTra -> listTuDaTra.startsWith("")).collect(Collectors.toList());
            listTuCanTra.getItems().addAll(cacTuDaTra);
        } else {
            listTuCanTra.getItems().clear();
            List<String> collect = (List<String>) EngData.keySet().stream().filter(EngData -> EngData.startsWith(b)).collect(Collectors.toList());
            List<String> collect1 = (List<String>) VieData.keySet().stream().filter(VieData -> VieData.startsWith(b)).collect(Collectors.toList());
            List<String> collect2 = (List<String>) listThem.keySet().stream().filter(listThem -> listThem.startsWith(b)).collect(Collectors.toList());
            listTuCanTra.getItems().addAll(collect);
            listTuCanTra.getItems().addAll(collect1);
            listTuCanTra.getItems().addAll(collect2);
            tuDaTra = b;
        }
    }

    /**
     * hàm để khi chọn chuột vào từ trên listview thì sẽ hiện nghĩa luôn.
     * @param mouseEvent an
     */
    public void chonTuBangChuot(MouseEvent mouseEvent) {
        listTuCanTra.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    tuDaTra = newValue;
                    if (EngData.get(newValue.trim()) != null) {
                        String selectedWord = EngData.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                        listTuDaTra.put(tuDaTra, EngData.get(tuDaTra) );
                    } else if (VieData.get(newValue.trim()) != null) {
                        String selectedWord = VieData.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                        listTuDaTra.put(tuDaTra, EngData.get(tuDaTra) );
                    } else if (listThem.get(newValue.trim()) != null) {
                        String selectedWord = listThem.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                        listTuDaTra.put(tuDaTra, EngData.get(tuDaTra) );
                    }
                }
        );
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void DoSpeech(ActionEvent event) {
        try {
            String word = tuDaTra;
            // Set property as Kevin Dictionary
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");

            // Register Engine
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");

            // Create a Synthesizer
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));

            // Allocate synthesizer
            synthesizer.allocate();

            // Resume Synthesizer
            synthesizer.resume();

            // Speaks the given text
            // until the queue is empty.
            synthesizer.speakPlainText(
                    word, null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);

            // Deallocate the Synthesizer.
            //synthesizer.deallocate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * hàm để tạo alert khi nhập từ không cso trong từ điển.
     * @param actionEvent an
     */
    public void thongBaoNhapSaiTu(ActionEvent actionEvent) {
        Alert thongBao = new Alert(Alert.AlertType.CONFIRMATION);
        thongBao.setTitle("The entered word is invalid");
        thongBao.setHeaderText("The entered word is invalid");
        thongBao.setContentText("Do you want to add new word to your dictionary?");

        ButtonType thoat = new ButtonType("Cancel");
        ButtonType chuyenSangThemTu = new ButtonType("I want to add new word");

        thongBao.getButtonTypes().setAll(thoat, chuyenSangThemTu);

        Optional<ButtonType> result = thongBao.showAndWait();
        if (result.isPresent() && result.get() == chuyenSangThemTu) {
            try {
                Parent themBot = FXMLLoader.load(getClass().getResource("themBotTu.fxml"));
                Stage b = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
                Scene c = new Scene(themBot);
                b.setScene(c);
                b.show();
                /*FXMLLoader loader = new FXMLLoader(getClass().getResource("themBotTu.fxml"));
                Parent themBot = loader.load();
                Scene scene = new Scene(themBot);
                Stage stage = (Stage) thongBao.getDialogPane().getScene().getWindow();
                stage.setScene(scene);
                stage.show();*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}

