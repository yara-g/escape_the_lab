package com.example.escape_the_lab.ui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class rCircuit {
    public Scene makeScene() {

        // when user clicks drawer, they are presented with cables, resistors, battery, switches, something to
        // measure voltage/current as well as various other crap
        ImageView drawer = new ImageView(new Image("cat.png"));
        drawer.setPreserveRatio(true);
        drawer.setFitHeight(300);
        drawer.setTranslateY(200);

        // the box is closed at first but the user can click it open
        // zoom in to a box with one lamp with two cables coming out of it
        // sticky note on the back of the box. user has to click it and it zooms in to read the text
        // "note for the electrician: DO NOT exceed a current of blahblah or else the lamp will explode and you will unfortunately die in a mysterious way like
        // the ones who came before you. at the same time, you must keep a current of bwbababab to turn the light on and open the door."
        ImageView metalBox = new ImageView(new Image("cat.png"));
        metalBox.setPreserveRatio(true);
        metalBox.setFitHeight(300);
        metalBox.setTranslateY(100);

        // door. if the user clicks it, a message tells them that it wont move at all
        ImageView door = new ImageView(new Image("cat.png"));
        door.setFitHeight(600);
        door.setFitWidth(200);
        door.setTranslateY(-50);

        HBox pane = new HBox(100, drawer, metalBox, door);
        Scene scene = new Scene(pane, 1000, 650);

        return scene;
    }
}
