package com.example.escape_the_lab.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class FlameLab {
    // Flame test
    // Possible flames : 4 colors, right color is the wall color.
    private final ImageView flameColorCrimsonLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorGreenLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorLilacLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorYellowLab = new ImageView(new Image("file:")); //
    // Chosen flame color.
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
    private final ImageView bunsenBurner = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView BunsenBurnerTool = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView bunsenBurnerLab = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    // Wire loop. Found from
    private final ImageView wireLoop = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView wireLoopTool = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    private final ImageView wireLoopLab = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
    // Test tubes: 3, be on table.
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

    // Zooms.
    private final ImageView doorZoom = new ImageView(new Image(getClass().getResource("/images/zoomDoorF.png").toExternalForm()));
    private final ImageView flameZoom = new ImageView(new Image(getClass().getResource("/images/zoomFlameF.png").toExternalForm()));

    ImageView zoomedMicroscope;

    ImageView powder;

    // Monolog.
    private final ImageView monoPass = new ImageView(new Image(getClass().getResource("/images/pass.png").toExternalForm()));
    private final ImageView monoPassF = new ImageView(new Image(getClass().getResource("/images/passF.png").toExternalForm()));
    private final ImageView monoFail = new ImageView(new Image(getClass().getResource("/images/fail.png").toExternalForm()));
    private final ImageView monoFailF = new ImageView(new Image(getClass().getResource("/images/failF.png").toExternalForm()));
    private final ImageView monoLab = new ImageView(new Image(getClass().getResource("/images/labTalk.png").toExternalForm()));
    private final ImageView monoLabF = new ImageView(new Image(getClass().getResource("/images/labTalkF.png").toExternalForm()));
    private final ImageView monoFind = new ImageView(new Image(getClass().getResource("/images/find.png").toExternalForm()));
    private final ImageView monoFindF = new ImageView(new Image(getClass().getResource("/images/findF.png").toExternalForm()));

    List<ImageView> monologues = new ArrayList<>();
    List<ImageView> monologuesF = new ArrayList<>();
    List<ImageView> monologuesL = new ArrayList<>();

    // Sounds.
    String batPath = getClass().getResource("/sounds/bat.mp3").toExternalForm();
    Media batMedia = new Media(batPath);
    MediaPlayer batPlayer = new MediaPlayer(batMedia);
    String batFlyPath = getClass().getResource("/sounds/batFly.mp3").toExternalForm();
    Media batFlyMedia = new Media(batFlyPath);
    MediaPlayer batFlyPlayer = new MediaPlayer(batFlyMedia);

    // My items.
    List<ImageView> myPossibleItems = new ArrayList<>();
    int chosenDigit;
    ImageView chosenItem;

    // Items.
    // a list for imageviews, that are what ive collected (6 max).
    // a list of max length 1, that is what is chosen atm.
    // if chosen contains xxx while clicking sth, reaction.

    public void startLab(Stage stage) {
        // Set up language system.
        GameController controller = new GameController();
        boolean l = controller.language;
        if (l) {
            monologuesL.clear();
            monologuesL = monologues;
        } else if (!l) {
            monologuesL.clear();
            monologuesL = monologuesF;
        }
        monologues.addAll(List.of(monoPass, monoFail, monoFind, monoLab));
        monologuesF.addAll(List.of(monoPassF, monoFailF, monoFindF, monoLabF));

        // Set up items list.
        chosenItem = myPossibleItems.get(chosenDigit);
        zoomedMicroscope.setOnMouseClicked(e -> {
            if (chosenItem.equals(powder)) {
                licl.setVisible(true);
                licl.setMouseTransparent(false);
                bacl2.setVisible(true);
                bacl2.setMouseTransparent(false);
                kcl.setVisible(true);
                kcl.setMouseTransparent(false);
                nacl.setVisible(true);
                nacl.setMouseTransparent(false);
            }
        });

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
        scareBats();

        // Set up main layout.
        StackPane mainLayout = new StackPane();
        flame.setMouseTransparent(true);
        mainLayout.getChildren().addAll(wall, drawerMic, microscope, drawerLab, labSet, door, flame, bats, batsFly, inventory);
        Scene scene = new Scene(mainLayout);

        // Set up basic mouse click actions.
        door.setOnMouseClicked(e -> {
            Scene zoomDScene = new Scene(zoomDoor(stage, scene));
            stage.setScene(zoomDScene);
        });
        drawerMic.setOnMouseClicked(e -> {
            Scene zoomDMScene = new Scene(zoomSmall(stage, scene));
            stage.setScene(zoomDMScene);
        });
        drawerLab.setOnMouseClicked(e -> {
            Scene zoomDLScene = new Scene(zoomBig(stage, scene));
            stage.setScene(zoomDLScene);
        });
        microscope.setOnMouseClicked(e -> {
            Scene zoomMScene = new Scene(zoomMicro(stage, scene));
            stage.setScene(zoomMScene);
        });
        labSet.setOnMouseClicked(e -> {
            Scene zoomLScene = new Scene(zoomLab(stage, scene));
            stage.setScene(zoomLScene);
        });

        stage.setScene(scene);
    }

    private void scareBats() {
        Timeline batTime = new Timeline(new KeyFrame(Duration.seconds(1.9), event -> {batsFly.setVisible(false);}));
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

    private StackPane zoomDoor(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(doorZoom);
        stackPane.getChildren().add(flameZoom);
        stackPane.getChildren().add(new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm())));
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        imageView.setOnMouseClicked(e -> {
            stage.setScene(scene);
        });
        stackPane.getChildren().add(imageView);

        // Button for monolog test
        Button b = new Button();
        stackPane.getChildren().add(b);
        b.setOnAction(e -> {
            stackPane.getChildren().add(monologuesL.get(0));
        });
        return stackPane;
    }

    private StackPane zoomBig(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        //stackPane.getChildren().add(doorZoom);
        stackPane.getChildren().add(new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm())));
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        imageView.setOnMouseClicked(e -> {
            stage.setScene(scene);
        });
        stackPane.getChildren().add(imageView);
        return stackPane;
    }

    private StackPane zoomSmall(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        //stackPane.getChildren().add(doorZoom);
        stackPane.getChildren().add(new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm())));
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        imageView.setOnMouseClicked(e -> {
            stage.setScene(scene);
        });
        stackPane.getChildren().add(imageView);
        return stackPane;
    }

    private StackPane zoomMicro(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        //stackPane.getChildren().add(doorZoom);
        stackPane.getChildren().add(new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm())));
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        imageView.setOnMouseClicked(e -> {
            stage.setScene(scene);
        });
        stackPane.getChildren().add(imageView);

        // Button for monolog test
        Button b = new Button();
        stackPane.getChildren().add(b);
        b.setOnAction(e -> {
            stackPane.getChildren().add(monologuesL.get(2));
        });
        return stackPane;
    }

    private StackPane zoomLab(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        //stackPane.getChildren().add(doorZoom);
        stackPane.getChildren().add(new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm())));
        ImageView imageView = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        imageView.setOnMouseClicked(e -> {
            stage.setScene(scene);
        });
        stackPane.getChildren().add(imageView);
        return stackPane;
    }
}