package com.example.escape_the_lab.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class LifeManager {
    // now every time a player makes a mistake call LifeManager.getInstance().decreaseLife();

    private static LifeManager instance;
    //private int lives = 3;
    private HBox lifeDisplay = new HBox(10); // Holds the hearts
    private ImageView[] hearts = new ImageView[3];

    private LifeManager() {
        Image heartImage = new Image(getClass().getResourceAsStream("/images/heart.png"));

        for (int i = 0; i < 3; i++) {
            hearts[i] = new ImageView(heartImage);
            hearts[i].setFitWidth(50); // Adjust size
            hearts[i].setFitHeight(50);
            lifeDisplay.getChildren().add(hearts[i]);
        }

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

    public void hideLives() {
        lifeDisplay.setVisible(false);
    }

    // Update the life display based on the player's current lives
    public void updateLives(int lives) {
        for (int i = 0; i < 3; i++) {
            if (i < lives) {
                hearts[i].setVisible(true);
            } else {
                hearts[i].setVisible(false);
            }
        }
    }
//    public int getLives() {
//        return lives;
//    }
//
//    public void decreaseLife() {
//        if (lives > 0) {
//            lives--;
//            hearts[lives].setVisible(false);
//        }
//    }
//
//    public void resetLives() {
//        lives = 3;
//        for (ImageView heart : hearts) {
//            heart.setVisible(true); // Show all hearts
//        }
//    }

    public HBox getLifeDisplay() {
        return lifeDisplay;
    }
}
