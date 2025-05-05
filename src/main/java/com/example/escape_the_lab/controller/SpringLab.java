package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.SpringModel;
import com.example.escape_the_lab.ui.Overlay;
import com.example.escape_the_lab.ui.rSpring;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The SpringLab class is the controller for the spring-mass physics lab in the game.
 * It manages the logic for placing items (spring and mass), checking combinations,
 * and interacting with the SpringModel and UI (rSpring).
 */
public class SpringLab extends Lab {
    private final Stage stage;
    private final rSpring springLabUI;
    private final SpringModel springModel;
    private final Overlay overlay;

    private Item placedSpringItem;
    private Item placedMassItem;
    private boolean springPlaced = false;
    private boolean massPlaced = false;

    private final double correctMass = 2.0;  //in kg
    private final double correctSpringConstant = 50; //k in N/m

    public SpringLab(Stage stage) {
        this.stage = stage;
        this.overlay = GameController.getOverlay();
        this.springModel = new SpringModel(-1, -1); //placeholder values
        this.springLabUI = new rSpring(stage, this);
    }

    /**
     * Starts the lab by displaying the main UI scene.
     */
    @Override
    public void startLab() {
        springLabUI.showMainScene();
    }

    /**
     * Returns the scene for the lab. Not used here as the UI handles it directly.
     * @return
     */
    @Override
    public Scene createScene() {
        return null; //UI class does this (rSpring)
    }

    /**
     * Attempts to place a given item in the lab.
     * @param item the item to place
     * @return true if placement was successful
     */
    public boolean tryPlaceItem(Item item) {
        if (item == null) return false;

        if (item.getName().contains("N/m")) {
            placedSpringItem = item;
            springPlaced = true;
            return true;
        } else if (item.getName().contains("kg")) {
            placedMassItem = item;
            massPlaced = true;
            return true;
        }

        return false;
    }

    /**
     * Returns whether a spring has been placed.
     *
     * @return true if a spring is placed
     */
    public boolean isSpringPlaced() {
        return springPlaced;
    }

    /**
     * Returns whether a mass has been placed.
     *
     * @return true if a mass is placed
     */
    public boolean isMassPlaced() {
        return massPlaced;
    }

    public Item getSpringItem() {
        return placedSpringItem;
    }

    public Item getMassItem() {
        return placedMassItem;
    }

    /**
     * Resets the lab state, removing all placed items.
     */
    public void reset() {
        placedSpringItem = null;
        placedMassItem = null;
        springPlaced = false;
        massPlaced = false;
    }

    /**
     * Attempts to remove an item from the lab.
     * @param item the item to remove
     * @return true if the item was removed
     */
    public boolean removeItem(Item item) {
        if (item == null) return false;

        if (item.equals(placedSpringItem)) {
            placedSpringItem = null;
            return true;
        } else if (item.equals(placedMassItem)) {
            placedMassItem = null;
            return true;
        }
        return false;
    }

    /**
     * Checks whether both a mass and spring have been placed.
     * @return true if both items are placed
     */
    public boolean isReadyToCheck() {
        return placedSpringItem != null && placedMassItem != null;
    }

    /**
     * Checks if the currently placed items form the correct solution.
     * @return true if the solution is correct (based on item names)
     */
    @Override
    public boolean checkSolution() {
        return placedSpringItem != null && placedSpringItem.getName().contains("200") &&
                placedMassItem != null && placedMassItem.getName().contains("4");
    }

    public double getSpringConstant() {
        if (placedSpringItem == null) return 0;
        String name = placedSpringItem.getName();
        if (name.contains("100")) return 100;
        if (name.contains("200")) return 200;
        if (name.contains("300")) return 300;
        return 0;
    }

    public double getMassValue() {
        if (placedMassItem == null) return 0;
        String name = placedMassItem.getName();
        if (name.contains("3")) return 3;
        if (name.contains("4")) return 4;
        if (name.contains("5")) return 5;
        return 0;
    }


//
//    public void setSelectedMass(double mass) {
//        springModel.setMass(mass);
//    }
//
//    public void setSelectedSpringConstant(double k) {
//        springModel.setSpringConstant(k);
//    }
//
//    public void attemptEscape() {
//        if (checkSolution()) {
//            System.out.println("Correct combination! Escape successful.");
//            gameController.transitionToNextLab();
//        } else {
//            overlay.getLifeManager().failLab();
//        }
//    }
}
