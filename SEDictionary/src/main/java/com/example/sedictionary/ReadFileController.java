package com.example.sedictionary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ReadFileController extends mainSence{
    public void quayLaiTrangChinh(ActionEvent actionEvent) throws IOException {
        Parent a = FXMLLoader.load(getClass().getResource("manHinhChinh.fxml"));
        Stage b = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(a);
        b.setScene(c);
        b.show();
    }
    @FXML
    private ListView<String> listView;
    @FXML
    private WebView nghiaCuaTu;
    @FXML
    private TextField tuCanTra;

    /**
     * hàm gán vào button để bấm vào nó sẽ tra từ.
     * @param actionEvent
     */
    public void search(ActionEvent actionEvent){
        String a = tuCanTra.getText();
        if (EngData.get(a) != null){
            String b = EngData.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
        } else if (VieData.get(a) != null){
            String b = VieData.get(a);
            nghiaCuaTu.getEngine().loadContent(b, "text/html");
        }
    }

    /**
     * hàm hiện từ gợi ý trên listview.
     * @param keyEvent g
     */
    public void searchWord(KeyEvent keyEvent){
        String b = tuCanTra.getText();
        if ( b.isEmpty()){
            return;
        }
        listView.getItems().clear();
        List<String> collect = (List<String>) EngData.keySet().stream().filter(EngData -> EngData.startsWith(b)).collect(Collectors.toList());
        List<String> collect1 = (List<String>) VieData.keySet().stream().filter(VieData -> VieData.startsWith(b)).collect(Collectors.toList());
        List<String> collect2 = (List<String>) listThem.keySet().stream().filter(listThem -> listThem.startsWith(b)).collect(Collectors.toList());
        listView.getItems().addAll(collect);
        listView.getItems().addAll(collect1);
        listView.getItems().addAll(collect2);
    }

    /**
     * hàm để chọn từ bằng chuột trên list thì nó sẽ hiện lên webview.
     * @param mouseEvent fsd
     */
    public void chooseWord(MouseEvent mouseEvent){
        listView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (EngData.get(newValue.trim()) != null){
                        String selectedWord = EngData.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                    } else if (VieData.get(newValue.trim()) != null) {
                        String selectedWord = VieData.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                    } else if (listThem.get(newValue.trim()) != null){
                        String selectedWord = listThem.get(newValue.trim());
                        nghiaCuaTu.getEngine().loadContent(selectedWord, "text/html");
                    }
                }
        );
    }
    public void DoSpeech(ActionEvent event) {
        try {
            String word = tuCanTra.getText();
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
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

