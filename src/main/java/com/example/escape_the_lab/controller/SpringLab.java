package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.SpringModel;
import com.example.escape_the_lab.ui.Overlay;
import com.example.escape_the_lab.ui.rSpring;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpringLab extends Lab {
    private Stage stage;
    private rSpring springLabUI;
    private SpringModel springModel;
//    private double selectedMass = -1;  // no mass selected
//    private double selectedSpringConstant = -1;  // no spring selected
    private final double correctMass = 2.0;  // in kg
    private final double correctSpringConstant = 50; // k in N/m

    public SpringLab(Stage stage) {
        this.stage = stage;
        Overlay overlay = GameController.getOverlay();
        this.springLabUI = new rSpring(stage, this);
        this.springModel = new SpringModel(-1, -1); // Default values
    }

    @Override
    public void startLab() {
        springLabUI.showIntroScene();
    }

    @Override
    public boolean checkSolution() {
        return springModel.isCorrectCombination(correctMass, correctSpringConstant);
    }

    public void failLab() {
        System.out.println("Wrong combination! Try again.");
    }

    public void setupLab() {
        //will be used for the lab scene
    }

    @Override
    public Scene createScene() {
        return null; //UI class does this
    }

    public void setSelectedMass(double mass) {
        springModel.setMass(mass);
    }

    public void setSelectedSpringConstant(double k) {
        springModel.setSpringConstant(k);
    }

    public void attemptEscape() {
//        if (checkSolution()) {
//            System.out.println("Correct combination! Escape successful.");
//            gameController.transitionToNextLab();
//        } else {
//            failLab();
//        }
    }
}
