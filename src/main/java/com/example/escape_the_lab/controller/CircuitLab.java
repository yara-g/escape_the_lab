package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.ui.rCircuit;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CircuitLab extends Lab{

    public void startLab() {
        Stage stage = GameController.getStage();
        rCircuit rCircuit = new rCircuit(stage);
        rCircuit.start();
    }

    @Override
    public boolean checkSolution() {
        return false;
    }

    @Override
    public Scene createScene() {
        return null;
    }
}
