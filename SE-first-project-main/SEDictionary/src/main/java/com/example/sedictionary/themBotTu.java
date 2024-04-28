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
    public void quayLaiTrangChinh(ActionEvent actionEvent) throws IOException {
        Parent a = FXMLLoader.load(getClass().getResource("manHinhChinh.fxml"));
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

    /**
     * hàm để khi nhấn button thêm từ thì nó sẽ thêm từ vào list đồng thời ghi vào trong file để lưu từ đó.
     * @throws IOException an
     *//*
    public void themTu2(ActionEvent actionEvent) throws IOException {
        docFile();
        listThem.put(tuCanThem.getText(), nghiaCuaTuCanThem.getText());
        danhSachTuDuocThem.getItems().clear(); //xóa list trước
        danhSachTuDuocThem.getItems().addAll((Collection<? extends String>) listThem.keySet()); //hiện full list trên lisview
        FileWriter fr = new FileWriter("data/testThemTu.txt", true);
        BufferedWriter br = new BufferedWriter(fr);
        br.write(tuCanThem.getText() + "<html>" + nghiaCuaTuCanThem.getText() + "\n"); //viết vào file thêm từ để lưu
        br.close();
        fr.close();
    }*/

    public void searchWordThatNeedToFix(KeyEvent keyEvent) {
        String tu = tuCanSua.getText();
        if (tu.isEmpty()) {
            return;
        }
        listTuCanSua.getItems().clear();
        List<String> collect = (List<String>) EngData.keySet().stream().filter(EngData -> EngData.startsWith(tu)).collect(Collectors.toList());
        List<String> collect1 = (List<String>) VieData.keySet().stream().filter(VieData -> VieData.startsWith(tu)).collect(Collectors.toList());
        List<String> collect2 = (List<String>) listThem.keySet().stream().filter(listThem -> listThem.startsWith(tu)).collect(Collectors.toList());
        listTuCanSua.getItems().addAll(collect);
        listTuCanSua.getItems().addAll(collect1);
        listTuCanSua.getItems().addAll(collect2);
    }

    public void chonTuDeSua(MouseEvent mouseEvent){
        listTuCanSua.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (EngData.get(newValue.trim()) != null) {
                        String selectedWord = EngData.get(newValue.trim());
                        suaTu.setHtmlText(selectedWord);
                        tuCanSua.setText(newValue);
                    } else if (VieData.get(newValue.trim()) != null) {
                        String selectedWord = VieData.get(newValue.trim());
                        suaTu.setHtmlText(selectedWord);
                        tuCanSua.setText(newValue);
                    } else if (listThem.get(newValue.trim()) != null) {
                        String selectedWord = listThem.get(newValue.trim());
                        suaTu.setHtmlText(selectedWord);
                        tuCanSua.setText(newValue);
                    }
                }
        );
    }
    public void save(ActionEvent actionEvent) throws IOException{
        String tu = tuCanSua.getText();
        if (kiemTraCoHayKhong()){
            Alert tuCanThemSai = new Alert(Alert.AlertType.ERROR);
//            tuCanThemSai.setTitle("");
//            tuCanThemSai.setHeaderText("");
            tuCanThemSai.setContentText("This word is already exist!");
            tuCanThemSai.show();
        } else {
            BufferedReader a = new BufferedReader(new FileReader("data/ThemTu.txt"));
            String line;
            while ((line = a.readLine()) != null) {
                if (line.startsWith(tu + "<html>")) {
                    them();
                    System.out.println("day");
                    return;
                }
            }
            a.close();
            BufferedWriter sua = new BufferedWriter(new FileWriter("data/ThemTu.txt", true));
            sua.write(tu + "<html>" + suaTu.getHtmlText() + "\n");
            sua.close();
        }
    }

    public void them() throws IOException {
        String tu = tuCanSua.getText();
        BufferedReader a = new BufferedReader(new FileReader("data/ThemTu.txt"));
        List<String> dong = new ArrayList<>();
        String line;
        while ((line = a.readLine()) != null) {
            if (line.startsWith(tu + "<html>")) {
                dong.add(tu + "<html>" + suaTu.getHtmlText());
            } else dong.add(line);
        }
        a.close();
        BufferedWriter sua = new BufferedWriter(new FileWriter("data/ThemTu.txt"));
        for (String n : dong){
            sua.write(n + "\n");
        }
        sua.close();
    }

    public boolean kiemTraCoHayKhong() throws IOException{
        String tu = tuCanSua.getText();
        if(!(EngData.get(tu).isEmpty() && VieData.get(tu).isEmpty())) {
            return true;
        }
        return false;
    }

    public void delete(ActionEvent actionEvent) {

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
