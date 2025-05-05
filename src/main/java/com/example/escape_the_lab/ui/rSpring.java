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
 * The rSpring Class is responsible for managing the UI and scene transitions for the spring based puzzle lab in the
 * escape the room game. It interacts with the player's inventory, handles displaying item selection and placement,
 * and the solution based on physics principles; spring oscillation.
 */
public class rSpring {
    private final Stage stage;
    private final SpringLab springLab;
    private final Overlay overlay;
    private Inventory inventory;
    private Group inventoryPane;
    private LifeManager lifeManager;
    private boolean isDoorUnlocked = false;
    private boolean springPlaced = false;
    private boolean massPlaced = false;
    private Item placedSpringItem = null;
    private Item placedMassItem = null;
    private Item chosenItem;
    private final ImageView selectedSpring;
    private final ImageView selectedMass;
    Item placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");// placeholder item used when no item is selected

    /// Main scene.
    ImageView light = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/lightS.png")).toExternalForm()));
    ImageView door = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/doorS.png")).toExternalForm()));
    ImageView mainChair = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sofaS.png")).toExternalForm()));
    ImageView mainDrawer = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/tableS.png")).toExternalForm()));
    ImageView mainShelves = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/shelfS.png")).toExternalForm()));
    ImageView person = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/personS.png")).toExternalForm()));
    ImageView shadow = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/shadowS.png")).toExternalForm()));
    ImageView bgMain = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/bgS.png")).toExternalForm()));
    ImageView obj1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/obj1S.png")).toExternalForm()));
    ImageView obj2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/obj2S.png")).toExternalForm()));

//    ImageView mass1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/clockStatue.png")).toExternalForm())); //book
//    ImageView mass2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/books.png")).toExternalForm()));  // Correct choice
//    ImageView mass3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/globe.png")).toExternalForm()));

    /// Chair scene.
    ImageView chair = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sBgS.png")).toExternalForm()));
    ImageView chairCover = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sCoverS.png")).toExternalForm()));
    ImageView spring1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sSpring1S.png")).toExternalForm()));
    ImageView spring2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sSpring2S.png")).toExternalForm()));  // Correct choice
    ImageView spring3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sSpring3S.png")).toExternalForm()));

    Item spring1Item = new Item("100N/m", "/images/AAASpringLab/sO1.png");
    Item spring2Item = new Item("200N/m", "/images/AAASpringLab/sO2.png");
    Item spring3Item = new Item("300N/m", "/images/AAASpringLab/sO3.png");
    Item mass1Item = new Item("4kg", "/images/AAASpringLab/oO1.png");
    Item mass2Item = new Item("3kg", "/images/AAASpringLab/oO2.png");
    Item mass3Item = new Item("5kg", "/images/AAASpringLab/oO3.png");

    //lab scene
    ImageView springStand = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oS.png")).toExternalForm()));
    ImageView table = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oBgS.png")).toExternalForm()));
    ImageView obj1Low = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj1On.png")).toExternalForm()));
    ImageView obj1High = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj1OnW.png")).toExternalForm()));
    ImageView obj2Low = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj2On.png")).toExternalForm()));
    ImageView obj2High = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj2OnW.png")).toExternalForm()));
    ImageView obj3Low = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj3On.png")).toExternalForm()));
    ImageView obj3High = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oObj3OnW.png")).toExternalForm()));
    ImageView springLow = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oSpringOnW.png")).toExternalForm()));
    ImageView springHigh = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/oSpringOn.png")).toExternalForm()));

    //ImageView doorLockedMessage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/door..png")).toExternalForm()));
    //ImageView nothingPlacedMessage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/door..png")).toExternalForm()));
    List<ImageView> monologues = new ArrayList<>();
    List<ImageView> monologuesF = new ArrayList<>();
    List<ImageView> monologuesL = new ArrayList<>();

    /**
     * Constructs an rSpring scene with the given stage and controller.
     * @param stage the stage to render the scenes on.
     * @param springLab the controlller containing logic for the spring lab.
     */
    public rSpring(Stage stage, SpringLab springLab) {
        this.stage = stage;
        this.springLab = springLab;
        this.overlay = GameController.getOverlay();
        this.inventory = overlay.getInventory();
        this.inventoryPane = overlay.getOverlayPane();
        this.lifeManager = overlay.getLifeManager();
        selectedSpring = new ImageView();
        selectedMass = new ImageView();
    }

    /**
     * Displays the main scene for the spring lab where the user will choose their next step.
     */
    public void showMainScene() {
        initialize();

        // TEMPORARY - remove the button
        Button skipToNext = new Button("Escape");
        skipToNext.setOnAction(e -> {
            FlameLab f = new FlameLab();
            f.startLab(stage);
            inventory.resetInventory();
            overlay.updateInventory();
        });

        StackPane root = new StackPane(bgMain, light, door, mainChair, mainDrawer, mainShelves, obj1, obj2, person, shadow, skipToNext);
        addInventory(root);

        overlay.getHelpButton().setOnMouseClicked(e -> {
            Image helpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/help/springFormula.png")));
            Help.show("Select the right spring and mass so the oscillation is just right to reach the button by" +
                    " matching the spring's oscillation frequency!\n" + "Use the formula:", helpImage);
        });

        door.setOnMouseClicked(event -> root.getChildren().add(monologuesL.getFirst()));
        mainChair.setOnMouseClicked(event -> showSpringsScene());
        mainDrawer.setOnMouseClicked(event -> showLabScene());
        light.setOnMouseClicked(event -> {hideImage(shadow); showImage(person);});
        obj1.setOnMouseClicked(event -> addIntoInventory(obj1, mass1Item));
        obj2.setOnMouseClicked(event -> addIntoInventory(obj2, mass2Item));
        //        mass1Item.getImageView().setOnMouseClicked(e -> chosenItem = mass1Item);
//        mass2Item.getImageView().setOnMouseClicked(e -> chosenItem = mass2Item);

//        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
//        goBack.setOnMouseClicked(e -> showMainScene());
//
//        StackPane root = new StackPane(shelves, mass1, mass2, mass3, overlay.getOverlayPane(), goBack);
//        addInventory(root);
//        stage.setScene(new Scene(root, 1000, 650));
//        startSolutionCheck();

//        ImageView springStand = createImageView("/images/AAASpringLab/spring-Stand.png", 250, 300, 200, 200);
        Pane pane = new Pane(root, inventoryPane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        // Start an animation timer to continuously check the solution
        startSolutionCheck();
    }

    private void showDoorOpenScene() {
        FlameLab f = new FlameLab();
        f.startLab(stage);
        inventory.resetInventory();
        overlay.updateInventory();
    }

    /**
     * Displays the scene where the user can select different springs; whether the right or the wrong one.
     */
    private void showSpringsScene() {
        spring1Item.getImageView().setOnMouseClicked(e -> chosenItem = spring1Item);
        spring2Item.getImageView().setOnMouseClicked(e -> chosenItem = spring2Item);
        spring3Item.getImageView().setOnMouseClicked(e -> chosenItem = spring3Item);

        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        goBack.setOnMouseClicked(e -> showMainScene());

        spring1.setOnMouseClicked(e -> addIntoInventory(spring1, spring1Item));
        spring2.setOnMouseClicked(e -> addIntoInventory(spring2, spring2Item));
        spring3.setOnMouseClicked(e -> addIntoInventory(spring3, spring3Item));

        StackPane root = new StackPane(chair, spring1, spring2, spring3, chairCover, overlay.getOverlayPane());
        addInventory(root);
        root.getChildren().add(goBack);
        stage.setScene(new Scene(root, 1000, 650));
        startSolutionCheck();
    }

    /**
     * Displays the lab table scene where the player can place a mass and spring to oscillate for oscillation.
     */
    private void showLabScene() {
        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        goBack.setOnMouseClicked(e -> showMainScene());
        StackPane root = new StackPane();

        springStand.setOnMouseClicked(e -> {
            if (chosenItem != null && !chosenItem.equals(placeHolder)) {
                boolean placed = springLab.tryPlaceItem(chosenItem);
                if (placed) {
                    if (chosenItem.getName().contains("N/m")) {
                        selectedSpring.setImage(chosenItem.getImage());
                        selectedSpring.setLayoutY(200);
                        //placedSpringItem = chosenItem;
                        //springPlaced = true;

                        if (springLab.isMassPlaced()) {
                            selectedMass.setLayoutY(250);
                        }

                    } else if (chosenItem.getName().contains("kg")) {
                        selectedMass.setImage(chosenItem.getImage());

                        if (springLab.isSpringPlaced()) {
                            // If spring is placed, offset mass below
                            selectedMass.setLayoutY(250);
                        } else {
                            // If spring hasn't been placed yet, put mass at the spring's default position
                            selectedMass.setLayoutY(200);
                        }
                    }

                    //placedMassItem = chosenItem;
                    //massPlaced = true;

                    inventory.removeItem(chosenItem);
                    overlay.updateInventory();
                    chosenItem = placeHolder;
                }
            }
        });

        selectedSpring.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && placedSpringItem != null) {
                selectedSpring.setImage(null);
                inventory.addItem(placedSpringItem);
                overlay.updateInventory();
                placedSpringItem = null;
                springPlaced = false;

                if (massPlaced) {
                    // Move the mass up to where the spring was
                    selectedMass.setLayoutY(200);
                }
            }
        });

        selectedMass.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && placedMassItem != null) {
                selectedMass.setImage(null);
                inventory.addItem(placedMassItem);
                overlay.updateInventory();
                placedMassItem = null;
                massPlaced = false;
            }
        });

        Button playButton = new Button("Target");
        playButton.setLayoutX(400);
        playButton.setLayoutY(600);
        playButton.setOnAction(e -> {
            boolean springMissing = selectedSpring.getImage() == null;
            boolean massMissing = selectedMass.getImage() == null;

            if (springMissing && massMissing) {
                root.getChildren().add(monologuesL.get(1));
                //"You haven’t placed anything on the stand..."
            } else if (!springMissing && massMissing) {
                //if only mass is placed
                //"You didn’t oscillate your mind hard enough...""and so you’ve been stranded as the days swing back and forth"
                //"You didn’t even attempt to oscillate an object...""And so you’ve been stranded... As the days oscillate endlessly."
                //wrongLab();
            } else if (springMissing && !massMissing) {
                //if only spring is placed
                //"You didn’t oscillate your mind hard enough...""and so you’ve been stranded as the days swing back and forth"
                //"You didn’t even attempt to oscillate an object...""And so you’ve been stranded... As the days oscillate endlessly."
                //wrongLab();
            } else {
                // TODO: create springOscillation
                startSpringOscillation(selectedSpring, selectedMass);
            }
        });
        root.getChildren().addAll(table, springStand, selectedSpring, selectedMass, overlay.getOverlayPane(), playButton, goBack);
        addInventory(root);
        stage.setScene(new Scene(root, 1000, 650));
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
            case "3kg": return 3;
            case "4kg": return 4;
            case "5kg": return 5;
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

    /**
     * Displays the spring and mass oscillation animations and evaluates the solution.
     * @param selectedSpring ImageView of the placed spring
     * @param selectedMass ImageView of the placed mass
     */
    private void startSpringOscillation(ImageView selectedSpring, ImageView selectedMass) {
        if (selectedSpring.getImage() == null || selectedMass.getImage() == null) return;

        String springName = getItemNameFromImage(selectedSpring.getImage());
        String massName = getItemNameFromImage(selectedMass.getImage());

        double k = getSpringConstantFromName(springName);
        double m = getMassFromName(massName);
        double omega = Math.sqrt(k / m); // Angular frequency

        // mass oscillation
        TranslateTransition massOscillation = new TranslateTransition();
        massOscillation.setNode(selectedMass);
        massOscillation.setDuration(Duration.seconds(2));
        massOscillation.setCycleCount(TranslateTransition.INDEFINITE);
        massOscillation.setAutoReverse(true);
        massOscillation.setByY(50); // amplitude swing

        //spring oscillation
        ScaleTransition springOscillation = new ScaleTransition();
        springOscillation.setNode(selectedSpring);
        springOscillation.setDuration(Duration.seconds(2));
        springOscillation.setCycleCount(ScaleTransition.INDEFINITE);
        springOscillation.setAutoReverse(true);
        springOscillation.setFromY(1.0);
        springOscillation.setToY(1.3);

        massOscillation.play();
        springOscillation.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(e -> {
            massOscillation.stop();
            springOscillation.stop();

            if (placedSpringItem != null && placedMassItem != null &&
                    placedSpringItem.getName().contains("200") &&
                    placedMassItem.getName().contains("4")) {
                isDoorUnlocked = true;
                //showDoorOpenScene();
//            (Math.abs(omega - Math.sqrt(200 / 4.0)) < 0.1) {
//                isDoorUnlocked = true;
//                showDoorOpenScene(); // Only open door if correct
            } else {
                isDoorUnlocked = false;
                KillPlayer.killPlayer("You missed the target...\nThe oscillations carried on without purpose. Trapped in repetition, you remain.", stage, stage.getScene(), overlay);
            }
        });

        pause.play();
    }

    /**
     * Displays the Circuit Lab scene
     */
    private void startRCircuitLab() {
        rCircuit lab = new rCircuit(stage);
        try {
            lab.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // Start an AnimationTimer to periodically check if the solution is correct

    /**
     * Begins periodic checking of the player's solution using AnimationTimer.
     */
    private void startSolutionCheck() {
        AnimationTimer solutionCheckTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (springLab.checkSolution()) {
                    startRCircuitLab();
                    stop();  // Stop the timer after transitioning to the next lab
                }
            }
        };
        solutionCheckTimer.start(); // Start the timer
    }

    /**
     * Initializes the language-specific monologue settings.
     */
    public void initialize() {
        if (Objects.equals(player.getLanguage(), "english")) {
            monologuesL.clear();
            monologuesL = monologues;
        } else {
            monologuesL.clear();
            monologuesL = monologuesF;
        }
        //monologues.addAll(List.of(doorLockedMessage, nothingPlacedMessage));
        monologuesF.addAll(List.of());
        hideImage(person);
//      "Something must fall. Something must stretch."
//      "Choose wisely. Or stay... forever oscillating."
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

    private void wrongLab (Item wrongItem, StackPane stackPane, Stage stage) {
        removeFromInventory(wrongItem);
        stackPane.getChildren().remove(monologuesL.get(1));
        stackPane.getChildren().add(monologuesL.get(1));
        lifeManager.decreaseLife();
        //lifeManager.kill(inventoryPane, );
    }

    /**
     * Easy add inventory image and set up.
     */
    private void addInventory(StackPane stackPane) {
        ImageView inventoryImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/inventory.png")).toExternalForm()));
        inventoryImage.setMouseTransparent(true);
        stackPane.getChildren().add(inventoryImage);
    }
}
