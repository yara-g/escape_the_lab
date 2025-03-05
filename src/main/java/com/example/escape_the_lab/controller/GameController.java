package com.example.escape_the_lab.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;

public class GameController extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ImageView startGame = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
        StackPane main = new StackPane(startGame);
        main.setAlignment(Pos.CENTER);
        Scene scene = new Scene(main, 1000, 650);
        stage.setScene(scene);
        stage.show();
    }
}

