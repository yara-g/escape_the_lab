package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.*;
import com.example.escape_the_lab.model.Item;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.escape_the_lab.controller.GameController.player;

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

    ImageView light = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/lightS.png")).toExternalForm()));
    ImageView door = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/doorS.png")).toExternalForm()));
    ImageView mainChair = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sofaS.png")).toExternalForm()));
    ImageView mainDrawer = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/drawer.png")).toExternalForm()));
    ImageView mainShelves = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/shelves.png")).toExternalForm()));
    ImageView person = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/personS.png")).toExternalForm()));

    Item placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");
    ImageView shelves = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/shelves.png")).toExternalForm()));
    ImageView mass1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/clockStatue.png")).toExternalForm())); //book
    ImageView mass2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/books.png")).toExternalForm()));  // Correct choice
    ImageView mass3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/globe.png")).toExternalForm()));

    ImageView chair = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/sofaS.png")).toExternalForm()));
    ImageView spring1 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/spring1.png")).toExternalForm()));
    ImageView spring2 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/spring2.png")).toExternalForm()));  // Correct choice
    ImageView spring3 = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/spring3.png")).toExternalForm()));

    Item spring1Item = new Item("100N/m", "/images/spring1.png");
    Item spring2Item = new Item("200N/m", "/images/spring2.png");
    Item spring3Item = new Item("300N/m", "/images/spring3.png");
    Item mass1Item = new Item("4kg", "/images/AAASpringLab/clockStatue.png");
    Item mass2Item = new Item("3kg", "/images/AAASpringLab/books.png");
    Item mass3Item = new Item("5kg", "/images/AAASpringLab/globe.png");

    //lab scene
    ImageView springStand = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/spring-Stand.png")).toExternalForm()));
    ImageView table = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/table.png")).toExternalForm()));

    ImageView doorLockedMessage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/door..png")).toExternalForm()));
    ImageView nothingPlacedMessage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAASpringLab/door..png")).toExternalForm()));
    List<ImageView> monologues = new ArrayList<>();
    List<ImageView> monologuesF = new ArrayList<>();
    List<ImageView> monologuesL = new ArrayList<>();

    // Constructor
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

        StackPane root = new StackPane(light, door, mainChair, mainDrawer, mainShelves, person, skipToNext);
        addInventory(root);

        overlay.getHelpButton().setOnMouseClicked(e -> {
            Image helpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/help/springFormula.png")));
            Help.show("Select the right spring and mass so the oscillation is just right to reach the button by" +
                    " matching the spring's oscillation frequency!\n" + "Use the formula:", helpImage);
        });

        door.setOnMouseClicked(event -> root.getChildren().add(monologuesL.getFirst()));
        mainChair.setOnMouseClicked(event -> showSpringsScene());
        mainDrawer.setOnMouseClicked(event -> showLabScene());
        mainShelves.setOnMouseClicked(event -> showShelvesScene());
        light.setOnMouseClicked(event -> {
            showImage(person);
        });
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

    // Show springs scene with different types of springs
    private void showSpringsScene() {
        spring1Item.getImageView().setOnMouseClicked(e -> chosenItem = spring1Item);
        spring2Item.getImageView().setOnMouseClicked(e -> chosenItem = spring2Item);
        spring3Item.getImageView().setOnMouseClicked(e -> chosenItem = spring3Item);

        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        goBack.setOnMouseClicked(e -> showMainScene());

        spring1.setOnMouseClicked(e -> addIntoInventory(spring1, spring1Item));
        spring2.setOnMouseClicked(e -> addIntoInventory(spring2, spring2Item));
        spring3.setOnMouseClicked(e -> addIntoInventory(spring3, spring3Item));

        StackPane root = new StackPane(chair, spring1, spring2, spring3, overlay.getOverlayPane(), goBack);
        addInventory(root);
        stage.setScene(new Scene(root, 1000, 650));
        startSolutionCheck();
    }

    // Show drawers scene with different masses
    private void showShelvesScene() {
        mass1Item.getImageView().setOnMouseClicked(e -> chosenItem = mass1Item);
        mass2Item.getImageView().setOnMouseClicked(e -> chosenItem = mass2Item);
        mass3Item.getImageView().setOnMouseClicked(e -> chosenItem = mass3Item);

        mass1.setOnMouseClicked(e -> addIntoInventory(mass1, mass1Item));
        mass2.setOnMouseClicked(e -> addIntoInventory(mass2, mass2Item));
        mass3.setOnMouseClicked(e -> addIntoInventory(mass3, mass3Item));

        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        goBack.setOnMouseClicked(e -> showMainScene());

        StackPane root = new StackPane(shelves, mass1, mass2, mass3, overlay.getOverlayPane(), goBack);
        addInventory(root);
        stage.setScene(new Scene(root, 1000, 650));
        startSolutionCheck();
    }

    private void showLabScene() {
        ImageView goBack = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/back.png")).toExternalForm()));
        goBack.setOnMouseClicked(e -> showMainScene());
        StackPane root = new StackPane();

        springStand.setOnMouseClicked(e -> {
            if (chosenItem != null && !chosenItem.equals(placeHolder)) {
                if (chosenItem.getName().contains("N/m")){
                    selectedSpring.setImage(chosenItem.getImage());
                    selectedSpring.setFitWidth(100);
                    selectedSpring.setFitHeight(100);
                    selectedSpring.setLayoutX(440);
                    selectedSpring.setLayoutY(200);
                    placedSpringItem = chosenItem;
                    springPlaced = true;

                    if (massPlaced) {
                        selectedMass.setLayoutY(250);
                    }

                } else if (chosenItem.getName().contains("kg")) {
                    selectedMass.setImage(chosenItem.getImage());
                    selectedMass.setFitWidth(100);
                    selectedMass.setFitHeight(100);
                    selectedMass.setLayoutX(440);

                    if (springPlaced) {
                        // If spring is placed, offset mass below
                        selectedMass.setLayoutY(250);
                    } else {
                        // If spring hasn't been placed yet, put mass at the spring's default position
                        selectedMass.setLayoutY(200);
                    }

                    placedMassItem = chosenItem;
                    massPlaced = true;

                }
                inventory.removeItem(chosenItem);
                overlay.updateInventory();
                chosenItem = placeHolder;
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

    // Start the rCircuit lab
    private void startRCircuitLab() {
        rCircuit lab = new rCircuit(stage);
        try {
            lab.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // Start an AnimationTimer to periodically check if the solution is correct
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

    public void initialize() {
        if (Objects.equals(player.getLanguage(), "english")) {
            monologuesL.clear();
            monologuesL = monologues;
        } else {
            monologuesL.clear();
            monologuesL = monologuesF;
        }
        monologues.addAll(List.of(doorLockedMessage, nothingPlacedMessage));
        monologuesF.addAll(List.of());

        spring1Item.size();
        spring2Item.size();
        spring3Item.size();
        mass1Item.size();
        mass2Item.size();
        mass3Item.size();
        hideImage(person);
        hideImage(mainDrawer);
        hideImage(mainShelves);
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
