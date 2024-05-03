package com.example.sedictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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

    public void quayLaiTrangChinh(ActionEvent actionEvent) throws IOException {
        Parent a = FXMLLoader.load(getClass().getResource("My E-learning.fxml"));
        Stage b = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(a);
        b.setScene(c);
        b.show();
    }
    @FXML
    private TextField tuCanSua;
    @FXML
    private HTMLEditor suaTu;
    @FXML
    private ListView<String> listTuCanSua;


    public void searchWordThatNeedToFix(KeyEvent keyEvent) {
        String tu = tuCanSua.getText();
        if (tu.isEmpty()) {
            return;
        }
        listTuCanSua.getItems().clear();
        List<String> collect = (List<String>) allWord.keySet().stream().filter(allWord -> allWord.startsWith(tu)).collect(Collectors.toList());
        listTuCanSua.getItems().addAll(collect);
    }

    public void chonTuDeSua(MouseEvent mouseEvent){
        listTuCanSua.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null){
                        return;
                    }
                    if (allWord.get(newValue.trim()) != null) {
                        String selectedWord = allWord.get(newValue.trim());
                        suaTu.setHtmlText(selectedWord);
                        tuCanSua.setText(newValue);
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
                allWord.put(tu, suaTu.getHtmlText());
                return;
            }
        } else {
            them();
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
        /*String tu = tuCanSua.getText();
        BufferedReader a = new BufferedReader(new FileReader("data/ThemTu.txt"));
        String line;
        while ((line = a.readLine()) != null) {
            if (line.startsWith(tu + "<html>")) {
                sua();
                System.out.println("day");
                return;
            }
        }
        a.close();*/
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
        allWord.put(tu, suaTu.getHtmlText());
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
        allWord.put(tu, suaTu.getHtmlText());
    }

    public boolean kiemTraCoHayKhong() throws IOException{
        String tu = tuCanSua.getText();
        if(!((EngData.get(tu) == null) && (VieData.get(tu) == null))) {
            return false; //có
        }
        return true; //không có
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
            allWord.remove(tuCanSua.getText());
            tuCanSua.setText("");
            suaTu.setHtmlText("");
            listTuCanSua.getItems().clear();
        }
    }
    public void xoa() throws IOException{
        String tu = tuCanSua.getText();
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
        } else if (VieData.get(tu) != null){
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



    /**
     * hàm để khi nhấn button xóa từ thì sẽ xóa từ đã được click chuột trên listview trong list và cả trong file.
     * @param actionEvent fg
     * @throws IOException f
     */
   /* public void xoaTu2(ActionEvent actionEvent) throws IOException {
        String s = danhSachTuDuocThem.getSelectionModel().getSelectedItem();
        listThem.remove(s);
        danhSachTuDuocThem.getItems().clear(); //xóa list trước
        danhSachTuDuocThem.getItems().addAll((Collection<? extends String>) listThem.keySet()); //hiện full list trên lisview
        nghiamoi.getEngine().load(null);
        FileReader fr = new FileReader("data/testThemTu.txt");
        BufferedReader br = new BufferedReader(fr);
        List<String> dong = new ArrayList<>();
        String line;
        while ((line = br.readLine() ) != null){
            if(line.startsWith(s + "<html>")){
                continue;
            } else dong.add(line);
        } //tạo 1 list mới ghi hết các donhf trừ dòng đã xóa, sau đó thay vào file để xóa từ cần xóa
        fr.close();
        br.close();

        FileWriter fw = new FileWriter("data/testThemTu.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        for (String n : dong){
            bw.write(n + "\n");
        }
        bw.close();
        fw.close();
    }*/

    /**
     * hàm chọn từ trên listview để xóa.
     * @param mouseEvent an
     */
    /*public void chooseWord(MouseEvent mouseEvent){
        danhSachTuDuocThem.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String selectedWord = listThem.get(newValue.trim());
                    nghiamoi.getEngine().loadContent(selectedWord, "text/html");
                }
        );
    }*/
}
