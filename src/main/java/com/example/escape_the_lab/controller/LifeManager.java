package com.example.escape_the_lab.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class LifeManager {
    // now every time a player makes a mistake call LifeManager.getInstance().decreaseLife();

    private static LifeManager instance;
    private int lives = 3;
    private final HBox lifeDisplay = new HBox(); // Holds the hearts
    private final ImageView[] hearts = new ImageView[3];

    private LifeManager() {
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
}
