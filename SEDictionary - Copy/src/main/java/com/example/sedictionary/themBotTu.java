package com.example.sedictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class themBotTu extends mainSence {
    @Override
    public void chuyenSangTraTu(ActionEvent event) throws IOException {
        super.chuyenSangTraTu(event);
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
    private TextField tuCanSua;
    @FXML
    private HTMLEditor suaTu;
    @FXML
    private ListView<String> listTuCanSua;
    private Map<String, String> listTuDaTra = new HashMap<>();


    public void searchWordThatNeedToFix(KeyEvent keyEvent) {
        String tu = tuCanSua.getText();
        if (tu.isEmpty()) {
            listTuCanSua.setVisible(false);
            /*listTuCanSua.getItems().clear();
            List<String> cacTuDaTra = new ArrayList<>();
            cacTuDaTra = (List<String>) listTuDaTra.keySet().stream().filter(listTuDaTra -> listTuDaTra.startsWith("")).collect(Collectors.toList());
            listTuCanSua.getItems().addAll(cacTuDaTra);*/
        } else {
            listTuCanSua.setVisible(true);
            listTuCanSua.getItems().clear();
            List<String> collect = (List<String>) EngData.keySet().stream().filter(EngData -> EngData.startsWith(tu)).collect(Collectors.toList());
            List<String> collect1 = (List<String>) VieData.keySet().stream().filter(VieData -> VieData.startsWith(tu)).collect(Collectors.toList());
            List<String> collect2 = (List<String>) listThem.keySet().stream().filter(listThem -> listThem.startsWith(tu)).collect(Collectors.toList());
            listTuCanSua.getItems().addAll(collect);
            listTuCanSua.getItems().addAll(collect1);
            listTuCanSua.getItems().addAll(collect2);
        }
    }

    public void chonTuDeSua(MouseEvent mouseEvent){
        listTuCanSua.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null){
                        return;
                    }
                    if (listThem.get(newValue.trim()) != null) {
                        String selectedWord = listThem.get(newValue.trim());
                        suaTu.setHtmlText(selectedWord);
                        tuCanSua.setText(newValue.trim());
                    } else if (EngData.get(newValue.trim()) != null) {
                        String selectedWord = EngData.get(newValue.trim());
                        suaTu.setHtmlText(selectedWord);
                        tuCanSua.setText(newValue.trim());
                    } else if (VieData.get(newValue.trim()) != null) {
                        String selectedWord = VieData.get(newValue.trim());
                        suaTu.setHtmlText(selectedWord);
                        tuCanSua.setText(newValue.trim());
                    }
                }
        );
    }
    public void save(ActionEvent actionEvent) throws IOException {
        String tu = tuCanSua.getText();
        if (kiemTraCoHayKhong()) {
            Alert tuCanThemSai = new Alert(Alert.AlertType.CONFIRMATION);
            tuCanThemSai.setTitle("Add new word");
            tuCanThemSai.setHeaderText("");
            tuCanThemSai.setContentText("Do you want to add new word to the dictionary");
            Optional<ButtonType> ketQua = tuCanThemSai.showAndWait();
            if (ketQua.get() == ButtonType.OK){
                them();
                listThem.put(tu, suaTu.getHtmlText());
                return;
            }
        } else {
            them();
            listThem.put(tu, suaTu.getHtmlText());
            return;
        }
    }

    public void them() throws IOException{
        String tu = tuCanSua.getText();
        if (EngData.get(tu) != null) {
            suaE_V();
        } else if (VieData.get(tu) != null){
            suaV_E();
        } else {
            BufferedWriter sua = new BufferedWriter(new FileWriter("data/ThemTu.txt", true));
            sua.write(tu + "<html>" + suaTu.getHtmlText() + "\n");
            sua.close();
        }
    }

    public void suaE_V() throws IOException {
        String tu = tuCanSua.getText();
        BufferedReader a = new BufferedReader(new FileReader("data/E_V.txt"));
        List<String> dong = new ArrayList<>();
        String line;
        while ((line = a.readLine()) != null) {
            if (line.startsWith(tu + "<html>")) {
                dong.add(tu + "<html>" + suaTu.getHtmlText());
            } else dong.add(line);
        }
        a.close();

        BufferedWriter sua = new BufferedWriter(new FileWriter("data/E_V.txt"));
        for (String n : dong){
            sua.write(n + "\n");
        }
        sua.close();
    }

    public void suaV_E() throws IOException {
        String tu = tuCanSua.getText();
        BufferedReader a = new BufferedReader(new FileReader("data/V_E.txt"));
        List<String> dong = new ArrayList<>();
        String line;
        while ((line = a.readLine()) != null) {
            if (line.startsWith(tu + "<html>")) {
                dong.add(tu + "<html>" + suaTu.getHtmlText());
            } else dong.add(line);
        }
        a.close();

        BufferedWriter sua = new BufferedWriter(new FileWriter("data/V_E.txt"));
        for (String n : dong){
            sua.write(n + "\n");
        }
        sua.close();
    }

    public boolean kiemTraCoHayKhong() throws IOException{
        String tu = tuCanSua.getText();
        if(!((EngData.get(tu) == null) && (VieData.get(tu) == null))) {
            return false;
        }
        return true;
    }

    public void delete(ActionEvent actionEvent) throws IOException{
        if (kiemTraCoHayKhong()) {
            Alert khongCoTuNay = new Alert(Alert.AlertType.ERROR);
            khongCoTuNay.setTitle("ERROR");
            khongCoTuNay.setHeaderText("This word doesn't exist in your dictionary!");
            khongCoTuNay.show();
            return;
        }
        Alert thongBaoXoaTu = new Alert(Alert.AlertType.CONFIRMATION);
        thongBaoXoaTu.setTitle("Delete word");
        thongBaoXoaTu.setHeaderText("Do you want to delete this word?");
        thongBaoXoaTu.setContentText("Once deleted, you will not be able to look up this word again!");
        Optional<ButtonType> xoa = thongBaoXoaTu.showAndWait();
        if (xoa.get() == ButtonType.OK) {
            xoa();
            if(EngData.get(tuCanSua.getText()) != null) {
                EngData.remove(tuCanSua.getText());
            }
            if(VieData.get(tuCanSua.getText()) != null) {
                VieData.remove(tuCanSua.getText());
            }
            if(listThem.get(tuCanSua.getText()) != null) {
                listThem.remove(tuCanSua.getText());
            }
            tuCanSua.setText("");
            suaTu.setHtmlText("");
            listTuCanSua.getItems().clear();
        }
    }
    public void xoa() throws IOException{
        String tu = tuCanSua.getText();
        if (listThem.get(tu) != null) {
            BufferedReader a = new BufferedReader(new FileReader("data/E_V.txt"));
            List<String> dong = new ArrayList<>();
            String line;
            while ((line = a.readLine()) != null) {
                if (line.startsWith(tu + "<html>")) {
                    continue;
                } else dong.add(line);
            }
            a.close();

            BufferedWriter sua = new BufferedWriter(new FileWriter("data/E_V.txt"));
            for (String n : dong){
                sua.write(n + "\n");
            }
            sua.close();
        }
        if (EngData.get(tu) != null) {
            BufferedReader a = new BufferedReader(new FileReader("data/E_V.txt"));
            List<String> dong = new ArrayList<>();
            String line;
            while ((line = a.readLine()) != null) {
                if (line.startsWith(tu + "<html>")) {
                    continue;
                } else dong.add(line);
            }
            a.close();

            BufferedWriter sua = new BufferedWriter(new FileWriter("data/E_V.txt"));
            for (String n : dong){
                sua.write(n + "\n");
            }
            sua.close();
        }
        if (VieData.get(tu) != null){
            BufferedReader a = new BufferedReader(new FileReader("data/V_E.txt"));
            List<String> dong = new ArrayList<>();
            String line;
            while ((line = a.readLine()) != null) {
                if (line.startsWith(tu + "<html>")) {
                    continue;
                } else dong.add(line);
            }
            a.close();

            BufferedWriter sua = new BufferedWriter(new FileWriter("data/E_V.txt"));
            for (String n : dong){
                sua.write(n + "\n");
            }
            sua.close();
        }
    }
}
