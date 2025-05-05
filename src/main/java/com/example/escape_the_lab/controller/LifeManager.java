package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.ui.Inventory;
import com.example.escape_the_lab.ui.Overlay;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

public class LifeManager {
    private static LifeManager instance;
    private int lives = 3;
    private final HBox lifeDisplay = new HBox(); // Holds the hearts
    private final ImageView[] hearts = new ImageView[3];

    public LifeManager() {
        Image heart1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/heart1.png")));
        Image heart2 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/heart2.png")));
        Image heart3 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/heart3.png")));

        hearts[0] = new ImageView(heart1);
        hearts[1] = new ImageView(heart2);
        hearts[2] = new ImageView(heart3);
        lifeDisplay.getChildren().addAll(hearts[0], hearts[1], hearts[2]);

        lifeDisplay.setVisible(false); // Hidden at the start
    }

    public static LifeManager getInstance() {
        if (instance == null) {
            instance = new LifeManager();
        }
        return instance;
    }

    public void showLives() {
        lifeDisplay.setVisible(true);
    }

    public void decreaseLife() {
        if (lives > 0) {
            lives--;
            hearts[lives].setVisible(false);
        }
    }

    public void resetLives() {
        lives = 3;
        for (ImageView heart : hearts) {
            heart.setVisible(true); // Show all hearts
        }
    }

    public HBox getLifeDisplay() {
        return lifeDisplay;
    }

    public int getLives() {
        return lives;
    }

    public void kill(Group inventoryPane, ImageView bg, ImageView let, StackPane stackPane, Overlay overlay, ImageView goBack, ImageView retourner, Inventory inventory, Stage stage) {
        if (lives == 0) {
            FadeTransition fadeTransitionIn = new FadeTransition(Duration.seconds(2), inventoryPane);
            FadeTransition fadeTransitionBG = new FadeTransition(Duration.seconds(2), bg);
            FadeTransition fadeTransitionLet = new FadeTransition(Duration.seconds(2), let);
            fadeTransitionBG.setFromValue(0);
            fadeTransitionBG.setToValue(1);
            fadeTransitionLet.setFromValue(0);
            fadeTransitionLet.setToValue(1);
            fadeTransitionIn.setFromValue(1);
            fadeTransitionIn.setToValue(0);
            fadeTransitionBG.play();
            fadeTransitionLet.play();
            fadeTransitionIn.play();
            stackPane.getChildren().add(bg);
            stackPane.getChildren().add(let);
            overlay.updateInventory();
            goBack.setOnMouseClicked(e -> reStart(stage, inventoryPane, inventory, overlay));
            retourner.setOnMouseClicked(e -> reStart(stage, inventoryPane, inventory, overlay));
            inventory.resetInventory();
        }
    }

    /**
     * Go back to the beginning.
     * @param stage stage.
     */
    private void reStart(Stage stage, Group inventoryPane, Inventory inventory, Overlay overlay) {
        stage.setScene(GameController.getScene());
        resetLives();
        inventoryPane.setOpacity(1);
        inventory.resetInventory();
        overlay.updateInventory();
    }
}
