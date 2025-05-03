package com.example.escape_the_lab.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Help {
    public static void show(String message, Image image) {
        Stage helpStage = new Stage();
        helpStage.initModality(Modality.APPLICATION_MODAL);
        helpStage.setTitle("Help");

        Label helpMessage = new Label(message);
        helpMessage.setStyle("-fx-font-size: 16px; -fx-wrap-text: true; -fx-text-fill: red");
        helpMessage.setWrapText(true);

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(200);

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-text-fill: black; -fx-background-color: red");
        closeButton.setOnAction(e -> helpStage.close());

        VBox layout = new VBox(15, helpMessage, imageView, closeButton);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-background-color: black");

        Scene scene = new Scene(layout, 520, 400);
        helpStage.setScene(scene);
        helpStage.setResizable(false);
        helpStage.showAndWait();
    }
}
