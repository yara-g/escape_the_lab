package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.SpringModel;
import com.example.escape_the_lab.ui.Overlay;
import com.example.escape_the_lab.ui.rSpring;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The SpringLab class is the controller for the spring-mass physics lab part of the game. It extends the abstract
 * Lab class. It manages the logic related to the placement of items (spring and mass), validation of the user's
 * solution, and interactions with the SpringModel and UI(rSpring) classes.
 */
public class SpringLab extends Lab {
    private final Stage stage;
    private final rSpring springLabUI;
    private final SpringModel springModel;
    private final Overlay overlay;
    private final double correctMass = 2.0;  // in kg
    private final double correctSpringConstant = 50; // k in N/m

    public SpringLab(Stage stage) {
        this.stage = stage;
        this.overlay = GameController.getOverlay();
        this.springLabUI = new rSpring(stage, this);
        this.springModel = new SpringModel(-1, -1);
    }

    /**
     * Starts the lab by displaying the main scene through the UI class.
     */
    @Override
    public void startLab() {
        springLabUI.showMainScene();
    }

    /**
     * Checks whether the selected mass and spring constant match the correct values.
     * @return true if the correct combination is selected; false otherwise.
     */
    @Override
    public boolean checkSolution() {
        return springModel.isCorrectCombination(correctMass, correctSpringConstant);
    }

    @Override
    public Scene createScene() {
        return null; //UI class does this
    }
//
//    public void setSelectedMass(double mass) {
//        springModel.setMass(mass);
////    }
//
//    public void setSelectedSpringConstant(double k) {
//        springModel.setSpringConstant(k);
//    }

    public void attemptEscape() {
//        if (checkSolution()) {
//            System.out.println("Correct combination! Escape successful.");
//            gameController.transitionToNextLab();
//        } else {
//            failLab();
//        }
    }
}
