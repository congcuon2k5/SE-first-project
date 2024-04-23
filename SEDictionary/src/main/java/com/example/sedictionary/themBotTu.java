package com.example.sedictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

public class themBotTu extends mainSence {
    public void quayLaiTrangChinh(ActionEvent actionEvent) throws IOException {
        Parent a = FXMLLoader.load(getClass().getResource("manHinhChinh.fxml"));
        Stage b = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(a);
        b.setScene(c);
        b.show();
    }
    @FXML
    private TextField tuCanThem;
    @FXML
    private TextField nghiaCuaTuCanThem;

    /**
     * hàm để khi nhấn button thêm từ thì nó sẽ thêm từ vào list đồng thời ghi vào trong file để lưu từ đó.
     * @param actionEvent an
     * @throws IOException an
     */
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
    }

    /**
     * hàm để khi nhấn button xóa từ thì sẽ xóa từ đã được click chuột trên listview trong list và cả trong file.
     * @param actionEvent fg
     * @throws IOException f
     */
    public void xoaTu2(ActionEvent actionEvent) throws IOException {
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
    }

    /**
     * hàm chọn từ trên listview để xóa.
     * @param mouseEvent an
     */
    public void chooseWord(MouseEvent mouseEvent){
        danhSachTuDuocThem.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    String selectedWord = listThem.get(newValue.trim());
                    nghiamoi.getEngine().loadContent(selectedWord, "text/html");
                }
        );
    }
}
