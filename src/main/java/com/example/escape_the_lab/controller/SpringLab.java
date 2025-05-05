package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.SpringModel;
import com.example.escape_the_lab.ui.Overlay;
import com.example.escape_the_lab.ui.rSpring;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

    @Override
    public void startLab() {
        springLabUI.showMainScene();
    }

    @Override
    public boolean checkSolution() {
        return springModel.isCorrectCombination(correctMass, correctSpringConstant);
    }

    @Override
    public Scene createScene() {
        return null;
    }
}
