package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.Substance;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class AcidNeutralizationLab extends Lab {
    private String acid;
    private String base;
    public ArrayList<Substance> activeSubstances = new ArrayList<>();
    @FXML
    ImageView AcidImage1, AcidImage2, AcidImage3, AcidImage4, AcidImage5, BaseImage1,BaseImage2, BaseImage3, BaseImage4, BaseImage5;
    @FXML
    StackPane AcidHome1, AcidHome2, AcidHome3, AcidHome4, AcidHome5, AcidDisplay1, AcidDisplay2, AcidDisplay3, AcidDisplay4, AcidDisplay5;
    @FXML
    StackPane BaseHome1, BaseHome2, BaseHome3, BaseHome4, BaseHome5, BaseDisplay1, BaseDisplay2, BaseDisplay3, BaseDisplay4, BaseDisplay5;
    @FXML
    Pane arenaPane;
     @FXML
     VBox acidBank, baseBank;


    @Override
    public void startLab() {

    }

    @Override
    public boolean checkSolution() {
        return false;
    }

    @Override
    public void failLab() {

    }

    @Override
    public void setupLab() {

    }

    @Override
    public Scene createScene() {
        return null;
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
            // make each duck draggable (thanks stackoverflow)
            Image finalImage = img.getImage();
            substance.setOnDragDetected(_ -> {
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

    /**
     * make the substance draggable within the arena after it's dropped
     */
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

    /**
     * Set up the arena to accept dropped subtances
     */
    private void setTarget() {
        arenaPane.setOnDragOver(event -> {
            if (event.getGestureSource() != arenaPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        });

        arenaPane.setOnDragDropped(this::addSubstanceToArena);
    }

    /**
     * Add the substance display to the arena Pane
     *
     * @param event The DragEvent from the drag and drop action of the user
     * moving the substance
     */
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


    /**
     * Sets up the substance and substance bank to handle the substance being dragged back to
     * the substance bank
     *
     * @param substance the Substance that is being dragged
     */
    private void makeSubstanceRemovable(Substance substance) {
        substance.display.setOnDragDetected(_ -> {
            Dragboard db = substance.display.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(substance.sprite.getImage());
            db.setContent(content);
        });

        // Set up the acidBank and baseBank to accept subastances dragged back from the arena
        acidBank.setOnDragOver(event -> {
            if (event.getGestureSource() != duckBank && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });

        baseBank.setOnDragOver(event -> {
            if (event.getGestureSource() != duckBank && event.getDragboard().hasImage()) {
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

    /**
     * removes the subsatnce from the arena
     *
     * @param substance selected substance
     */
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
        substance.setActive(false);

    }

    public void mixSolutions() {

    }
}
