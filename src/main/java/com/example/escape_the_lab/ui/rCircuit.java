package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.AcidNeutralizationLab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Stack;

public class rCircuit {
    private Stage stage;
    private Overlay overlay;
    private Scene mainScene;

    ImageView inventoryImage = new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm()));
    ImageView back = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));

    // whole room
    ImageView panel = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/panel.png").toExternalForm()));
    ImageView mainBG = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/main-bg.png").toExternalForm()));
    ImageView glassThing = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/glass-thing.png").toExternalForm()));
    ImageView door = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/door.png").toExternalForm()));

    // panel scene
    ImageView panelBG = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/panel-bg.png").toExternalForm()));
    ImageView note = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/note.png").toExternalForm()));
//    ImageView noteZoom = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/note-zoomed.png").toExternalForm()));
    ImageView ledOff = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/led-off.png").toExternalForm()));
//    ImageView ledOn = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/led-on.png").toExternalForm()));
    ImageView unattachedWire = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/unattached-wire.png").toExternalForm()));
//    ImageView attachedWire = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/attached-wire.png").toExternalForm()));


    public rCircuit(Stage stage, Overlay overlay) {
        this.stage = stage;
        this.overlay = overlay;
    }

    public void startLab() {
        Scene scene = makeScene();
        stage.setScene(scene);
        stage.show();
    }

    public Scene makeScene() {
        /*
        left: drawer
        center: metal box with wires in them (idk what theyre called)
        right: blocked metal door
        if user tries to open the door, a little message tells them that its mechanically shut
        the metal box is open and it shows two disconnected wires. little paper at the bottom of the box.
        it says something like: the battery has an electric potential of 5V and the current needed is 0.02 A. (user must find a document listing all the forward voltages
        for each color of led and use the right voltage based on the color that they have, i was thinking red, 1.8V)
        find the resistance needed to make it light up. note: this is a special LED that will explode if the current is too high, but if it's too low, you will not be
        able to change resistance.
         */
        StackPane stackPane = new StackPane();
        Pane pane = new Pane(stackPane);
        mainScene = new Scene(pane, 1000, 650);

        Button skipToNext = new Button("Skip to next");
        skipToNext.setTranslateX(300);
        skipToNext.setTranslateY(300);
        skipToNext.setMinWidth(90);
        skipToNext.setOnAction(e -> {
            AcidNeutralizationLab acidLab = new AcidNeutralizationLab(stage, overlay); // Create a new instance
            rAcidNeutralization lab = new rAcidNeutralization(stage, acidLab, overlay); // Pass both stage and lab
            stage.setScene(lab.getMainScene());
        });

        panel.setOnMouseClicked(e -> {
            panelClicked(mainScene);
        });

        stackPane.getChildren().addAll(mainBG, panel, door, glassThing, inventoryImage, skipToNext);
        pane.getChildren().add(overlay.getInventoryPane());

        return mainScene;
    }

    private void panelClicked(Scene scene) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(panelBG, note, ledOff, unattachedWire, inventoryImage, back);
        Pane pane = new Pane(stackPane, overlay.getInventoryPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> {
            stage.setScene(scene);
        });

        stage.setScene(currentScene);
    }

    private void showInsideMetalBox() {


    }

    private void showDoorMessage() {
        System.out.println("too heavy for you.");
    }
}
