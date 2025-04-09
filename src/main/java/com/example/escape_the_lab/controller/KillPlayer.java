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
        if (overlay.getLifeManager().getLives() == 1) {
            Button restart = new Button("Go back to the main menu?");
            restart.setOnAction(e -> {
                GameController gameController = new GameController();
                try {
                    gameController.start(stage);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            Label cause = new Label("Looks like you ran out of lives...");
            cause.setTextFill(Color.RED);
            cause.setTranslateY(-50);
            stage.setScene(new Scene(new StackPane(deathScreen, restart, cause)));
        } else {
            Button restart = new Button("Restart lab?");
            restart.setOnAction(e -> {
                overlay.getInventory().resetInventory();
                overlay.updateInventory();
                stage.setScene(newScene);
            });
            Label cause = new Label(message);
            cause.setTextFill(Color.RED);
            cause.setTranslateY(-50);
            stage.setScene(new Scene(new StackPane(deathScreen, restart, cause)));
        }
        overlay.getLifeManager().resetLives();
    }
}
