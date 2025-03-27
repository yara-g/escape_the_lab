package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.ui.Inventory;
import com.example.escape_the_lab.ui.Overlay;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
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
import java.util.ArrayList;
import java.util.List;

public class FlameLab {
    // Chosen sound to tell chose a tool. Wire: find and tool, no lab. just click sol, fire turn auto.
    /// Possible tools. 11 in total.
    private final ImageView flameColorCrimsonTool = new ImageView(new Image("file:"));
    private final ImageView flameColorGreenTool = new ImageView(new Image("file:"));
    private final ImageView flameColorLilacTool = new ImageView(new Image("file:"));
    private final ImageView flameColorYellowTool = new ImageView(new Image("file:"));
    private final Item liclTool = new Item("LiCl", "/images/AAAFlameLab/liclTool.jpg");
    private final ImageView bacl2Tool = new ImageView(new Image("file:"));
    //private final ImageView kclTool = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/start-bg.png").toExternalForm()));
    //private final ImageView naclTool = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/start-bg.png").toExternalForm()));
    private final Item bunsenBurnerTool = new Item("Bunsen Burner", "/images/AAAFlameLab/bunsenTool.jpg");
    private final ImageView wireLoopTool = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/wireLoopTool.jpg").toExternalForm()));
    private final ImageView powderTool =new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/powderTool.jpg").toExternalForm()));
    /// Main page.
    //private final ImageView inventoryImage = new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm()));
    private final ImageView microscope = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/microF.png").toExternalForm()));
    private final ImageView drawerLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/bigF.png").toExternalForm()));
    private final ImageView labSet = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/bunsenF.png").toExternalForm()));
    private final ImageView drawerMic = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/smallF.png").toExternalForm()));
    private final ImageView door = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/doorF.png").toExternalForm()));
    private final ImageView flame = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/flameF.png").toExternalForm()));
    private final ImageView wall = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/bgF.png").toExternalForm()));
    private final ImageView bats = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/batF.png").toExternalForm()));
    private final ImageView batsFly = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/batFlyF.png").toExternalForm()));
    /// Lab page.
    private final ImageView bunsenBurnerLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/bunsenLabF.png").toExternalForm()));
    private final ImageView flameColorCrimsonLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/crimLabF.png").toExternalForm()));
    private final ImageView flameColorGreenLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/greenLabF.png").toExternalForm()));
    private final ImageView flameColorLilacLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/lilacLabF.png").toExternalForm()));
    private final ImageView flameColorYellowLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/yellowLabF.png").toExternalForm()));
    private final ImageView paperF = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/paperF.png").toExternalForm()));
    private final ImageView sol1F = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/sol1F.png").toExternalForm()));
    private final ImageView sol2F = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/sol2F.png").toExternalForm()));
    private final ImageView sol3F = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/sol3F.png").toExternalForm()));
    private final ImageView tube1F = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/tube1F.png").toExternalForm()));
    private final ImageView tube2F = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/tube2F.png").toExternalForm()));
    private final ImageView tube3F = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/tube3F.png").toExternalForm()));
    private final ImageView zoomLabF = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/zoomLabF.png").toExternalForm()));
    /// Big drawer page.
    private final ImageView closedBig = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/closedF.png").toExternalForm()));
    private final ImageView openBig = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/openF.png").toExternalForm()));
    private final ImageView drawerBunsen = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/drawerBunsenF.png").toExternalForm()));
    /// Small drawer page.
    //private final ImageView wireLoop = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/start-bg.png").toExternalForm()));
    /// Microscope page.
    ImageView zoomedMicroscope;
    private final ImageView licl = new ImageView(new Image("file:1.webp"));
    private final ImageView bacl2 = new ImageView(new Image("file:"));
    private final ImageView kcl = new ImageView(new Image("file:"));
    private final ImageView nacl = new ImageView(new Image("file:"));
    /// Door page.
    private final ImageView doorZoom = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/zoomDoorF.png").toExternalForm()));
    private final ImageView flameZoom = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/zoomFlameF.png").toExternalForm()));
    private final ImageView flameZoomRight = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/flameRightF.png").toExternalForm()));
    /// Monologue.
    //ADD THE PAPER ONE. LOOK AROUND YOU
    private final ImageView monoPass = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/pass.png").toExternalForm()));
    private final ImageView monoPassF = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/passF.png").toExternalForm()));
    private final ImageView monoFail = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/fail.png").toExternalForm()));
    private final ImageView monoFailF = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/failF.png").toExternalForm()));
    private final ImageView monoLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/labTalk.png").toExternalForm()));
    private final ImageView monoLabF = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/labTalkF.png").toExternalForm()));
    private final ImageView monoFind = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/find.png").toExternalForm()));
    private final ImageView monoFindF = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/findF.png").toExternalForm()));
    List<ImageView> monologues = new ArrayList<>();
    List<ImageView> monologuesF = new ArrayList<>();
    List<ImageView> monologuesL = new ArrayList<>();
    /// Sounds.
    String batPath = getClass().getResource("/sounds/bat.mp3").toExternalForm();
    Media batMedia = new Media(batPath);
    MediaPlayer batPlayer = new MediaPlayer(batMedia);
    String batFlyPath = getClass().getResource("/sounds/batFly.mp3").toExternalForm();
    Media batFlyMedia = new Media(batFlyPath);
    MediaPlayer batFlyPlayer = new MediaPlayer(batFlyMedia);
    String doorCreak = getClass().getResource("/sounds/doorCreak.mp3").toExternalForm();
    Media doorMedia = new Media(doorCreak);
    MediaPlayer doorPlayer = new MediaPlayer(doorMedia);

    //private final ImageView wireLoopLab = new ImageView(new Image(getClass().getResource("/images/AAAFlameLab/start-bg.png").toExternalForm()));

    Overlay overlay;
    Inventory inventory;
    Group inventoryPane;
    StackPane mainLayout;
    
    public void startLab(Stage stage, Overlay overlay) {
        /// Set up language system.
        if (GameController.language) {
            monologuesL.clear();
            monologuesL = monologues;
        } else {
            monologuesL.clear();
            monologuesL = monologuesF;
        }
        monologues.addAll(List.of(monoPass, monoFail, monoFind, monoLab));
        monologuesF.addAll(List.of(monoPassF, monoFailF, monoFindF, monoLabF));

        /// Set up inventory.
        this.overlay = overlay;
        this.inventory = overlay.getInventory();
        this.inventoryPane = overlay.getOverlayPane();

        /// Set up to start the lab.
        this.mainLayout = new StackPane();
        scareBat();
        initialize();

        this.mainLayout.getChildren().addAll(wall, drawerMic, microscope, drawerLab, labSet, door, flame, bats, batsFly);
        addInventory(this.mainLayout);
        Pane pane = new Pane(this.mainLayout, this.inventoryPane);
        Scene scene = new Scene(pane);
        zoomMain(stage, scene);
        stage.setScene(scene);
    }

    private void back(Stage stage) {
        this.mainLayout = new StackPane();
        this.mainLayout.getChildren().addAll(wall, drawerMic, microscope, drawerLab, labSet, door, flame, bats, batsFly);
        addInventory(this.mainLayout);
        Pane pane = new Pane(this.mainLayout, this.inventoryPane);
        Scene scene = new Scene(pane);
        zoomMain(stage, scene);
        stage.setScene(scene);
    }

    private Pane zoomDoor(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        back.setOnMouseClicked(e -> {
            back(stage);
        });

        stackPane.getChildren().addAll(List.of(doorZoom, flameZoom, flameZoomRight));
        addInventory(stackPane);
        stackPane.getChildren().add(back);

        // Button for monologue test
        Button b = new Button();
        stackPane.getChildren().add(b);
        b.setOnAction(e -> {
            stackPane.getChildren().add(monologuesL.get(0));
        });
        return new Pane(stackPane, inventoryPane);
    }

    private Pane zoomBig(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        back.setOnMouseClicked(e -> {
            back(stage);
        });

        stackPane.getChildren().addAll(List.of(closedBig, openBig, drawerBunsen));
        addInventory(stackPane);
        stackPane.getChildren().add(back);

        closedBig.setOnMouseClicked(e -> {
            doorPlayer.play();
            openBig.setVisible(true);
            drawerBunsen.setVisible(true);
            drawerBunsen.setMouseTransparent(false);
        });

        drawerBunsen.setOnMouseClicked(e -> {
            drawerBunsen.setVisible(false);
            drawerBunsen.setMouseTransparent(true);

            inventory.addItem(bunsenBurnerTool);

            overlay.updateInventory();
        });
        return new Pane(stackPane, inventoryPane);
    }
/*
    xxx.setOnMouseClicked(e -> {
        if (this.bunsenBurnerTool.isItemSelected()) {

        }
    }
 */
    private Pane zoomSmall(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        back.setOnMouseClicked(e -> {
            back(stage);
        });

        //stackPane.getChildren().add(doorZoom);
        addInventory(stackPane);
        stackPane.getChildren().add(back);

        return new Pane(stackPane, inventoryPane);
    }

    private Pane zoomMicro(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        back.setOnMouseClicked(e -> {
            back(stage);
        });

        //stackPane.getChildren().add(doorZoom);
        addInventory(stackPane);
        stackPane.getChildren().add(back);

        // Button for monolog test
        Button b = new Button();
        stackPane.getChildren().add(b);
        b.setOnAction(e -> {
            stackPane.getChildren().add(monologuesL.get(2));
        });
        return new Pane(stackPane, inventoryPane);
    }

    private Pane zoomLab(Stage stage, Scene scene) {
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(getClass().getResource("/images/back.png").toExternalForm()));
        back.setOnMouseClicked(e -> {
            back(stage);
        });

        stackPane.getChildren().addAll(List.of(zoomLabF, bunsenBurnerLab, paperF, sol1F, sol2F, sol3F, tube1F, tube2F, tube3F, flameColorCrimsonLab, flameColorGreenLab, flameColorLilacLab, flameColorYellowLab));
        addInventory(stackPane);
        stackPane.getChildren().add(back);

        paperF.setOnMouseClicked(e -> {
            // Look Around you
        });

        return new Pane(stackPane, inventoryPane);
    }

    /**
     * Extracted settings of bat animation from start to a method for order.
     */
    private void scareBat() {
        batsFly.setVisible(false);
        batsFly.setMouseTransparent(true);

        batPlayer.setOnEndOfMedia (() -> {batPlayer.stop();});
        batFlyPlayer.setOnEndOfMedia (() -> {batFlyPlayer.stop();});
        Timeline batTime = new Timeline(new KeyFrame(Duration.seconds(1.9), event -> {batsFly.setVisible(false);}));

        bats.setOnMouseClicked(e -> {
            bats.setMouseTransparent(true);
            batPlayer.play();

            Timeline batVisibleTime = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {bats.setVisible(false); batsFly.setVisible(true);}));
            Timeline batSoundTime = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {batFlyPlayer.play();}));
            batTime.playFromStart();
            batSoundTime.playFromStart();
            batVisibleTime.playFromStart();
        });
    }

    /**
     * Initialize the original state of certain media.
     */
    private void initialize() {
        flame.setMouseTransparent(true);

        flameZoomRight.setMouseTransparent(true);
        flameZoomRight.setVisible(false);

        openBig.setMouseTransparent(true);
        openBig.setVisible(false);
        drawerBunsen.setMouseTransparent(true);
        drawerBunsen.setVisible(false);
        doorPlayer.setOnEndOfMedia (() -> {
            doorPlayer.stop();
        });

        sol1F.setMouseTransparent(true);
        sol1F.setVisible(false);
        sol2F.setMouseTransparent(true);
        sol2F.setVisible(false);
        sol3F.setMouseTransparent(true);
        sol3F.setVisible(false);
        bunsenBurnerLab.setMouseTransparent(true);
        bunsenBurnerLab.setVisible(false);
        flameColorCrimsonLab.setMouseTransparent(true);
        flameColorGreenLab.setMouseTransparent(true);
        flameColorLilacLab.setMouseTransparent(true);
        flameColorYellowLab.setMouseTransparent(true);
        flameColorCrimsonLab.setVisible(false);
        flameColorGreenLab.setVisible(false);
        flameColorLilacLab.setVisible(false);
        flameColorYellowLab.setVisible(false);
    }

    /**
     * Set up mouse click actions for the main scene.
     */
    private void zoomMain(Stage stage, Scene scene) {
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
        stage.setOnShown(event -> {
            if (!mainLayout.getChildren().contains(inventoryPane)) {
                mainLayout.getChildren().add(inventoryPane);
            }
        });
    }

    private void addInventory(StackPane stackPane) {
        ImageView inventoryImage = new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm()));
        inventoryImage.setMouseTransparent(true);
        stackPane.getChildren().add(inventoryImage);
    }
}
