package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.GameController;
import com.example.escape_the_lab.controller.KillPlayer;
import com.example.escape_the_lab.controller.LifeManager;
import com.example.escape_the_lab.controller.SpringLab;
import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.model.Player;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
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
    private LifeManager lifeManager = GameController.getLifeManager();
    private final Player player = GameController.getPlayer();

    ImageView inventoryImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/inventory.png")).toExternalForm()));
    ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));

    // whole room
    ImageView panel = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/paneC.png")).toExternalForm()));
    ImageView mainBG = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/bgC.png")).toExternalForm()));
    ImageView bgBody = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/bgBody.png")).toExternalForm()));
    ImageView glassThing = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/smallC.png")).toExternalForm()));
    ImageView door = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/doorC.png")).toExternalForm()));
    ImageView openedDoor = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/doorOpenC.png")).toExternalForm()));
    ImageView crack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/bigC.png")).toExternalForm()));
    ImageView body = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/bodyC.png")).toExternalForm()));
    ImageView dialogue3 = new ImageView(new Image(Objects.requireNonNull((getClass().getResource("/images/AAACircuitLab/heavyC.png")).toExternalForm())));

    // panel scene
    ImageView panelBG = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/zoomPaneC.png")).toExternalForm()));
    ImageView note = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/noteC.png")).toExternalForm()));
    ImageView noteZoomFr = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/note-zoomedF.png")).toExternalForm()));
    ImageView noteZoom = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/note-zoomed.png")).toExternalForm()));
    ImageView ledBroken = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/led-broken.png")).toExternalForm()));
    ImageView head = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/headC.png")).toExternalForm()));
    ImageView ledOn = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/lightC.png")).toExternalForm()));
    ImageView clickableSection = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/placeC.png")).toExternalForm()));
    ImageView dialogue2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/dialogue2.png")).toExternalForm()));
    ImageView fils = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/fils.png")).toExternalForm()));
    ImageView placedResistor = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/placedR.png")).toExternalForm()));

    // head scene
    ImageView headBG = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/zoomHeadC.png")).toExternalForm()));
    ImageView dialogue = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/resistorsC.png")).toExternalForm()));
    ImageView dialogue4 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/sorryC.png")).toExternalForm()));
    ImageView res1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/r1.png")).toExternalForm()));
    ImageView res2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/r2.png")).toExternalForm()));
    ImageView res3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/r3.png")).toExternalForm()));
    ImageView res4 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAACircuitLab/r4.png")).toExternalForm()));

    // sound used when glass is shattered
    String shatterPath = Objects.requireNonNull(GameController.class.getResource("/sounds/glassShatter.mp3")).toExternalForm();
    Media shatterMedia = new Media(shatterPath);
    MediaPlayer shatterSoundPlayer = new MediaPlayer(shatterMedia);

    // sound used when the led explodes
    String shatterPath2 = Objects.requireNonNull(GameController.class.getResource("/sounds/glassShatter2.mp3")).toExternalForm();
    Media shatterMedia2 = new Media(shatterPath2);
    MediaPlayer shatterSoundPlayer2 = new MediaPlayer(shatterMedia2);

    // open door sound
    Media doorMedia = new Media(Objects.requireNonNull(GameController.class.getResource("/sounds/metalDoor.mp3")).toExternalForm());
    MediaPlayer doorSoundPLayer = new MediaPlayer(doorMedia);

    // items
    Item res1Item = new Item("160 Ohm Resistor", "/images/AAACircuitLab/res1item.png");
    Item res2Item = new Item("200 Ohm Resistor", "/images/AAACircuitLab/res2item.png");
    Item res3Item = new Item("120 Ohm Resistor", "/images/AAACircuitLab/res3item.png");
    Item res4Item = new Item("100 Ohm Resistor", "/images/AAACircuitLab/res4item.png");

    // dialogue
    private final ImageView fail = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/gameFail.png")).toExternalForm()));
    private final ImageView failF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/gameFailF.png")).toExternalForm()));
    private final ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/goBack.png")).toExternalForm()));
    private final ImageView retourner = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/retourner.png")).toExternalForm()));
    List<ImageView> monologues = new ArrayList<>();
    List<ImageView> monologuesF = new ArrayList<>();
    List<ImageView> monologuesL = new ArrayList<>();

    public rCircuit(Stage stage) {
        // initialize lab
        this.stage = stage;
        this.overlay = GameController.getOverlay();
        placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");
        makeScene();
        chosenItem = placeHolder;
        head.setVisible(false);
        hideImage(crack);
        hideImage(body);
        hideImage(placedResistor);
    }

    public void start() {
        stage.setScene(makeScene());
        stage.show();
    }

    public Scene makeScene() {
        StackPane stackPane = new StackPane();

        // language control
        if (Objects.equals(player.getLanguage(), "english")) {
            monologuesL.clear();
            monologuesL = monologues;
        } else {
            monologuesL.clear();
            monologuesL = monologuesF;
        }
        monologues.addAll(List.of(fail, goBack));
        monologuesF.addAll(List.of(failF, retourner));

        Button skipToNext = new Button("Skip to next");
        skipToNext.setTranslateX(-400);
        skipToNext.setTranslateY(-300);
        skipToNext.setMinWidth(90);
        skipToNext.setOnAction(e -> passLab());

        hideImage(dialogue4);
        inventoryImage.setMouseTransparent(true);

        // event handlers on main screen
        panel.setOnMouseClicked(e -> panelScene());
        door.setOnMouseClicked(e -> stackPane.getChildren().add(dialogue3));
        openedDoor.setOnMouseClicked(e -> passLab());
        glassThing.setOnMouseClicked(e -> breakGlass());
        head.setOnMouseClicked(e -> inspectHead());

        // event handlers for resistors
        res1Item.getImageView().setOnMouseClicked(e -> chosenItem = res1Item);
        res2Item.getImageView().setOnMouseClicked(e -> chosenItem = res2Item);
        res3Item.getImageView().setOnMouseClicked(e -> chosenItem = res3Item);
        res4Item.getImageView().setOnMouseClicked(e -> chosenItem = res4Item);

        stackPane.getChildren().addAll(mainBG, bgBody, body, panel, glassThing, crack, head, inventoryImage, skipToNext);
        // this checks if the door should be open or not
        if (isLedOn) {
            stackPane.getChildren().add(2, openedDoor);
        } else {
            stackPane.getChildren().add(2, door);
        }

        overlay.getHelpButton().setOnMouseClicked(e -> {
            Image helpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/help/circuitFormula.jpg")));
            if (player.getLanguage().equals("english")) {
                Help.show("Remember this from your E&M class?", helpImage);
            } else {
                Help.show("Te souviens-tu de ton cours d'électricité?", helpImage);
            }
        });

        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        mainScene = new Scene(pane, 1000, 650);

        return mainScene;
    }

    private void goBack() {
        Overlay.playClick();
        stage.setScene(makeScene());
    }

    private void panelScene() {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(panelBG, note, inventoryImage, back, clickableSection, fils, placedResistor);

        // all 3 outcomes to attaching a resistor (whether correct or incorrect)
        if (isLedOn) {
            if (player.isSoundOn()) {
                doorSoundPLayer.seek(new Duration(0));
                doorSoundPLayer.play();
            }
            stackPane.getChildren().addAll(ledOn);
        } else if (resTooLow) {
            if (player.isSoundOn()) {
                shatterSoundPlayer2.seek(new Duration(0));
                shatterSoundPlayer2.play();
            }
            stackPane.getChildren().add(ledBroken);
        }

        if (resTooHigh) {
            stackPane.getChildren().add(dialogue2);
        }

        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> goBack());
        note.setOnMouseClicked(e -> readNote());
        // this is the little section used to put the resistor in
        clickableSection.setOnMouseClicked(e -> {
            if (chosenItem != placeHolder) {
                useItem(e);
                showImage(placedResistor);
            }
        });

        stage.setScene(currentScene);
    }

    private void inspectHead() {
        StackPane stackPane = new StackPane(headBG, res1, res2, res3, res4, inventoryImage, dialogue, dialogue4, back);
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> goBack());


        // all event handlers for collecting the resistors from the head
        res1.setOnMouseClicked(e -> {
            dialogue.setVisible(false);
            dialogue4.setVisible(true);
            res1.setVisible(false);
            overlay.getInventory().addItem(res1Item);
            overlay.updateInventory();
        });

        res2.setOnMouseClicked(e -> {
            dialogue.setVisible(false);
            dialogue4.setVisible(true);
            res2.setVisible(false);
            overlay.getInventory().addItem(res2Item);
            overlay.updateInventory();
        });

        res3.setOnMouseClicked(e -> {
            dialogue.setVisible(false);
            dialogue4.setVisible(true);
            res3.setVisible(false);
            overlay.getInventory().addItem(res3Item);
            overlay.updateInventory();
        });

        res4.setOnMouseClicked(e -> {
            dialogue.setVisible(false);
            dialogue4.setVisible(true);
            res4.setVisible(false);
            overlay.getInventory().addItem(res4Item);
            overlay.updateInventory();
        });

        stage.setScene(currentScene);
    }

    // used when user clicks sticky note
    private void readNote() {
        StackPane stackPane;
        if (player.getLanguage().equals("english")) {
            stackPane = new StackPane(noteZoom, inventoryImage, back);
        } else {
            stackPane = new StackPane(noteZoomFr, inventoryImage, back);
        }
        Pane pane = new Pane(stackPane, overlay.getOverlayPane());
        Scene currentScene = new Scene(pane);
        back.setOnMouseClicked(e -> panelScene());
        stage.setScene(currentScene);
    }

    private void breakGlass() {
        if (player.isSoundOn()) {
            shatterSoundPlayer.play();
        }
        showImage(head);
        body.setVisible(true);
        crack.setVisible(true);
        hideImage(bgBody);
        stage.setScene(makeScene());
        hideImage(glassThing);
    }

    // skip to next lab (spring lab)
    private void passLab() {
        overlay.getInventory().resetInventory();
        SpringLab s = new SpringLab(stage);
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
            showImage(res1);
            chosenItem = placeHolder;
            overlay.getInventory().removeItem(res1Item);
            overlay.updateInventory();
            isLedOn = true;
            panelScene();
        }
        if (chosenItem != null && chosenItem.equals(res2Item)) {
            chosenItem = placeHolder;
            overlay.getInventory().removeItem(res2Item);
            overlay.updateInventory();
            panelScene();
            lifeManager.decreaseLife();
        }
        if (chosenItem != null && chosenItem.equals(res3Item)) {
            chosenItem = placeHolder;
            overlay.getInventory().removeItem(res3Item);
            overlay.updateInventory();
            panelScene();
            lifeManager.decreaseLife();
        }
        if (chosenItem != null && chosenItem.equals(res4Item)) {
            chosenItem = placeHolder;
            overlay.getInventory().removeItem(res4Item);
            overlay.updateInventory();
            panelScene();
            lifeManager.decreaseLife();
        }

        if (lifeManager.getLives() == 0) {
            assert chosenItem != null;
            chosenItem.getImageView().setMouseTransparent(false);
            chosenItem.getImageView().setVisible(true);
            overlay.getInventory().removeItem(chosenItem);
            overlay.updateInventory();
            failLab();
        }
    }
    /**
     * Extracted repeated method for making an image view visible and clickable.
     */
    private void showImage(ImageView image) {
        image.setMouseTransparent(false);
        image.setVisible(true);
    }

    /**
     * Extracted repeated method for making an image view not visible and not clickable.
     */
    private void hideImage(ImageView image) {
        image.setMouseTransparent(true);
        image.setVisible(false);
    }
}
