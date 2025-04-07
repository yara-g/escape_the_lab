package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.GameController;
import com.example.escape_the_lab.controller.KillPlayer;
import com.example.escape_the_lab.controller.SpringLab;
import com.example.escape_the_lab.model.Item;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Objects;

public class rCircuit {
    private final Stage stage;
    private final Overlay overlay;
    private Scene mainScene;
    private Item chosenItem;
    private final Item placeHolder;
    private boolean isLedOn = false;
    private boolean resTooLow = false;
    private boolean resTooHigh = false;

    ImageView inventoryImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/inventory.png")).toExternalForm()));
    ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));

    // whole room
    ImageView panel = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/panel.png")).toExternalForm()));
    ImageView mainBG = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/main-bg.png")).toExternalForm()));
    ImageView glassThing = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/glass-thing.png")).toExternalForm()));
    ImageView door = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/door.png")).toExternalForm()));
    ImageView openedDoor = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/door-open.png")).toExternalForm()));
    ImageView dialogue3 = new ImageView(new Image(Objects.requireNonNull((getClass().getResource("/images/AAACircuitLab/dialogue3.png")).toExternalForm())));

    // panel scene
    ImageView panelBG = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/panel-bg.png")).toExternalForm()));
    ImageView note = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/note.png")).toExternalForm()));
    ImageView noteZoom = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/note-zoomed.png")).toExternalForm()));
    ImageView ledOff = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/led-off.png")).toExternalForm()));
    ImageView ledBroken = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/led-broken.png")).toExternalForm()));
    ImageView head = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/head.png")).toExternalForm()));
    ImageView ledOn = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/led-on.png")).toExternalForm()));
    ImageView unattachedWire = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/unattached-wire.png")).toExternalForm()));
    ImageView attachedWire = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/attached-wire.png")).toExternalForm()));
    ImageView clickableSection = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/clickable-section.png")).toExternalForm()));
    ImageView dialogue2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/dialogue2.png")).toExternalForm()));

    // head scene
    ImageView headBG = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/head-zoomed.png")).toExternalForm()));
    ImageView dialogue = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/dialogue.png")).toExternalForm()));
    ImageView res1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/res1.png")).toExternalForm()));
    ImageView res2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/res2.png")).toExternalForm()));
    ImageView res3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/res3.png")).toExternalForm()));
    ImageView res4 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/res4.png")).toExternalForm()));

    // items
    Item res1Item = new Item("160 Ohm Resistor", "/images/AAACircuitLab/res1item.png");
    Item res2Item = new Item("200 Ohm Resistor", "/images/AAACircuitLab/res2item.png");
    Item res3Item = new Item("120 Ohm Resistor", "/images/AAACircuitLab/res3item.png");
    Item res4Item = new Item("100 Ohm Resistor", "/images/AAACircuitLab/res4item.png");

    public rCircuit(Stage stage) {
        this.stage = stage;
        this.overlay = GameController.getOverlay();
        head.setVisible(false);
        placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");
        makeScene();
        chosenItem = placeHolder;
    }

    public void start() {
        stage.setScene(makeScene());
        stage.show();
    }

    public Scene makeScene() {
        StackPane stackPane = new StackPane();

        Button skipToNext = new Button("Skip to next");
        skipToNext.setTranslateX(-400);
        skipToNext.setTranslateY(-300);
        skipToNext.setMinWidth(90);
        skipToNext.setOnAction(e -> passLab());

        panel.setOnMouseClicked(e -> panelScene());

        door.setOnMouseClicked(e -> {
                    stackPane.getChildren().add(dialogue3);
        });

        openedDoor.setOnMouseClicked(e -> {
            passLab();
        });

        glassThing.setOnMouseClicked(e -> breakGlass());

        head.setOnMouseClicked(e -> inspectHead());

        res1Item.getImageView().setOnMouseClicked(e -> chosenItem = res1Item);
        res2Item.getImageView().setOnMouseClicked(e -> chosenItem = res2Item);
        res3Item.getImageView().setOnMouseClicked(e -> chosenItem = res3Item);
        res4Item.getImageView().setOnMouseClicked(e -> chosenItem = res4Item);

        stackPane.getChildren().addAll(mainBG, panel, glassThing, head, inventoryImage, skipToNext);
        if (isLedOn) {
            stackPane.getChildren().add(2, openedDoor);
        } else {
            stackPane.getChildren().add(2, door);
        }
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        mainScene = new Scene(pane, 1000, 650);

        return mainScene;
    }

    private void goBack() {
        stage.setScene(makeScene());
    }

    private void panelScene() {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(panelBG, note, inventoryImage, back, clickableSection);
        if (isLedOn || resTooLow || resTooHigh) {
            stackPane.getChildren().add(attachedWire);
        } else {
            stackPane.getChildren().add(unattachedWire);
        }

        if (isLedOn) {
            stackPane.getChildren().addAll(ledOn);
        } else if (resTooLow) {
            stackPane.getChildren().add(ledBroken);
        } else {
            stackPane.getChildren().add(ledOff);
        }

        if (resTooHigh) {
            stackPane.getChildren().add(dialogue2);
        }

        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> goBack());
        note.setOnMouseClicked(e -> readNote());
        clickableSection.setOnMouseClicked(e -> {
            if (chosenItem != placeHolder) {
                useItem(e);
            }
        });

        stage.setScene(currentScene);
    }

    private void inspectHead() {
        StackPane stackPane = new StackPane(headBG, res1, res2, res3, res4, dialogue, inventoryImage, back);
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> goBack());

        res1.setOnMouseClicked(e -> {
            dialogue.setVisible(false);
            res1.setVisible(false);
            overlay.getInventory().addItem(res1Item);
            overlay.updateInventory();
        });

        res2.setOnMouseClicked(e -> {
            res2.setVisible(false);
            overlay.getInventory().addItem(res2Item);
            overlay.updateInventory();
        });

        res3.setOnMouseClicked(e -> {
            res3.setVisible(false);
            overlay.getInventory().addItem(res3Item);
            overlay.updateInventory();
        });

        res4.setOnMouseClicked(e -> {
            res4.setVisible(false);
            overlay.getInventory().addItem(res4Item);
            overlay.updateInventory();
        });

        stage.setScene(currentScene);
    }

    private void readNote() {
        StackPane stackPane = new StackPane(panelBG, note, ledOff, unattachedWire, inventoryImage, back, noteZoom);
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> panelScene());
        stage.setScene(currentScene);
    }

    private void breakGlass() {
        glassThing = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/glass-thing-broken.png")).toExternalForm()));
        head.setVisible(true);
        stage.setScene(makeScene());
    }

    private void passLab() {
        overlay.getInventory().resetInventory();
        SpringLab s = new SpringLab(stage);
        //AcidNeutralizationLab acidLab = new AcidNeutralizationLab(stage, overlay); // Create a new instance
        //rAcidNeutralization lab = new rAcidNeutralization(stage, acidLab, overlay); // Pass both stage and lab
        s.startLab();
        overlay.updateInventory();
    }

    private void failLab() {
        // new circuit lab created if we need to restart the lab
        rCircuit newRCircuit = new rCircuit(stage);

        if (chosenItem.equals(res3Item) || chosenItem.equals(res4Item)) {
            resTooLow = true;
            panelScene();
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.play();

            pause.setOnFinished(e -> KillPlayer.killPlayer("you were blinded by a shard that got into your eye.", stage, newRCircuit.makeScene(), overlay));

        } else {
            resTooHigh = true;
            panelScene();
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.play();

            pause.setOnFinished(e -> KillPlayer.killPlayer("you couldn't figure out how to get out, so you succumbed due to dehydration.", stage, newRCircuit.makeScene(), overlay));
        }
    }

    private void useItem(MouseEvent event) {
        if (chosenItem != null && chosenItem.equals(res1Item)) {
            res1Item.getImageView().setMouseTransparent(false);
            res1Item.getImageView().setVisible(true);
            chosenItem = placeHolder;
            overlay.getInventory().removeItem(res1Item);
            overlay.updateInventory();

            isLedOn = true;
            panelScene();
        } else {
            assert chosenItem != null;
            chosenItem.getImageView().setMouseTransparent(false);
            chosenItem.getImageView().setVisible(true);
            overlay.getInventory().removeItem(chosenItem);
            overlay.updateInventory();

            failLab();
        }
    }
}
