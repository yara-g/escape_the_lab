package com.example.escape_the_lab.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameController extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button test = new Button("test");
        Scene scene = new Scene(test, 1000, 650);
        stage.setScene(scene);
        stage.show();
    }
}

