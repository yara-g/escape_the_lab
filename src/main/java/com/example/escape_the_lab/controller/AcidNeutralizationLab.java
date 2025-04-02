package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.Substance;
import com.example.escape_the_lab.ui.Overlay;
import com.example.escape_the_lab.ui.rAcidNeutralization;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import java.util.Objects;

public class AcidNeutralizationLab extends Lab {
    public ArrayList<Substance> activeSubstances = new ArrayList<>();
    public ArrayList<Substance> droppedSubstances = new ArrayList<>();
    @FXML
    ImageView AcidSprite1, AcidSprite2, AcidSprite3, AcidSprite4, AcidSprite5, AcidImage1, AcidImage2, AcidImage3, AcidImage4, AcidImage5;
    @FXML
    StackPane AcidHome1, AcidHome2, AcidHome3, AcidHome4, AcidHome5, AcidDisplay1, AcidDisplay2, AcidDisplay3, AcidDisplay4, AcidDisplay5;
    @FXML
    Pane arenaPane;
    @FXML
    VBox AcidBank;
    private Lab lab;
    Stage primaryStage;
    Substance substance1;
    Substance substance2;
    Substance substance3;
    Substance substance4;
    Substance substance5;
    boolean succeedLab =  false;
    Button doorButton = new Button();
    rAcidNeutralization acidNeutralizationLabUI;

    public Overlay overlay;
    private final ImageView backGroundA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/bgA.png")).toExternalForm()));
    private final ImageView treeA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/treeA.png")).toExternalForm()));
    private final ImageView houseA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/houseA.png")).toExternalForm()));
    private final ImageView doorA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/doorA.png")).toExternalForm()));
    private final ImageView bigFlowerA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/bigFlowerA.png")).toExternalForm()));
    private final ImageView hintFlowerA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/hintFlowerA.png")).toExternalForm()));
    private final ImageView acidFloorA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/acidA.png")).toExternalForm()));
    private final ImageView inventory = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/inventory.png")).toExternalForm()));

    public AcidNeutralizationLab(Stage stage, Overlay overlay) {
        this.primaryStage = stage;
        this.acidNeutralizationLabUI = new rAcidNeutralization(stage, overlay);
        this.overlay = overlay;
    }

    @FXML
    private void initialize() {
        initializeLab(lab);
        AcidSprite1.setOpacity(0);
        AcidSprite2.setOpacity(0);
        AcidSprite3.setOpacity(0);
        AcidSprite4.setOpacity(0);
        AcidSprite5.setOpacity(0);
        setupDrag();
        setTarget();
        if (AcidBank == null) {
            System.out.println("arenaPane is still null!");
        }
        for (Substance s : activeSubstances) {
            System.out.println("Initialized: " + s.getName());
        }
    }

    @Override
    public void startLab() {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    @Override
    public boolean checkSolution() {
        if (droppedSubstances.isEmpty()) {
            return false;
        }
        boolean hasNeutralized = false;
        detectDroppedSubstances();

        for (Substance substance : droppedSubstances) {
            if (substance.equals(substance3) && substance.equals(substance5)) {
                hasNeutralized = true;
            }
            detectDroppedSubstances();
        }

        return hasNeutralized;
    }

    public void failLab() {
        showFailedScreen();
    }

    public void setupLab() {
        createScene();
    }

    @Override
    public Scene createScene() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcidNeutralizationLab_layout.fxml"));
            loader.setController(this);
            Parent labRoot = loader.load();
            doorOpen(primaryStage);
            bigFlowerA.setOnMouseClicked(mouseEvent ->
                    pressBigFlower());


            arenaPane.getChildren().addAll(backGroundA, acidFloorA, doorA, treeA, houseA, hintFlowerA, bigFlowerA, inventory, overlay.getOverlayPane());

            return new Scene(labRoot, 1000, 650);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void pressBigFlower() {
        Stage flowerStage = new Stage();


        Label failedLabel = new Label("A scientist accidentally spilled some very highly concentrated\n" +
                "HCl on the ground. Neutralize it to be able to pass!");
        failedLabel.setStyle("-fx-font-size: 16px;");
        failedLabel.setWrapText(true); // Allow automatic text wrapping
        failedLabel.setMaxWidth(450);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> flowerStage.close());

        VBox layout = new VBox(10, failedLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene failedScene = new Scene(layout, 500, 300);
        flowerStage.setScene(failedScene);
        flowerStage.show();

    }

    public void initializeLab(Lab lab) {
        this.lab = lab;
        substance1 = new Substance("Hydrochloric Acid", 1.0, AcidSprite1, 100, 100);
        substance2 = new Substance("Sulfuric Acid", 2.0, AcidSprite2, 100, 100);
        substance3 = new Substance("Sodium Hydroxide", 13.0, AcidSprite3, 100, 100);
        substance4 = new Substance("Ammonia", 11.0, AcidSprite4, 100, 100);
        substance5 = new Substance("Acetic Acid", 4.7, AcidSprite5, 100, 100);

        activeSubstances.add(substance1);
        activeSubstances.add(substance2);
        activeSubstances.add(substance3);
        activeSubstances.add(substance4);
        activeSubstances.add(substance5);

    }

    /**
     * Set up draggable substances from the VBox
     */
    private void setupDrag() {
        StackPane[] substances = {AcidHome1, AcidHome2, AcidHome3, AcidHome4, AcidHome5};
        for (StackPane substance : substances) {
            ImageView img = new ImageView();
            for (Node child : substance.getChildren()) {
                if (child instanceof ImageView) {
                    img = (ImageView) child;
                }
            }
            // make each substance draggable
            Image finalImage = img.getImage();
            substance.setOnDragDetected(event -> {
                Dragboard db = substance.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();//stores data
                content.putImage(finalImage);
                db.setContent(content);
            });

            // set initial size (or reset it if needed)
            img.setFitWidth(100);
            img.setFitHeight(100);
        }
    }

    //    /**
//     * make the substance draggable within the arena after it's dropped
//     */
    private void Draggable(Substance substance) {
        substance.display.setOnMousePressed(event -> substance.display.setUserData(new double[]{event.getSceneX(), event.getSceneY()}));

        substance.display.setOnMouseDragged(event -> {
            try {
                // update the stored mouse coordinates
                substance.display.setUserData(new double[]{event.getSceneX(), event.getSceneY()});

                // allow the substance to be dragged back to the VBox
                makeSubstanceRemovable(substance);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
//
//    /**
//     * Set up the arena to accept dropped subtances
//     */

    private void setTarget() {
        arenaPane.setOnDragOver(event -> {
            if (event.getGestureSource() != arenaPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        });
        arenaPane.setOnDragDropped(this::addSubstanceToArena);
    }
//
//    /**
//     * Add the substance display to the arena Pane
//     *
//     * @param event The DragEvent from the drag and drop action of the user
//     * moving the substance
//     */

    private void addSubstanceToArena(DragEvent event) {
        Dragboard db = event.getDragboard();

        if (db.hasImage()) {
            StackPane draggedDisplay = (StackPane) event.getGestureSource();

            Substance currentSubstance = createNewSubstance(draggedDisplay);

            try {
                // Move the substance to the arena
                assert currentSubstance != null; // There should be a substance selected
                currentSubstance.sprite.setFitWidth(100);
                currentSubstance.sprite.setFitHeight(100);
                Objects.requireNonNull(currentSubstance).setActive(true);

                // Set the position of the substance to where it was dropped
                currentSubstance.display.setLayoutX(event.getX() - 50);
                currentSubstance.display.setLayoutY(event.getY() - 50);

                currentSubstance.initialPositionX = currentSubstance.display.getLayoutX();
                currentSubstance.initialPositionY = currentSubstance.display.getLayoutY();

                // Add the substance to the arena if it's not already there
                if (!arenaPane.getChildren().contains(currentSubstance.display)) {
                    arenaPane.getChildren().add(currentSubstance.display);
                    currentSubstance.sprite.setOpacity(1);
                    droppedSubstances.add(currentSubstance);

                }

                // make the substance draggable within the arena
                Draggable(currentSubstance);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            detectDroppedSubstances();
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }

    }

    //    /**
//     * Sets up the substance and substance bank to handle the substance being dragged back to
//     * the substance bank
//     *
//     * @param substance the Substance that is being dragged
//     */
    private void makeSubstanceRemovable(Substance substance) {
        substance.display.setOnDragDetected(event -> {
            Dragboard db = substance.display.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(substance.sprite.getImage());
            db.setContent(content);
        });

        // Set up the acidBank to accept subastances dragged back from the arena
        AcidBank.setOnDragOver(event -> {
            if (event.getGestureSource() != AcidBank && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });

        // handle the drop when the substance is dragged back to the acidBank
        AcidBank.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            if (db.hasImage()) {
                removeSubstance(substance);
                activeSubstances.remove(substance);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
        });
    }
//
//    /**
//     * removes the subsatnce from the arena
//     *
//     * @param substance selected substance
//     */

    private void removeSubstance(Substance substance) {
        substance.display.setOpacity(0);
        substance.sprite.setFitWidth(100);
        substance.sprite.setFitHeight(100);

        switch (substance.substanceNumber()) {
            case 1 ->
                    AcidHome1.getChildren().addLast(substance.display);
            case 2 ->
                    AcidHome2.getChildren().addLast(substance.display);
            case 3 ->
                    AcidHome3.getChildren().addLast(substance.display);
            case 4 ->
                    AcidHome4.getChildren().addLast(substance.display);
            case 5 ->
                    AcidHome5.getChildren().addLast(substance.display);

        }

        Draggable(substance);
        substance.setActive(false);
        arenaPane.getChildren().remove(substance.display);

    }
//    /**
//     * Create a new instance of the Substance class
//     *
//     * @param substanceDisplay the StackPane to be assigned to the new Substance
//     * @return the new Substance object
//     */

    private Substance createNewSubstance(StackPane substanceDisplay) {

        Substance currentSusbtance = null;
        ImageView substanceImage = new ImageView();
        for (Node node : substanceDisplay.getChildren()) {
            if (node instanceof ImageView) {
                substanceImage = (ImageView) node;
            }
        }

        if (!Substance.existsIn(activeSubstances, substanceImage)) {
            Substance newSubstance = new Substance(substanceImage);
            switch (newSubstance.substanceNumber()) {
                case 1 ->
                        newSubstance = substance1;
                case 2 ->
                        newSubstance = substance2;
                case 3 ->
                        newSubstance = substance3;
                case 4 ->
                        newSubstance = substance4;
                case 5 ->
                        newSubstance = substance5;

            }
            switch (newSubstance.substanceNumber()) {
                case 1 ->
                        newSubstance.setHome(AcidSprite1);
                case 2 ->
                        newSubstance.setHome(AcidSprite2);
                case 3 ->
                        newSubstance.setHome(AcidSprite3);
                case 4 ->
                        newSubstance.setHome(AcidSprite4);
                case 5 ->
                        newSubstance.setHome(AcidSprite5);
            }

            for (Substance other : activeSubstances) {
                other.setFocused(false);
            }

            newSubstance.setFocused(true);

            // Handle focus of ducks
            newSubstance.display.pressedProperty().addListener(event -> {

                for (Substance other : activeSubstances) {
                    other.setFocused(false);
                }
            });

            activeSubstances.add(newSubstance);

            currentSusbtance = newSubstance;
        } else {
            for (Substance substance : activeSubstances) {
                if (substance.sprite.equals(substanceImage)) {
                    currentSusbtance = substance;
                }
            }
        }
        return currentSusbtance;

    }

    private void detectDroppedSubstances() {
        for (Substance substance : droppedSubstances) {
            System.out.println("Dropped substance: " + substance.getName());
        }
        System.out.println("Dropped substances: " + droppedSubstances);
        if (droppedSubstances.contains(substance3) && droppedSubstances.contains(substance5)) {
            System.out.println("Success! You neutralized the floor");
            arenaPane.setStyle("");
            succeedLab = true;
            doorOpen(primaryStage);
            arenaPane.getChildren().remove(acidFloorA);
            showSuccessScreen();
            // Add logic to proceed to the next level
        } else if (droppedSubstances.size() >= 2) {
            showFailedScreen();
            System.out.println("Incorrect substances! You lose a life.");
            // Add logic to reduce player lives
        }
    }


    private void showSuccessScreen() {
        succeedLab = true;
        Stage successStage = new Stage();
        successStage.setTitle("Lab Complete!");

        Label successLabel = new Label("You successfully neutralized the solution!\n\n "
                + "Hydrochloric acid (HCl) was neutralized by two different bases: \n"
                + "NaOH and CaCO₃. NaOH reacted in a typical acid-base reaction"
                + "to form salt and water,\n while CaCO₃ created a bubbling effect by releasing CO₂ gas.");
        successLabel.setStyle("-fx-font-size: 16px;");
        successLabel.setWrapText(true); // Allow automatic text wrapping
        successLabel.setMaxWidth(450);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> successStage.close());

        VBox layout = new VBox(10, successLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene successScene = new Scene(layout, 500, 300);
        successStage.setScene(successScene);
        successStage.show();
    }

    private void showFailedScreen() {
        Stage failedStage = new Stage();
        failedStage.setTitle("Lab Failed!");

        Label failedLabel = new Label("You have mixed the wrong substances!\n You just "
                + "lost a life. \n Hint: This is a special type of neutralization! Since "
                + "HCl is a strong acid, it requires two different bases to be fully neutralized");
        failedLabel.setStyle("-fx-font-size: 16px;");
        failedLabel.setWrapText(true); // Allow automatic text wrapping
        failedLabel.setMaxWidth(450);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> failedStage.close());

        VBox layout = new VBox(10, failedLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene failedScene = new Scene(layout, 500, 300);
        failedStage.setScene(failedScene);
        failedStage.show();
    }

    public void doorOpen(Stage stage) {
        if (succeedLab) {
//            doorButton.setOnAction((event) -> {
//                System.out.println("Opening door");
//            });
            doorA.setOnMouseClicked((event) -> {
                        System.out.println("Opening door");
                        doorA.setOnMouseClicked(e -> {
                            FlameLab f = new FlameLab();
                            f.startLab(stage, overlay);
                        });
                    }
            );
        }
        if (!succeedLab) {
//            doorButton.setOnAction((event) -> {
//                System.out.println("Door is locked");
//            });
            doorA.setOnMouseClicked((event) -> {
                System.out.println("Door is locked...");
            });
        }
    }

    public void openHouse() {
        houseA.setOnMouseClicked((event) -> {
            Button goBack = new Button("Go back");
            goBack.setOnAction(e -> {
                createScene();
            });
            Parent root = new Pane();
            primaryStage.setScene(new Scene(root));
        });
    }
    //    private Substance findExistingSubstance(StackPane substanceDisplay) {
//    ImageView substanceImage = null;
//
//    // Extract the ImageView from the StackPane
//    for (Node node : substanceDisplay.getChildren()) {
//        if (node instanceof ImageView) {
//            substanceImage = (ImageView) node;
//        }
//    }
//
//    // If no image was found, return null
//    if (substanceImage == null) {
//        return null;
//    }
//
//    // Search for the existing substance that matches the image
//    for (Substance substance : activeSubstances) {
//        if (substance.sprite.equals(substanceImage)) {
//            return substance;  // Found the existing substance, return it
//        }
//    }System.out.println("Active substances:");
//for (Substance substance : activeSubstances) {
//    System.out.println(substance.getName() + " - " + substance.getSprite());
//}
//if (substanceImage == null) {
//    System.out.println("Error: No ImageView found in StackPane.");
//    return null;
//}
//System.out.println("Extracted ImageView: " + substanceImage);
//for (Substance substance : activeSubstances) {
//    System.out.println("Comparing with: " + substance.getSprite());
//    if (substance.getSprite() == substanceImage) {  // Using '==' for reference comparison
//        System.out.println("Match found: " + substance.getName());
//        return substance;
//    }
//}
//for (Substance substance : activeSubstances) {
//    if (substance.getSprite().getImage().getUrl().equals(substanceImage.getImage().getUrl())) {
//        System.out.println("Match found based on image URL: " + substance.getName());
//        return substance;
//    }
//}
//
//
//    return null;  // No existing substance was found
//    }
}
