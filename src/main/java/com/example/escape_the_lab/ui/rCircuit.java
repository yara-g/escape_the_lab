package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.AcidNeutralizationLab;
import com.example.escape_the_lab.model.Item;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class rCircuit {
    private Stage stage;
    private Overlay overlay;
    private Scene mainScene;
    private boolean hasResistor = false;

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
    ImageView noteZoom = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/note-zoomed.png").toExternalForm()));
    ImageView ledOff = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/led-off.png").toExternalForm()));
    ImageView head = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/head.png").toExternalForm()));
//    ImageView ledOn = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/led-on.png").toExternalForm()));
    ImageView unattachedWire = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/unattached-wire.png").toExternalForm()));
//    ImageView attachedWire = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/attached-wire.png").toExternalForm()));

    // head scene
    ImageView headBG = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/head-zoomed.png").toExternalForm()));
    ImageView dialogue = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/dialogue.png").toExternalForm()));
    ImageView res1 = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/res1.png").toExternalForm()));
    ImageView res2 = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/res2.png").toExternalForm()));
    ImageView res3 = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/res3.png").toExternalForm()));
    ImageView res4 = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/res4.png").toExternalForm()));

    Item res1Item = new Item("160 Ohm Resistor", "/images/AAACircuitLab/res1item.png");
    Item res2Item = new Item("200 Ohm Resistor", "/images/AAACircuitLab/res2item.png");
    Item res3Item = new Item("120 Ohm Resistor", "/images/AAACircuitLab/res3item.png");
    Item res4Item = new Item("100 Ohm Resistor", "/images/AAACircuitLab/res4item.png");

    public rCircuit(Stage stage, Overlay overlay) {
        this.stage = stage;
        this.overlay = overlay;
        head.setVisible(false);
        makeScene();
    }

    public void start() {
        stage.setScene(makeScene());
        stage.show();
    }

    public Scene makeScene() {
        StackPane stackPane = new StackPane();

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
            panelClicked();
        });

        door.setOnMouseClicked(e -> {
            doorClicked();
        });

        glassThing.setOnMouseClicked(e -> {
            breakGlass();
        });

        head.setOnMouseClicked(e -> {
            inspectHead();
        });

        stackPane.getChildren().addAll(mainBG, panel, door, glassThing, head, inventoryImage, skipToNext);
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        mainScene = new Scene(pane, 1000, 650);

        return mainScene;
    }

    private void goBack() {
        stage.setScene(makeScene());
    }

    private void panelClicked() {
        StackPane stackPane = new StackPane();
        Button addResistor = new Button("add res");
        addResistor.setTranslateX(-55);
        stackPane.getChildren().addAll(panelBG, note, ledOff, unattachedWire, inventoryImage, back);
        if (hasResistor) {
            stackPane.getChildren().add(addResistor);
        }
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        overlay.getLifeManager().decreaseLife();
        overlay.updateLifeManager();
        back.setOnMouseClicked(e -> {
            goBack();
        });
        note.setOnMouseClicked(e -> {
            readNote();
        });
        addResistor.setOnAction(e -> {
            System.out.println("What would you like to put here?");
        });

        stage.setScene(currentScene);
    }

    private void doorClicked() {
        System.out.println("The power is cut and the door is way too heavy for you to push open.");
    }

    private void inspectHead() {
        StackPane stackPane = new StackPane(headBG, res1, res2, res3, res4, dialogue, inventoryImage, back);
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> {
            goBack();
        });

        res1.setOnMouseClicked(e -> {
            dialogue.setVisible(false);
            res1.setVisible(false);
            overlay.getInventory().addItem(res1Item);
            overlay.updateInventory();
            hasResistor = true;
        });

        res2.setOnMouseClicked(e -> {
            res2.setVisible(false);
            overlay.getInventory().addItem(res2Item);
            overlay.updateInventory();
            hasResistor = true;
        });

        res3.setOnMouseClicked(e -> {
            res3.setVisible(false);
            overlay.getInventory().addItem(res3Item);
            overlay.updateInventory();
            hasResistor = true;
        });

        res4.setOnMouseClicked(e -> {
            res4.setVisible(false);
            overlay.getInventory().addItem(res4Item);
            overlay.updateInventory();
            hasResistor = true;
        });

        stage.setScene(currentScene);
    }

    private void readNote() {
        StackPane stackPane = new StackPane(panelBG, note, ledOff, unattachedWire, inventoryImage, back, noteZoom);
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> {
            panelClicked();
        });

        stage.setScene(currentScene);
    }

    private void breakGlass() {
        glassThing = new ImageView(new Image(getClass().getResource("/images/AAACircuitLab/glass-thing-broken.png").toExternalForm()));
        head.setVisible(true);
        stage.setScene(makeScene());
    }
}
