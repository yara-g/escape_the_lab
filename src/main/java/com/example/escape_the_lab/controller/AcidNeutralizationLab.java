package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.Substance;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXML;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AcidNeutralizationLab extends Lab {

    private String acid;
    private String base;
    public ArrayList<Substance> activeSubstances = new ArrayList<>();
    @FXML
    ImageView AcidImage1, AcidImage2, AcidImage3, AcidImage4, AcidImage5, BaseImage1, BaseImage2, BaseImage3, BaseImage4, BaseImage5, AcidSprite1, AcidSprite2, AcidSprite3, AcidSprite4, AcidSprite5, BaseSprite1, BaseSprite5, BaseSprite2, BaseSprite3, BaseSprite4;
    @FXML
    StackPane AcidHome1, AcidHome2, AcidHome3, AcidHome4, AcidHome5, AcidDisplay1, AcidDisplay2, AcidDisplay3, AcidDisplay4, AcidDisplay5;
    @FXML
    StackPane BaseHome1, BaseHome2, BaseHome3, BaseHome4, BaseHome5, BaseDisplay1, BaseDisplay2, BaseDisplay3, BaseDisplay4, BaseDisplay5;
    @FXML
    Pane arenaPane;
    @FXML
    VBox acidBank, baseBank;
    private Lab lab;

    @Override
    public void startLab() {
        setupLab();
    }

    @Override
    public boolean checkSolution() {
        return false;
    }

    @Override
    public void failLab() {
    resetSubstances();
    }

    @Override
    public void setupLab() {
        AcidSprite1.setOpacity(0);
        AcidSprite2.setOpacity(0);
        AcidSprite3.setOpacity(0);
        AcidSprite4.setOpacity(0);
        AcidSprite5.setOpacity(0);
        BaseSprite1.setOpacity(0);
        BaseSprite2.setOpacity(0);
        BaseSprite3.setOpacity(0);
        BaseSprite4.setOpacity(0);
        BaseSprite5.setOpacity(0);
        setupDrag();
        setTarget();
    }

    @Override
    public Scene createScene() {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcidNeutralizationLab_layout.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1000, 650);
        return scene;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
    }

    public void initializeLab(Lab lab) {
        this.lab = lab;
    }

    /**
     * Set up draggable substances from the VBox
     */
    private void setupDrag() {
        StackPane[] substances = {AcidDisplay1, AcidDisplay2, AcidDisplay3, AcidDisplay4, AcidDisplay5, BaseDisplay1, BaseDisplay2, BaseDisplay3, BaseDisplay4, BaseDisplay5};

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
        Dragboard db = event.getDragboard();//container
        if (db.hasImage()) {
            //Create instance of Substance class to represent the dragged substance
            StackPane draggedDisplay = (StackPane) event.getGestureSource();

            Substance currentSubstance = createNewSubstance(draggedDisplay);
            try {
                // Move the substance to the arena
                assert currentSubstance != null; // There should be a substance selected
                currentSubstance.sprite.setFitWidth(100);
                currentSubstance.sprite.setFitHeight(100);
                currentSubstance.setActive(true);

                // Set the position of the substance to where it was dropped
                currentSubstance.display.setLayoutX(event.getX() - 50);
                currentSubstance.display.setLayoutY(event.getY() - 50);

                // Add the substance to the arena if it's not already there
                if (!arenaPane.getChildren().contains(currentSubstance.display)) {
                    arenaPane.getChildren().add(currentSubstance.display);
                    currentSubstance.sprite.setOpacity(1);

                }

                // make the substance draggable within the arena
                Draggable(currentSubstance);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

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

        // Set up the acidBank and baseBank to accept subastances dragged back from the arena
        acidBank.setOnDragOver(event -> {
            if (event.getGestureSource() != acidBank && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });

        baseBank.setOnDragOver(event -> {
            if (event.getGestureSource() != baseBank && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });

        // handle the drop when the substance is dragged back to the acidBank and baseBank
        acidBank.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            if (db.hasImage()) {
                removeSubstance(substance);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
        });

        baseBank.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            if (db.hasImage()) {
                removeSubstance(substance);
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
        // Remove the dragged substance from its current parent
        arenaPane.getChildren().remove(substance.display);
        substance.display.setOpacity(0);
        substance.sprite.setFitWidth(100);
        substance.sprite.setFitHeight(100);

        switch (substance.acidNumber()) {
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
        switch (substance.baseNumber()) {
            case 1 ->
                BaseHome1.getChildren().addLast(substance.display);
            case 2 ->
                BaseHome2.getChildren().addLast(substance.display);
            case 3 ->
                BaseHome3.getChildren().addLast(substance.display);
            case 4 ->
                BaseHome4.getChildren().addLast(substance.display);
            case 5 ->
                BaseHome5.getChildren().addLast(substance.display);
        }

        Draggable(substance);
        activeSubstances.remove(substance);
        // substance.setActive(false);

    }
//    /**
//     * Create a new instance of the Substance class 
//     *
//     * @param substanceDisplay the StackPane to be assigned to the new Substance
//     * @return the new Substance object
//     */

    private Substance createNewSubstance(StackPane substanceDisplay) {
        return null;
//        Substance currentSubstance = null;
//        ImageView substanceImage = new ImageView();
//        for (Node node : substanceDisplay.getChildren()) {
//            if (node instanceof ImageView) {
//                substanceImage = (ImageView) node;
//            }
//        }
//
//        // Only create a new instance if the substance is new to the arena
//    if (substanceImage != null && !Substance.existsIn(activeSubstances, substanceImage)) {
//        Substance newSubstance = new Substance(
//               
//        );
//
//        System.out.println("In createNewSubstance()");
//
//        switch (newSubstance.acidNumber()) {
//            case 1 -> newSubstance.setHome(AcidHome1);
//            case 2 -> newSubstance.setHome(AcidHome2);
//            case 3 -> newSubstance.setHome(AcidHome3);
//            case 4 -> newSubstance.setHome(AcidHome4);
//            case 5 -> newSubstance.setHome(AcidHome5);
//        }
//        switch (newSubstance.baseNumber()) {
//            case 1 -> newSubstance.setHome(BaseHome1);
//            case 2 -> newSubstance.setHome(BaseHome2);
//            case 3 -> newSubstance.setHome(BaseHome3);
//            case 4 -> newSubstance.setHome(BaseHome4);
//            case 5 -> newSubstance.setHome(BaseHome5);
//        }
//
//        for (Substance other : activeSubstances) {
//            other.setFocused(false);
//        }
//
//        newSubstance.setFocused(true);
//
//        // Handle focus of substances
//        newSubstance.display.setOnMousePressed(event -> {
//            for (Substance other : activeSubstances) {
//                other.setFocused(false);
//            }
//
//            newSubstance.setFocused(true);
//        });
//
//        activeSubstances.add(newSubstance);
//
//        currentSubstance = newSubstance;
//    } else {
//        for (Substance substance : activeSubstances) {
//            if (substance.sprite.equals(substanceImage)) {
//                currentSubstance = substance;
//            }
//        }
//    }
//
//    return currentSubstance;
    }

    public void mixSolutions() {

    }
    private void resetSubstances() {
    // Example: Remove substances from arenaPane and return them to bank
    for (Substance substance : activeSubstances) {
        arenaPane.getChildren().remove(substance.display);
        // Reset to original position in VBox
        baseBank.getChildren().add(substance.display);
    }
    }
}
