package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.nio.file.Path;

public class FlameLab {
    Button quit;
    // Flame test
    // Possible flames : 4 colors, right color is the wall paper.
    private final ImageView flameColorCrimsonLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorGreenLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorLilacLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorYellowLab = new ImageView(new Image("file:")); //
    // Chosen flame color.
    // Collect the flame to put on a slot on door
    // My hand! It burns! (wrong color)
    // Thank God I don't have to play with fire anymore... (right color)
    private ImageView flameColor;
    private final ImageView flameColorCrimsonTool = new ImageView(new Image("file:"));
    private final ImageView flameColorGreenTool = new ImageView(new Image("file:"));
    private final ImageView flameColorLilacTool = new ImageView(new Image("file:"));
    private final ImageView flameColorYellowTool = new ImageView(new Image("file:"));
    // Powders to find from microscope (and they appear as the chemical formula)
    // Are those...? (If inventory doesn't have powder yet)
    private final ImageView licl = new ImageView(new Image("file:1.webp"));
    private final ImageView bacl2 = new ImageView(new Image("file:"));
    private final ImageView kcl = new ImageView(new Image("file:"));
    private final ImageView nacl = new ImageView(new Image("file:"));
    private final ImageView liclTool = new ImageView(new Image("file:1.webp"));
    private final ImageView bacl2Tool = new ImageView(new Image("file:"));
    private final ImageView kclTool = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView naclTool = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    // Bunsen burner blue flame. Found from
    // Now let me cook.
    private final ImageView bunsenBurner = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView BunsenBurnerTool = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView bunsenBurnerLab = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    // Wire loop. Found from
    private final ImageView wireLoop = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView wireLoopTool = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView wireLoopLab = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    // Test tubes: 3, be on table.
    // If try to put in used tube: It's already filled.
    private final ImageView testTubesLab = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    // Tiny paper hint on table says: "Look around you"
    // Other images needed.
    private final ImageView microscope = new ImageView(new Image(getClass().getResource("/images/microF.png").toExternalForm()));
    private final ImageView drawerLab = new ImageView(new Image(getClass().getResource("/images/bigF.png").toExternalForm()));
    private final ImageView labSet = new ImageView(new Image(getClass().getResource("/images/bunsenF.png").toExternalForm()));
    private final ImageView drawerMic = new ImageView(new Image(getClass().getResource("/images/smallF.png").toExternalForm()));
    private final ImageView door = new ImageView(new Image(getClass().getResource("/images/doorF.png").toExternalForm()));
    private final ImageView flame = new ImageView(new Image(getClass().getResource("/images/flameF.png").toExternalForm()));
    private final ImageView wall = new ImageView(new Image(getClass().getResource("/images/bgF.png").toExternalForm()));
    private final ImageView bats = new ImageView(new Image(getClass().getResource("/images/batF.png").toExternalForm()));
    private final ImageView batsFly = new ImageView(new Image(getClass().getResource("/images/batFlyF.png").toExternalForm()));
    private final ImageView inventory = new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm()));

    // Zooms
    private final ImageView doorZoom = new ImageView(new Image(getClass().getResource("/images/zoomDoorF.png").toExternalForm()));
    private final ImageView flameZoom = new ImageView(new Image(getClass().getResource("/images/zoomFlameF.png").toExternalForm()));
    // background
    // start/end

    // Sounds
    String batPath = getClass().getResource("/sounds/bat.mp3").toExternalForm();
    Media batMedia = new Media(batPath);
    MediaPlayer batPlayer = new MediaPlayer(batMedia);
    String batFlyPath = getClass().getResource("/sounds/batFly.mp3").toExternalForm();
    Media batFlyMedia = new Media(batFlyPath);
    MediaPlayer batFlyPlayer = new MediaPlayer(batFlyMedia);

    public void startLab(Stage stage) {
        // Set bats for start.
        batsFly.setVisible(false);
        batsFly.setMouseTransparent(true);
        inventory.setMouseTransparent(true);
        batPlayer.setOnEndOfMedia (() -> {
            batPlayer.stop();
        });
        batFlyPlayer.setOnEndOfMedia (() -> {
            batFlyPlayer.stop();
        });

        // Call methods.
        scareBats();

        // Set up main layout.
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().addAll(wall, drawerMic, microscope, drawerLab, labSet, door, flame, bats, batsFly, inventory);

        Scene scene = new Scene(mainLayout);

        door.setOnMouseClicked(e -> {
            Scene zoomScene = new Scene(zoomDoor());
            stage.setScene(zoomScene);
        });
        quit = new Button("back");
        quit.setOnAction(e -> {
            stage.setScene(scene);
        });

        stage.setScene(scene);
    }

    private void scareBats() {
        Timeline batTime = new Timeline(new KeyFrame(Duration.seconds(2), event -> {batsFly.setVisible(false);}));
        bats.setOnMouseClicked(e -> {
            bats.setMouseTransparent(true);
            batPlayer.play();
            Timeline batVisibleTime = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {bats.setVisible(false);batsFly.setVisible(true);}));
            Timeline batSoundTime = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {batFlyPlayer.play();}));
            batTime.playFromStart();
            batSoundTime.playFromStart();
            batVisibleTime.playFromStart();
        });
    }

    private StackPane zoomDoor() {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(doorZoom);
        stackPane.getChildren().add(flameZoom);
        //stackPane.getChildren().add(inventory);
        stackPane.getChildren().add(quit);
        return stackPane;
    }

    public boolean checkSolution() {
        return false;
    }

    public void failLab() {

    }

    public void setupLab() {

    }

    public Scene createScene() {
        return null;
    }

    public void changeFlameColor() {

    }
}
