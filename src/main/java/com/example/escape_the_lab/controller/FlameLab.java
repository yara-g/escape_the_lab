package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.model.Player;
import com.example.escape_the_lab.ui.Inventory;
import com.example.escape_the_lab.ui.Overlay;
import javafx.animation.*;
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
import java.util.Objects;

public class FlameLab {
    // Chosen sound to tell chose a tool. Wire: find and tool, no lab. just click sol, fire turn auto.
    /// Possible tools. 14 in total.
    // <editor-fold>
    private final Item flameColorCrimsonTool = new Item("Crimson Flame", "/images/AAAFlameLab/crimTool.png");
    private final Item flameColorGreenTool = new Item("Green Flame", "/images/AAAFlameLab/greTool.png");
    private final Item flameColorLilacTool = new Item("Lilac Flame", "/images/AAAFlameLab/lilTool.png");
    private final Item flameColorYellowTool = new Item("Yellow Flame", "/images/AAAFlameLab/yelTool.png");
    private final Item liclTool = new Item("LiCl", "/images/AAAFlameLab/liTool.png");
    private final Item bacl2Tool = new Item("BaCl2", "/images/AAAFlameLab/baTool.png");
    private final Item kclTool = new Item("KCl", "/images/AAAFlameLab/kTool.png");
    private final Item naclTool = new Item("NaCl", "/images/AAAFlameLab/naTool.png");
    private final Item bunsenBurnerTool = new Item("Bunsen Burner", "/images/AAAFlameLab/bunsenTool.png");
    private final Item powderTool = new Item("Powder", "/images/AAAFlameLab/powTool.png");
    private final Item tubeToolK = new Item("Tube", "/images/AAAFlameLab/tubeToolK.png");
    private final Item tubeToolB = new Item("Tube", "/images/AAAFlameLab/tubeToolB.png");
    private final Item tubeToolL = new Item("Tube", "/images/AAAFlameLab/tubeToolL.png");
    private final Item tubeToolN = new Item("Tube", "/images/AAAFlameLab/tubeToolN.png");
    // </editor-fold>
    /// Main page.
    // <editor-fold>
    private final ImageView microscope = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/microF.png")).toExternalForm()));
    private final ImageView drawerLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/bigF.png")).toExternalForm()));
    private final ImageView labSet = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/bunsenUnplacedF.png")).toExternalForm()));
    private final ImageView labSetShow = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/bunsenF.png")).toExternalForm()));
    private final ImageView drawerMic = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/smallF.png")).toExternalForm()));
    private final ImageView door = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/doorF.png")).toExternalForm()));
    private final ImageView flame = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/flameF.png")).toExternalForm()));
    private final ImageView wall = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/bgF.png")).toExternalForm()));
    private final ImageView bats = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/batF.png")).toExternalForm()));
    private final ImageView batsFly = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/batFlyF.png")).toExternalForm()));
    // </editor-fold>
    /// Lab page.
    // <editor-fold>
    private final ImageView bunsenBurnerLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/bunsenLabF.png")).toExternalForm()));
    private final ImageView flameColorCrimsonLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/crimLabF.png")).toExternalForm()));
    private final ImageView flameColorGreenLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/greenLabF.png")).toExternalForm()));
    private final ImageView flameColorLilacLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/lilacLabF.png")).toExternalForm()));
    private final ImageView flameColorYellowLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/yellowLabF.png")).toExternalForm()));
    private final ImageView paperF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/paperF.png")).toExternalForm()));
    private final ImageView full1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/fullTube1.png")).toExternalForm()));
    private final ImageView full2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/fullTube2.png")).toExternalForm()));
    private final ImageView full3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/fullTube3.png")).toExternalForm()));
    private final ImageView tube1F = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/tube1F.png")).toExternalForm()));
    private final ImageView tube2F = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/tube2F.png")).toExternalForm()));
    private final ImageView tube3F = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/tube3F.png")).toExternalForm()));
    private final ImageView zoomLabF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/zoomLabF.png")).toExternalForm()));
    // </editor-fold>
    /// Big drawer page.
    // <editor-fold>
    private final ImageView closedBig = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/closedF.png")).toExternalForm()));
    private final ImageView openBig = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/openF.png")).toExternalForm()));
    private final ImageView drawerBunsen = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/drawerBunsenF.png")).toExternalForm()));
    // </editor-fold>
    /// Small drawer page.
    // <editor-fold>
    private final ImageView smallZoom = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/zoomSmallF.png")).toExternalForm()));
    private final ImageView powderZoom = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/powderSmallF.png")).toExternalForm()));
    // </editor-fold>
    /// Microscope page.
    // <editor-fold>
    private final ImageView zoomedMicroscope = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/zoomMicroF.png")).toExternalForm()));
    private final ImageView zoomedPowderMicro = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/zoomMicroPowderF.png")).toExternalForm()));
    private final ImageView licl = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/LiClzoomF.png")).toExternalForm()));
    private final ImageView bacl2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/BaCl2zoomF.png")).toExternalForm()));
    private final ImageView kcl = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/KClzoomF.png")).toExternalForm()));
    private final ImageView nacl = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/NaClzoomF.png")).toExternalForm()));
    // </editor-fold>
    /// Door page.
    // <editor-fold>
    private final ImageView doorZoom = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/zoomDoorF.png")).toExternalForm()));
    private final ImageView flameZoom = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/zoomFlameF.png")).toExternalForm()));
    private final ImageView flameZoomRight = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/flameRightF.png")).toExternalForm()));
    // </editor-fold>
    /// Monologue.
    // <editor-fold>
    private final ImageView fail = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/gameFail.png")).toExternalForm()));
    private final ImageView failF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/gameFailF.png")).toExternalForm()));
    private final ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/goBack.png")).toExternalForm()));
    private final ImageView retourner = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/retourner.png")).toExternalForm()));
    private final ImageView monoPass = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/pass.png")).toExternalForm()));
    private final ImageView monoPassF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/passF.png")).toExternalForm()));
    private final ImageView monoFail = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/fail.png")).toExternalForm()));
    private final ImageView monoFailF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/failF.png")).toExternalForm()));
    private final ImageView monoLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/labTalk.png")).toExternalForm()));
    private final ImageView monoLabF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/labTalkF.png")).toExternalForm()));
    private final ImageView monoFind = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/find.png")).toExternalForm()));
    private final ImageView monoFindF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/findF.png")).toExternalForm()));
    private final ImageView monoPaper = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/monoPaper.png")).toExternalForm()));
    private final ImageView monoPaperF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAFlameLab/monoPaperF.png")).toExternalForm()));
    List<ImageView> monologues = new ArrayList<>();
    List<ImageView> monologuesF = new ArrayList<>();
    List<ImageView> monologuesL = new ArrayList<>();
    // </editor-fold>
    /// Sounds.
    // <editor-fold>
    String batPath = Objects.requireNonNull(getClass().getResource("/sounds/bat.mp3")).toExternalForm();
    Media batMedia = new Media(batPath);
    MediaPlayer batPlayer = new MediaPlayer(batMedia);
    String batFlyPath = Objects.requireNonNull(getClass().getResource("/sounds/batFly.mp3")).toExternalForm();
    Media batFlyMedia = new Media(batFlyPath);
    MediaPlayer batFlyPlayer = new MediaPlayer(batFlyMedia);
    String doorCreak = Objects.requireNonNull(getClass().getResource("/sounds/doorCreak.mp3")).toExternalForm();
    Media doorMedia = new Media(doorCreak);
    MediaPlayer doorPlayer = new MediaPlayer(doorMedia);
    // </editor-fold>
    /// Other useful variables.
    // <editor-fold>
    private Overlay overlay;
    private Inventory inventory;
    private LifeManager lifeManager;
    private Group inventoryPane;
    private StackPane mainLayout;
    private Item chosenItem;
    Item placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");
    // </editor-fold>
    Button b;
    private final Player player = GameController.getPlayer();

    /**
     * Call this to start the flame lab!
     * @param stage Stage of game.
     */
    public void startLab(Stage stage) {
        /// Set up language system.
        if (Objects.equals(player.getLanguage(), "english")) {
            monologuesL.clear();
            monologuesL = monologues;
        } else {
            monologuesL.clear();
            monologuesL = monologuesF;
        }
        monologues.addAll(List.of(monoPass, monoFail, monoFind, monoLab, monoPaper, fail, goBack));
        monologuesF.addAll(List.of(monoPassF, monoFailF, monoFindF, monoLabF, monoPaperF, failF, retourner));
        /// Set up inventory.
        this.overlay = GameController.getOverlay();
        this.inventory = overlay.getInventory();
        this.inventoryPane = overlay.getOverlayPane();
        this.lifeManager = GameController.getLifeManager();
        /// Set up to start the lab.
        scareBat();
        initialize();
        this.mainLayout = new StackPane();

        // Temp button for acid.
        b = new Button("skip");
        b.setOnAction(e -> {
            AcidNeutralizationLab a = new AcidNeutralizationLab(stage);
            a.startLab();
        });

        this.mainLayout.getChildren().addAll(wall, drawerMic, microscope, drawerLab, labSet, labSetShow, door, flame, bats, batsFly, b);
        addInventory(this.mainLayout);
        Pane pane = new Pane(this.mainLayout, this.inventoryPane);
        Scene scene = new Scene(pane);
        zoomMain(stage);
        stage.setScene(scene);
    }

    /**
     * Call this to go back from a zoomed scene to main scene.
     * @param stage Stage.
     */
    private void back(Stage stage) {
        this.mainLayout = new StackPane();
        this.mainLayout.getChildren().addAll(wall, drawerMic, microscope, drawerLab, labSet, labSetShow, door, flame, bats, batsFly, b);
        addInventory(this.mainLayout);
        Pane pane = new Pane(this.mainLayout, this.inventoryPane);
        Scene scene = new Scene(pane);
        zoomMain(stage);
        stage.setScene(scene);
    }

    /**
     * The zoomed scene of door.
     * @param stage Stage.
     * @return Pane of door.
     */
    private Pane zoomDoor(Stage stage) {
        /// Set up scene.
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        back.setOnMouseClicked(e -> back(stage));
        stackPane.getChildren().addAll(List.of(doorZoom, flameZoom, flameZoomRight));
        addInventory(stackPane);
        stackPane.getChildren().add(back);
        flameZoom.setOnMouseClicked(e -> useItem(flameZoom, stackPane, stage));
        return new Pane(stackPane, inventoryPane);
    }

    /**
     * The zoomed scene of big drawer.
     * @param stage Stage.
     * @return Pane of big drawer.
     */
    private Pane zoomBig(Stage stage) {
        /// Set up scene.
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        back.setOnMouseClicked(e -> back(stage));
        stackPane.getChildren().addAll(List.of(closedBig, openBig, drawerBunsen));
        addInventory(stackPane);
        stackPane.getChildren().add(back);
        /// Set up actions for all interactive image views.
        closedBig.setOnMouseClicked(e -> {
            if (player.isSoundOn()) {
                doorPlayer.play();
            }
            openBig.setVisible(true);
            showImage(drawerBunsen);
            closedBig.setMouseTransparent(true);
        });
        drawerBunsen.setOnMouseClicked(e -> addIntoInventory(drawerBunsen, bunsenBurnerTool));
        return new Pane(stackPane, inventoryPane);
    }

    /**
     * The zoomed scene of small drawer.
     * @param stage Stage.
     * @return Pane of small drawer.
     */
    private Pane zoomSmall(Stage stage) {
        /// Set up scene.
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        back.setOnMouseClicked(e -> back(stage));
        stackPane.getChildren().addAll(List.of(smallZoom, powderZoom));
        addInventory(stackPane);
        stackPane.getChildren().add(back);
        /// Set up actions for all interactive image views.
        powderZoom.setOnMouseClicked(e -> addIntoInventory(powderZoom, powderTool));
        return new Pane(stackPane, inventoryPane);
    }

    /**
     * The zoomed scene of microscope.
     * @param stage Stage.
     * @return Pane of microscope.
     */
    private Pane zoomMicro(Stage stage) {
        /// Set up scene.
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        back.setOnMouseClicked(e -> back(stage));
        stackPane.getChildren().addAll(List.of(zoomedMicroscope, zoomedPowderMicro, nacl, licl, bacl2, kcl));
        addInventory(stackPane);
        stackPane.getChildren().add(back);
        /// Set up actions for all interactive image views.
        zoomedMicroscope.setOnMouseClicked(e -> useItem(zoomedMicroscope, stackPane, stage));
        bacl2.setOnMouseClicked(e -> addIntoInventory(bacl2, bacl2Tool));
        kcl.setOnMouseClicked(e -> addIntoInventory(kcl, kclTool));
        nacl.setOnMouseClicked(e -> addIntoInventory(nacl, naclTool));
        licl.setOnMouseClicked(e -> addIntoInventory(licl, liclTool));
        return new Pane(stackPane, inventoryPane);
    }

    /**
     * The zoomed scene of lab set.
     * @param stage Stage.
     * @return Pane of lab set.
     */
    private Pane zoomLab(Stage stage) {
        /// Set up scene.
        StackPane stackPane = new StackPane();
        ImageView back = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        back.setOnMouseClicked(e -> back(stage));
        stackPane.getChildren().addAll(List.of(zoomLabF, bunsenBurnerLab, paperF, full1, full2, full3, tube1F, tube2F, tube3F, flameColorCrimsonLab, flameColorGreenLab, flameColorLilacLab, flameColorYellowLab));
        addInventory(stackPane);
        stackPane.getChildren().add(back);
        /// Set up actions for all interactive image views.
        paperF.setOnMouseClicked(e -> {
            stackPane.getChildren().remove(monologuesL.get(3));
            stackPane.getChildren().add(monologuesL.get(4));
            paperF.setMouseTransparent(true);
        });
        zoomLabF.setOnMouseClicked(e -> useItem(zoomLabF, stackPane, stage));
        tube1F.setOnMouseClicked(e -> useItem(tube1F, stackPane, stage));
        tube2F.setOnMouseClicked(e -> useItem(tube2F, stackPane, stage));
        tube3F.setOnMouseClicked(e -> useItem(tube3F, stackPane, stage));
        bunsenBurnerLab.setOnMouseClicked(e -> useItem(bunsenBurnerLab, stackPane, stage));
        flameColorLilacLab.setOnMouseClicked(e -> {addIntoInventory(flameColorLilacLab, flameColorLilacTool);
            bunsenBurnerLab.setMouseTransparent(false);});
        flameColorCrimsonLab.setOnMouseClicked(e -> {addIntoInventory(flameColorCrimsonLab, flameColorCrimsonTool);
            bunsenBurnerLab.setMouseTransparent(false);});
        flameColorGreenLab.setOnMouseClicked(e -> {addIntoInventory(flameColorGreenLab, flameColorGreenTool);
            bunsenBurnerLab.setMouseTransparent(false);});
        flameColorYellowLab.setOnMouseClicked(e -> {addIntoInventory(flameColorYellowLab, flameColorYellowTool);
            bunsenBurnerLab.setMouseTransparent(false);});
        return new Pane(stackPane, inventoryPane);
    }

    /**
     * Settings of bat animation.
     */
    private void scareBat() {
        hideImage(batsFly);
        batPlayer.setOnEndOfMedia (() -> batPlayer.stop());
        batFlyPlayer.setOnEndOfMedia (() -> batFlyPlayer.stop());
        Timeline batTime = new Timeline(new KeyFrame(Duration.seconds(1.9), event -> batsFly.setVisible(false)));
        /// Set on action.
        bats.setOnMouseClicked(e -> {
            bats.setMouseTransparent(true);
            if (player.isSoundOn()) {
                batPlayer.play();
            }
            Timeline batVisibleTime = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {bats.setVisible(false); batsFly.setVisible(true);}));
            Timeline batSoundTime = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
                if (player.isSoundOn()) {
                    batFlyPlayer.play();
                }
            }));
            batTime.playFromStart();
            batSoundTime.playFromStart();
            batVisibleTime.playFromStart();
        });
    }

    /**
     * Initialize the original state of certain media.
     */
    private void initialize() {
        /// Main scene.
        chosenItem = placeHolder;
        flame.setMouseTransparent(true);
        hideImage(labSetShow);
        fail.setOpacity(0);
        failF.setOpacity(0);
        goBack.setOpacity(0);
        retourner.setOpacity(0);
        /// Door scene.
        hideImage(flameZoomRight);
        /// Bug drawer scene.
        hideImage(openBig);
        hideImage(drawerBunsen);
        doorPlayer.setOnEndOfMedia (() -> doorPlayer.stop());
        /// Microscope scene.
        hideImage(zoomedPowderMicro);
        hideImage(nacl);
        hideImage(bacl2);
        hideImage(kcl);
        hideImage(licl);
        /// Lab set scene.
        hideImage(full1);
        hideImage(full2);
        hideImage(full3);
        hideImage(bunsenBurnerLab);
        hideImage(flameColorCrimsonLab);
        hideImage(flameColorGreenLab);
        hideImage(flameColorLilacLab);
        hideImage(flameColorYellowLab);
        /// Set up items.
        bunsenBurnerTool.getImageView().setOnMouseClicked(e -> chosenItem = bunsenBurnerTool);
        powderTool.getImageView().setOnMouseClicked(e -> chosenItem = powderTool);
        kclTool.getImageView().setOnMouseClicked(e -> chosenItem = kclTool);
        bacl2Tool.getImageView().setOnMouseClicked(e -> chosenItem = bacl2Tool);
        naclTool.getImageView().setOnMouseClicked(e -> chosenItem = naclTool);
        liclTool.getImageView().setOnMouseClicked(e -> chosenItem = liclTool);
        tubeToolK.getImageView().setOnMouseClicked(e -> chosenItem = tubeToolK);
        tubeToolN.getImageView().setOnMouseClicked(e -> chosenItem = tubeToolN);
        tubeToolB.getImageView().setOnMouseClicked(e -> chosenItem = tubeToolB);
        tubeToolL.getImageView().setOnMouseClicked(e -> chosenItem = tubeToolL);
        flameColorLilacTool.getImageView().setOnMouseClicked(e -> chosenItem = flameColorLilacTool);
        flameColorYellowTool.getImageView().setOnMouseClicked(e -> chosenItem = flameColorYellowTool);
        flameColorCrimsonTool.getImageView().setOnMouseClicked(e -> chosenItem = flameColorCrimsonTool);
        flameColorGreenTool.getImageView().setOnMouseClicked(e -> chosenItem = flameColorGreenTool);
    }

    /**
     * Set up mouse click actions for the main scene.
     */
    private void zoomMain(Stage stage) {
        door.setOnMouseClicked(e -> {
            Scene zoomDScene = new Scene(zoomDoor(stage));
            stage.setScene(zoomDScene);
        });
        drawerMic.setOnMouseClicked(e -> {
            Scene zoomDMScene = new Scene(zoomSmall(stage));
            stage.setScene(zoomDMScene);
        });
        drawerLab.setOnMouseClicked(e -> {
            Scene zoomDLScene = new Scene(zoomBig(stage));
            stage.setScene(zoomDLScene);
        });
        microscope.setOnMouseClicked(e -> {
            Scene zoomMScene = new Scene(zoomMicro(stage));
            stage.setScene(zoomMScene);
        });
        labSet.setOnMouseClicked(e -> {
            Scene zoomLScene = new Scene(zoomLab(stage));
            stage.setScene(zoomLScene);
        });
        labSetShow.setOnMouseClicked(e -> {
            Scene zoomLScene = new Scene(zoomLab(stage));
            stage.setScene(zoomLScene);
        });
        stage.setOnShown(event -> {
            if (!mainLayout.getChildren().contains(inventoryPane)) {
                mainLayout.getChildren().add(inventoryPane);
            }
        });
    }

    /**
     * Use item action to... use items.
     */
    private void useItem(ImageView clickedImage, StackPane stackPane, Stage stage) {
        if (chosenItem != null && chosenItem.equals(bunsenBurnerTool) && clickedImage.equals(zoomLabF)) {
            stackPane.getChildren().remove(monologuesL.get(4));
            stackPane.getChildren().add(monologuesL.get(3));
            showImage(labSetShow);
            showImage(bunsenBurnerLab);
            removeFromInventory(bunsenBurnerTool);
        } else if (chosenItem != null && chosenItem.equals(powderTool) && clickedImage.equals(zoomedMicroscope)) {
            removeFromInventory(powderTool);
            stackPane.getChildren().add(monologuesL.get(2));
            zoomedPowderMicro.setVisible(true);
            zoomedMicroscope.setMouseTransparent(true);
            showImage(nacl);
            showImage(bacl2);
            showImage(kcl);
            showImage(licl);
        } else if (chosenItem != null && chosenItem.equals(kclTool)) {placeChem(clickedImage, tubeToolK, kclTool);
        } else if (chosenItem != null && chosenItem.equals(naclTool)) {placeChem(clickedImage, tubeToolN, naclTool);
        } else if (chosenItem != null && chosenItem.equals(bacl2Tool)) {placeChem(clickedImage, tubeToolB, bacl2Tool);
        } else if (chosenItem != null && chosenItem.equals(liclTool)) {placeChem(clickedImage, tubeToolL, liclTool);
        } else if (chosenItem != null && chosenItem.equals(tubeToolK) && clickedImage.equals(bunsenBurnerLab)) {placeTube(tubeToolK, flameColorLilacLab);
        } else if (chosenItem != null && chosenItem.equals(tubeToolN) && clickedImage.equals(bunsenBurnerLab)) {placeTube(tubeToolN, flameColorYellowLab);
        } else if (chosenItem != null && chosenItem.equals(tubeToolB) && clickedImage.equals(bunsenBurnerLab)) {placeTube(tubeToolB, flameColorGreenLab);
        } else if (chosenItem != null && chosenItem.equals(tubeToolL) && clickedImage.equals(bunsenBurnerLab)) {placeTube(tubeToolL, flameColorCrimsonLab);
        } else if (chosenItem != null && chosenItem.equals(flameColorCrimsonTool) && clickedImage.equals(flameZoom)) {
            stackPane.getChildren().remove(monologuesL.get(1));
            removeFromInventory(flameColorCrimsonTool);
            stackPane.getChildren().add(monologuesL.getFirst());
            showImage(flameZoomRight);
            hideImage(flameZoom);
        } else if (chosenItem != null && clickedImage.equals(flameZoom)) {
            if (chosenItem.equals(flameColorYellowTool)) {
                useWrongFlame(flameColorYellowTool, stackPane, stage);
            } else if (chosenItem.equals(flameColorGreenTool)) {
                useWrongFlame(flameColorGreenTool, stackPane, stage);
            } else if (chosenItem.equals(flameColorLilacTool)) {
                useWrongFlame(flameColorLilacTool, stackPane, stage);
            }
        }
    }

    /**
     * Extracted repeated method for placing tubes.
     */
    private void placeTube(Item tubeTool, ImageView flameColor) {
        removeFromInventory(tubeTool);
        bunsenBurnerLab.setMouseTransparent(true);
        showImage(flameColor);
    }

    /**
     * Extracted repeated method for placing chemicals.
     */
    private void placeChem(ImageView clickedImage, Item tubeTool, Item chemTool) {
        if (clickedImage.equals(tube1F)) {
            tubeSet(full1, tube1F, tubeTool);
            removeFromInventory(chemTool);
        } else if (clickedImage.equals(tube2F)) {
            tubeSet(full2, tube2F, tubeTool);
            removeFromInventory(chemTool);
        } else if (clickedImage.equals(tube3F)) {
            tubeSet(full3, tube3F, tubeTool);
            removeFromInventory(chemTool);
        } else {
            chosenItem = chemTool;
        }
    }

    /**
     * Extracted repeated method for tube changes from putting chemicals in empty tubes.
     */
    private void tubeSet(ImageView full, ImageView tube, Item tubeTool) {
        showImage(full);
        hideImage(tube);
        full.setOnMouseClicked(e -> addIntoInventory(full, tubeTool));
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

    /**
     * Extracted repeated method for adding an item into the inventory.
     */
    private void addIntoInventory(ImageView imageOfTool, Item tool){
        hideImage(imageOfTool);
        inventory.addItem(tool);
        overlay.updateInventory();
    }

    /**
     * Extracted repeated method for removing an item from the inventory.
     */
    private void removeFromInventory(Item tool){
        chosenItem = placeHolder;
        inventory.removeItem(tool);
        overlay.updateInventory();
    }

    /**
     * Easy add inventory image and set up.
     */
    private void addInventory(StackPane stackPane) {
        ImageView inventoryImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/inventory.png")).toExternalForm()));
        inventoryImage.setMouseTransparent(true);
        stackPane.getChildren().add(inventoryImage);
    }

    /**
     * Method for when user uses wrong color of flame to open the door.
     */
    private void useWrongFlame (Item wrongFlame, StackPane stackPane, Stage stage) {
        removeFromInventory(wrongFlame);
        stackPane.getChildren().remove(monologuesL.get(1));
        stackPane.getChildren().add(monologuesL.get(1));
        lifeManager.decreaseLife();
        if (lifeManager.getLives() == 0) {
            FadeTransition fadeTransitionIn = new FadeTransition(Duration.seconds(2), inventoryPane);
            FadeTransition fadeTransitionBG = new FadeTransition(Duration.seconds(2), monologuesL.get(5));
            FadeTransition fadeTransitionLet = new FadeTransition(Duration.seconds(2), monologuesL.get(6));
            fadeTransitionBG.setFromValue(0);
            fadeTransitionBG.setToValue(1);
            fadeTransitionLet.setFromValue(0);
            fadeTransitionLet.setToValue(1);
            fadeTransitionIn.setFromValue(1);
            fadeTransitionIn.setToValue(0);
            fadeTransitionBG.play();
            fadeTransitionLet.play();
            fadeTransitionIn.play();
            stackPane.getChildren().add(monologuesL.get(5));
            stackPane.getChildren().add(monologuesL.get(6));
            overlay.updateInventory();
            goBack.setOnMouseClicked(e -> reStart(stage));
            retourner.setOnMouseClicked(e -> reStart(stage));
        }
    }

    /**
     * Go back to the beginning.
     * @param stage stage.
     */
    private void reStart(Stage stage) {
        GameController gameController = new GameController();
        try {
            gameController.start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        lifeManager.resetLives();
        inventoryPane.setOpacity(1);
        inventory.resetInventory();
        overlay.updateInventory();
    }
}
