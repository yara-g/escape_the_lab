package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.SpringLab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class rSpring extends Application {
    private Label messageLabel;

    @Override
    public void start(Stage primaryStage) {
        Image image = new Image("file:example.jpg"); // Replace with your image path
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1000);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(true);

        StackPane root = new StackPane(imageView);
        Scene scene = new Scene(root, 1000, 600);

        primaryStage.setTitle("UI Scene with ImageView");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
