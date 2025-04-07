package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.LifeManager;
import com.example.escape_the_lab.model.Item;
import javafx.scene.Group;
import javafx.scene.layout.*;

import java.util.List;

public class Overlay {
    private LifeManager lifeManager;
    private Inventory inventory;
    private final Group inventoryPane; // Shared inventory pane
    private final Group overlayPane;
    private final Group lifePane;

    public Overlay(Inventory inventory, LifeManager lifeManager) {
        this.lifeManager = lifeManager;
        this.inventory = inventory;
        inventoryPane = new Group();
        lifePane = new Group();

        overlayPane = new Group(lifePane, inventoryPane);
    }

    public Group getOverlayPane() {
        return overlayPane;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updateLifeManager() {
        HBox hearts = lifeManager.getLifeDisplay();
        hearts.setTranslateX(858);
        hearts.setTranslateY(26);
        lifePane.getChildren().clear();
        lifePane.getChildren().add(hearts);
    }

    public void updateInventory() {
        List<Item> items = inventory.getItems();
        VBox vBox = new VBox();

        vBox.getChildren().clear(); // Clear existing items before adding new ones

        for (Item item : items) {
            vBox.getChildren().add(item.getImageView());
        }

        vBox.setSpacing(17);
        vBox.setTranslateY(75);
        vBox.setTranslateX(858);

        //TEMPORARY this works for the rest of the labs
//        vBox.setSpacing(38);
//        vBox.setTranslateY(80);
//        vBox.setTranslateX(880);

        inventoryPane.getChildren().clear(); // Clear the shared inventory pane
        inventoryPane.getChildren().add(vBox); // Add the updated VBox
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LifeManager getLifeManager() {
        return lifeManager;
    }

    public void setLifeManager(LifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }
}
