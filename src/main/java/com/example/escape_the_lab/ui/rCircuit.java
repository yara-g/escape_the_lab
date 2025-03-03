package com.example.escape_the_lab.ui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;

public class rCircuit {
    public Scene makeScene() {
        FlowPane pane = new FlowPane();
        Scene scene = new Scene(pane);

        Image image = new Image("resources/cat.png");


        return scene;
    }
}
