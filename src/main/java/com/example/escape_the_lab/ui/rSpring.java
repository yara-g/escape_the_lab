package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.*;
import com.example.escape_the_lab.model.Item;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.escape_the_lab.controller.GameController.player;

/**
 * The rSpring class is responsible for managing the UI and scene transitions for the spring-based puzzle lab part of
 * the "escape the room" game. It interacts with the player's inventory, displays handled item selection and placement,
 * and evaluates the solution based on physics principles (spring oscillation).
 */
public class rSpring {
    private final Stage stage;
    private final SpringLab springLab;
    private final Overlay overlay;
    private Inventory inventory;
    private Group inventoryPane;
    private LifeManager lifeManager;
    public boolean isDoorUnlocked = false;
    public boolean springPlaced = false;
    public boolean massPlaced = false;
    private Item placedSpringItem = null;
    private Item placedMassItem = null;
    private Item chosenItem;
    private int order = 0;
    //private ImageView selectedSpring;
    //private ImageView selectedMass;
    StackPane root;
    public Item placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");

    /// Main scene.
    public ImageView light = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/lightS.png")).toExternalForm()));
    public ImageView lightOn = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/lightOnS.png")).toExternalForm()));
    public ImageView door = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/doorS.png")).toExternalForm()));
    public ImageView mainChair = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sofaS.png")).toExternalForm()));
    public ImageView mainDrawer = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/tableS.png")).toExternalForm()));
    public ImageView mainShelves = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/shelfS.png")).toExternalForm()));
    public ImageView person = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/personS.png")).toExternalForm()));
    public ImageView shadow = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/shadowS.png")).toExternalForm()));
    public ImageView bgMain = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/bgS.png")).toExternalForm()));
    public ImageView obj1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/obj1S.png")).toExternalForm()));
    public ImageView obj2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/obj2S.png")).toExternalForm()));

    /// Chair scene.
    public ImageView chair = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sBgS.png")).toExternalForm()));
    public ImageView chairCover = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sCoverS.png")).toExternalForm()));
    public ImageView spring1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sSpring1S.png")).toExternalForm()));
    public ImageView spring2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sSpring2S.png")).toExternalForm()));  // Correct choice
    public ImageView spring3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sSpring3S.png")).toExternalForm()));

    public Item spring1Item = new Item("100N/m", "/images/AAASpringLab/sO1.png");
    public Item spring2Item = new Item("200N/m", "/images/AAASpringLab/sO2.png");
    public Item spring3Item = new Item("300N/m", "/images/AAASpringLab/sO3.png");
    public Item mass1Item = new Item("5kg", "/images/AAASpringLab/oO1.png");
    public Item mass2Item = new Item("3kg", "/images/AAASpringLab/oO2.png");
    public Item mass3Item = new Item("4kg", "/images/AAASpringLab/oO3.png");

    //lab scene
    public ImageView springStand = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oS.png")).toExternalForm()));
    public ImageView table = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oBgS.png")).toExternalForm()));
    public ImageView obj1Low = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj1On.png")).toExternalForm()));
    public ImageView obj1High = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj1OnW.png")).toExternalForm()));
    public ImageView obj2Low = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj2On.png")).toExternalForm()));
    public ImageView obj2High = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj2OnW.png")).toExternalForm()));
    public ImageView obj3Low = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj3On.png")).toExternalForm()));
    public ImageView obj3High = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj3OnW.png")).toExternalForm()));
    public ImageView springLow = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oSpringOnW.png")).toExternalForm()));
    public ImageView springHigh = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oSpringOn.png")).toExternalForm()));
    public ImageView tryButton = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/try.png")).toExternalForm()));
    public ImageView stretch1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/stretch1.png")).toExternalForm()));
    public ImageView stretch2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/stretch2.png")).toExternalForm()));

    public List<ImageView> monologues = new ArrayList<>();
    public List<ImageView> monologuesF = new ArrayList<>();
    public List<ImageView> monologuesL = new ArrayList<>();

    private final ImageView fail = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/gameFail.png")).toExternalForm()));
    private final ImageView failF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/gameFailF.png")).toExternalForm()));
    private final ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/goBack.png")).toExternalForm()));
    private final ImageView retourner = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/retourner.png")).toExternalForm()));
    public ImageView monoDoor = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/9.png")).toExternalForm()));
    public ImageView monoDoorF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/10.png")).toExternalForm()));
    public ImageView monoHI = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/11.png")).toExternalForm()));
    public ImageView monoHIF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/12.png")).toExternalForm()));
    public ImageView monoMessage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/13.png")).toExternalForm()));
    public ImageView monoMessageF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/14.png")).toExternalForm()));
    public ImageView monoLab = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/15.png")).toExternalForm()));
    public ImageView monoLabF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/16.png")).toExternalForm()));
    public ImageView monoNothing = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/17.png")).toExternalForm()));
    public ImageView monoNothingF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/18.png")).toExternalForm()));
    public ImageView monoObj = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/19.png")).toExternalForm()));
    public ImageView monoObjF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/20.png")).toExternalForm()));
    public ImageView monoSpring = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/21.png")).toExternalForm()));
    public ImageView monoSpringF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/22.png")).toExternalForm()));
    public ImageView monoWrong = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/23.png")).toExternalForm()));
    public ImageView monoWrongF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/24.png")).toExternalForm()));
    public ImageView monoLeave = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/25.png")).toExternalForm()));
    public ImageView monoLeaveF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/26.png")).toExternalForm()));

    /**
     * Constructs an rSpring scene with the given stage and controller.
     * @param stage The stage to render scenes on.
     * @param springLab The controller containing logic for the spring lab.
     */
    public rSpring(Stage stage, SpringLab springLab) {
        this.stage = stage;
        this.springLab = springLab;
        this.overlay = GameController.getOverlay();
        this.inventory = overlay.getInventory();
        this.inventoryPane = overlay.getOverlayPane();
        this.lifeManager = overlay.getLifeManager();
        //selectedSpring = new ImageView();
        //selectedMass = new ImageView();
        hideImage(obj1Low);
        hideImage(obj1High);
        hideImage(obj2Low);
        hideImage(obj2High);
        hideImage(obj3Low);
        hideImage(obj3High);
        hideImage(springLow);
        hideImage(springHigh);
        hideImage(lightOn);
        hideImage(stretch1);
        hideImage(stretch2);
        hideImage(monoDoor);
        hideImage(monoDoorF);
        hideImage(monoHI);
        hideImage(monoHIF);
        hideImage(monoMessage);
        hideImage(monoMessageF);
        hideImage(monoLab);
        hideImage(monoLabF);
        hideImage(monoNothing);
        hideImage(monoNothingF);
        hideImage(monoObj);
        hideImage(monoObjF);
        hideImage(monoSpring);
        hideImage(monoSpringF);
        hideImage(monoWrong);
        hideImage(monoWrongF);
        hideImage(monoLeave);
        hideImage(monoLeaveF);
    }

    /**
     * Displays the main scene for the spring lab where the player navigates to get out.
     */
    public void showMainScene() {
        initialize();

        StackPane root = new StackPane(bgMain, light, door, mainChair, mainDrawer, mainShelves, obj1, obj2, person, shadow, lightOn);
        addInventory(root);
        root.getChildren().addAll(List.of(monologuesL.get(2), monologuesL.get(3), monologuesL.get(4)));

        overlay.getHelpButton().setOnMouseClicked(e -> {
            Image helpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/help/springFormula.png")));
            if (player.getLanguage().equals("english")) {
                Help.show("Select the right spring and mass so the oscillation is just right to reach the button by" +
                        " matching the spring's oscillation frequency!\n" + "Use the formula:", helpImage);
            } else {
                Help.show("Choisissez le bon ressort et la bonne masse pour que l'oscillation atteigne juste le bouton " +
                        "en correspondant à la fréquence d'oscillation du ressort !\n" + "Utilisez la formule :", helpImage);
            }
//            Help.show("Select the right spring and mass so the oscillation is just right to reach the button by" +
//                    " matching the spring's oscillation frequency!\n" + "Use the formula:", helpImage);
        });

        //door.setOnMouseClicked(event -> root.getChildren().add(monologuesL.getFirst()));
        mainChair.setOnMouseClicked(event -> showSpringsScene());
        mainDrawer.setOnMouseClicked(event -> showLabScene());
        light.setOnMouseClicked(event -> {hideImage(shadow); showImage(person); showImage(lightOn);
            hideImage(monologuesL.get(2));
            showImage(monologuesL.get(3));
            hideImage(monologuesL.get(4));});
        obj1.setOnMouseClicked(event -> addIntoInventory(obj1, mass1Item));
        obj2.setOnMouseClicked(event -> addIntoInventory(obj2, mass2Item));
        person.setOnMouseClicked(event -> {inventory.addItem(mass3Item); overlay.updateInventory(); person.setMouseTransparent(true);
            hideImage(monologuesL.get(2));
            hideImage(monologuesL.get(3));
            showImage(monologuesL.get(4));});

        Pane pane = new Pane(root, inventoryPane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        startSolutionCheck();
    }

    /**
     * Displays the scene where the player is able to select different springs.
     */
    private void showSpringsScene() {
        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        goBack.setOnMouseClicked(e -> showMainScene());

        spring1.setOnMouseClicked(e -> addIntoInventory(spring1, spring1Item));
        spring2.setOnMouseClicked(e -> addIntoInventory(spring2, spring2Item));
        spring3.setOnMouseClicked(e -> addIntoInventory(spring3, spring3Item));

        StackPane root = new StackPane(chair, spring1, spring2, spring3, chairCover, overlay.getOverlayPane());
        addInventory(root);
        root.getChildren().add(goBack);
        Pane pane = new Pane(root, inventoryPane);

        stage.setScene(new Scene(pane, 1000, 650));
        startSolutionCheck();
    }

    /**
     * Displays the lab table scene where the player can place spring and mass items from their inventory.
     */
    private void showLabScene() {
        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        goBack.setOnMouseClicked(e -> showMainScene());
        root = new StackPane();
        showImage(monologuesL.get(5));

        springStand.setOnMouseClicked(e -> {
            if (chosenItem != null && !chosenItem.equals(placeHolder)) {
                if ((chosenItem.equals(spring1Item) || chosenItem.equals(spring2Item) || chosenItem.equals(spring3Item)) && placedSpringItem == null) {
                    if (massPlaced) {
                        showImage(springLow);
                    } else {
                        showImage(springHigh);
                    }
                    placedSpringItem = chosenItem;
                    springPlaced = true;
                    inventory.removeItem(chosenItem);
                    overlay.updateInventory();
                    chosenItem = placeHolder;
                } else if (chosenItem.getName().contains("kg") && placedMassItem == null) {
                    if (chosenItem.equals(mass1Item)) {
                        if (springPlaced) {
                            showImage(obj1Low);
                        } else {
                            showImage(obj1High);
                        }
                    } else if (chosenItem.equals(mass2Item)) {
                        if (springPlaced) {
                            showImage(obj2Low);
                        } else {
                            showImage(obj2High);
                        }
                    } else  if (chosenItem.equals(mass3Item)) {
                        if (springPlaced) {
                            showImage(obj3Low);
                        } else {
                            showImage(obj3High);
                        }
                    }
                    placedMassItem = chosenItem;
                    massPlaced = true;
                    inventory.removeItem(chosenItem);
                    overlay.updateInventory();
                    chosenItem = placeHolder;
                }
            }
        });
        removeSpring(springHigh);
        removeSpring(springLow);
        removeMass(obj1High);
        removeMass(obj1Low);
        removeMass(obj2High);
        removeMass(obj2Low);
        removeMass(obj3High);
        removeMass(obj3Low);

        tryButton.setOnMouseClicked(e -> {
            boolean springMissing = !springPlaced;
            boolean massMissing = !massPlaced;

            if (placedSpringItem.equals(spring2Item) && placedMassItem.equals(mass3Item) && springHigh.isVisible()) {
                startSpringOscillation(root);
            } else if (springMissing && massMissing) {
                showImage(monologuesL.get(6));
                hideImage(springHigh);
                hideImage(monologuesL.getLast());
                hideImage(monologuesL.get(5));
                hideImage(monologuesL.get(7));
                hideImage(monologuesL.get(8));
                hideImage(monologuesL.get(9));
            } else if (!springMissing && massMissing) {
                showImage(monologuesL.get(7));
                hideImage(springHigh);
                hideImage(monologuesL.getLast());
                hideImage(monologuesL.get(5));
                hideImage(monologuesL.get(6));
                hideImage(monologuesL.get(8));
                hideImage(monologuesL.get(9));
                lifeManager.decreaseLife();
                lifeManager.kill(overlay.getOverlayPane(), bgMain, placeHolder.getImageView(), root, overlay, goBack, retourner, overlay.getInventory(), stage);
            } else if (springMissing && !massMissing) {
                showImage(monologuesL.get(8));
                hideImage(springHigh);
                hideImage(monologuesL.getLast());
                hideImage(monologuesL.get(5));
                hideImage(monologuesL.get(7));
                hideImage(monologuesL.get(6));
                hideImage(monologuesL.get(9));
                lifeManager.decreaseLife();
                lifeManager.kill(overlay.getOverlayPane(), bgMain, placeHolder.getImageView(), root, overlay, goBack, retourner, overlay.getInventory(), stage);
            } else {
                showImage(monologuesL.get(9));
                hideImage(springHigh);
                hideImage(monologuesL.getLast());
                hideImage(monologuesL.get(5));
                hideImage(monologuesL.get(7));
                hideImage(monologuesL.get(8));
                hideImage(monologuesL.get(6));
                lifeManager.decreaseLife();
                lifeManager.kill(overlay.getOverlayPane(), bgMain, placeHolder.getImageView(), root, overlay, goBack, retourner, overlay.getInventory(), stage);
            }
        });

        root.getChildren().addAll(table, springStand, springLow, springHigh, obj1High, obj1Low, obj2High, obj2Low, obj3High, obj3Low, stretch1, stretch2, overlay.getOverlayPane(), tryButton);
        addInventory(root);
        root.getChildren().add(goBack);
        root.getChildren().addAll(monologuesL.get(5), monologuesL.get(6), monologuesL.get(7), monologuesL.get(8), monologuesL.get(9), monologuesL.get(10));
        Pane pane = new Pane(root, inventoryPane);
        stage.setScene(new Scene(pane, 1000, 650));
    }

    /**
     * Displays the spring and mass oscillation animations and evaluates the solution.
     */
    private void startSpringOscillation(StackPane stackPane) {
        PauseTransition pause1 = new PauseTransition(Duration.seconds(0.5));
        pause1.setOnFinished(e -> {showImage(stretch1); hideImage(obj3Low); hideImage(springHigh);});

        PauseTransition pause2 = new PauseTransition(Duration.seconds(1.0));
        pause2.setOnFinished(e -> {hideImage(stretch1); showImage(stretch2);});

        PauseTransition pause3 = new PauseTransition(Duration.seconds(2.0));
        pause3.setOnFinished(e -> {hideImage(stretch2); showImage(stretch1);});

        PauseTransition pause4 = new PauseTransition(Duration.seconds(2.5));
        pause4.setOnFinished(e -> {hideImage(stretch1); showImage(obj3Low); showImage(springHigh); showImage(monologuesL.getLast()); hideImage(monologuesL.get(5)); hideImage(monologuesL.get(6)); hideImage(monologuesL.get(7)); hideImage(monologuesL.get(8)); hideImage(monologuesL.get(9));});

        // Start both pauses
        pause1.play();
        pause2.play();
        pause3.play();
        pause4.play();

        isDoorUnlocked = true;
        door.setOnMouseClicked(event -> {
            FlameLab f = new FlameLab();
            f.startLab(stage);
            inventory.resetInventory();
            overlay.updateInventory();
        });
    }

    /**
     * Starts the rCiruit lab scene.
     */
    private void startRCircuitLab() {
        rCircuit lab = new rCircuit(stage);
        try {
            lab.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Begins periodic checking of the player's solution using an AnimationTimer.
     */
    private void startSolutionCheck() {
        AnimationTimer solutionCheckTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (springLab.checkSolution()) {
                    startRCircuitLab();
                    stop();
                }
            }
        };
        solutionCheckTimer.start();
    }

    /**
     * Initiolizes the language-specific monologue settings
     */
    public void initialize() {
        if (Objects.equals(player.getLanguage(), "english")) {
            monologuesL.clear();
            monologuesL = monologues;
        } else {
            monologuesL.clear();
            monologuesL = monologuesF;
        }
        monologues.addAll(List.of(fail, goBack, monoDoor, monoHI, monoMessage, monoLab, monoNothing, monoObj, monoSpring, monoWrong, monoLeave));
        monologuesF.addAll(List.of(failF, retourner, monoDoorF, monoHIF, monoMessageF, monoLabF, monoNothingF, monoObjF, monoSpringF, monoWrongF, monoLeaveF));

        spring1Item.getImageView().setOnMouseClicked(e -> chosenItem = spring1Item);
        spring2Item.getImageView().setOnMouseClicked(e -> chosenItem = spring2Item);
        spring3Item.getImageView().setOnMouseClicked(e -> chosenItem = spring3Item);
        mass1Item.getImageView().setOnMouseClicked(e -> chosenItem = mass1Item);
        mass2Item.getImageView().setOnMouseClicked(e -> chosenItem = mass2Item);
        mass3Item.getImageView().setOnMouseClicked(e -> chosenItem = mass3Item);
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
    public void removeFromInventory(Item tool){
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

    private void removeSpring(ImageView selectedSpring) {
        selectedSpring.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && placedSpringItem != null) {
                hideImage(selectedSpring);
                inventory.addItem(placedSpringItem);
                placedSpringItem = null;
                springPlaced = false;
                if (massPlaced) {
                    if (obj1Low.isVisible()) {
                        hideImage(obj1Low);
                        showImage(obj1High);
                    } else if (obj2Low.isVisible()) {
                        hideImage(obj2Low);
                        showImage(obj2High);
                    } else if (obj3Low.isVisible()) {
                        hideImage(obj3Low);
                        showImage(obj3High);
                    }
                }
                overlay.updateInventory();
            }
        });
    }

    private void removeMass(ImageView selectedMass) {
        selectedMass.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && placedMassItem != null) {
                hideImage(selectedMass);
                inventory.addItem(placedMassItem);
                placedMassItem = null;
                massPlaced = false;
                if (springPlaced) {
                    hideImage(springLow);
                    showImage(springHigh);
                }
                overlay.updateInventory();
            }
        });
    }

    private double getSpringConstantFromName(String name) {
        switch (name) {
            case "100N": return 100;
            case "200N": return 200;
            case "300N": return 300;
            default: return 0;
        }
    }

    private double getMassFromName(String name) {
        switch (name) {
            case "3g": return 3;
            case "4g": return 4;
            case "5g": return 5;
            default: return 0;
        }
    }

    private String getItemNameFromImage(Image img) {
        if (img == mass1Item.getImage()) return mass1Item.getName();
        if (img == mass2Item.getImage()) return mass2Item.getName();
        if (img == mass3Item.getImage()) return mass3Item.getName();
        if (img == spring1Item.getImage()) return spring1Item.getName();
        if (img == spring2Item.getImage()) return spring2Item.getName();
        if (img == spring3Item.getImage()) return spring3Item.getName();
        return "";
    }
}
