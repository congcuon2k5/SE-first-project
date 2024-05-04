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
    @Override
    public void chuyenSangThemBotTu(ActionEvent actionEvent) throws IOException {
        super.chuyenSangThemBotTu(actionEvent);
    }

    @Override
    public void chuyenSangTranslate(ActionEvent actionEvent) throws IOException {
        super.chuyenSangTranslate(actionEvent);
    }

    @Override
    public void quayLaiTrangChinh(MouseEvent mouseEvent) throws IOException {
        super.quayLaiTrangChinh(mouseEvent);
    }

    @FXML
    private ListView<String> listTuCanTra;
    @FXML
    private WebView nghiaCuaTu;
    @FXML
    private TextField tuCanTra;
    @FXML
    private Button PhatAm;

    private String tuDaTra;
    private Map<String, String> listTuDaTra = new HashMap<>();
    public void search(ActionEvent actionEvent) {
        String a = tuCanTra.getText();
        if (listThem.get(a) != null) {
            String b = listThem.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
            listTuDaTra.put(a, b);
        } else if (EngData.get(a) != null) {
            String b = EngData.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
            listTuDaTra.put(a, b);
        } else if (VieData.get(a) != null) {
            String b = VieData.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
            listTuDaTra.put(a, b);
        } else {
            hienThongBaoNhapSaiTu();
        }
        tuDaTra = a;
        kiemTraCoPhaiTuTiengAnhKhong();
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
            search(new ActionEvent());
            tuDaTra = tuCanTra.getText();
            kiemTraCoPhaiTuTiengAnhKhong();
        }

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
        }
    }

    /**
     * hàm để khi chọn chuột vào từ trên listview thì sẽ hiện nghĩa luôn.
     * @param mouseEvent an
     */
    public void chonTuBangChuot(MouseEvent mouseEvent) {
        listTuCanTra.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if(newValue != null){
                        tuDaTra=newValue.trim();
                    } else return;
                    if (listThem.get(tuDaTra) != null) {
                        String selectedWord = listThem.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                        listTuDaTra.put(tuDaTra, listThem.get(tuDaTra) );
                        tuCanTra.setText(tuDaTra);
                        PhatAm.setVisible(false);
                    } else if (EngData.get(tuDaTra) != null) {
                        String selectedWord = EngData.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                        listTuDaTra.put(tuDaTra, EngData.get(tuDaTra) );
                        tuCanTra.setText(tuDaTra);
                        PhatAm.setVisible(true);
                    } else if (VieData.get(tuDaTra) != null) {
                        String selectedWord = VieData.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                        listTuDaTra.put(tuDaTra, VieData.get(tuDaTra) );
                        tuCanTra.setText(tuDaTra);
                        PhatAm.setVisible(false);
                    }
                }
        );
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    public void kiemTraCoPhaiTuTiengAnhKhong(){
        if (tuCanTra == null) {
            PhatAm.setVisible(false);
        } else if(EngData.get(tuCanTra.getText()) != null){
            PhatAm.setVisible(true);
        } else PhatAm.setVisible(false);
    }

    public void DoSpeech(ActionEvent event) {
        try {
            String word = tuDaTra;
            System.setProperty(
                    "freetts.voices",
                    "com.sun.speech.freetts.en.us"
                            + ".cmu_us_kal.KevinVoiceDirectory");
            Central.registerEngineCentral(
                    "com.sun.speech.freetts"
                            + ".jsapi.FreeTTSEngineCentral");
            Synthesizer synthesizer
                    = Central.createSynthesizer(
                    new SynthesizerModeDesc(Locale.US));
            synthesizer.allocate();
            synthesizer.resume();
            synthesizer.speakPlainText(
                    word, null);
            synthesizer.waitEngineState(
                    Synthesizer.QUEUE_EMPTY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void hienThongBaoNhapSaiTu() {
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
                chuyenSangThemTuScene();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void chuyenSangThemTuScene() throws IOException {
        Parent themBot = FXMLLoader.load(getClass().getResource("themBotTu.fxml"));
        Stage stage = (Stage) tuCanTra.getScene().getWindow();
        Scene scene = new Scene(themBot);
        stage.setScene(scene);
        TextField truyen = (TextField) themBot.lookup("#tuCanSua");
        truyen.setText(tuCanTra.getText());
        stage.show();
    }



}

