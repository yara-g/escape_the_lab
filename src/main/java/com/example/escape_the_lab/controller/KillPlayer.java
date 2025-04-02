package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.ui.Overlay;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class KillPlayer {
    static ImageView deathScreen = new ImageView(new Image(Objects.requireNonNull(KillPlayer.class.getResource("/images/death-screen.png")).toExternalForm()));
    public static void killPlayer(String message, Stage stage, Scene newScene, Overlay overlay) {
        Button restart = new Button("Restart lab?");
        restart.setOnAction(e -> {
            overlay.getInventory().resetInventory();
            stage.setScene(newScene);
        });
        Label cause = new Label(message);
        cause.setTextFill(Color.RED);
        cause.setTranslateY(-50);
        stage.setScene(new Scene(new StackPane(deathScreen, restart, cause)));
        overlay.getLifeManager().decreaseLife();
    }
}
