package com.example.gameofse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Game of SE Group");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
    public void Start(ActionEvent actionEvent) throws IOException {
        Parent a = FXMLLoader.load(getClass().getResource("game.fxml"));
        Stage b = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene c = new Scene(a);
        b.setScene(c);
        b.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}