package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.LifeManager;
import com.example.escape_the_lab.model.Item;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Overlay {
    private LifeManager lifeManager;
    private Inventory inventory;
    private Group inventoryPane; // Shared inventory pane
    private ImageView inv1;
    private ImageView inv2;
    private ImageView inv3;
    private ImageView inv4;
    private ImageView inv5;
    private ImageView inv6;

    public Overlay(Inventory inventory, LifeManager lifeManager, Group inventoryPane) {
        this.lifeManager = lifeManager;
        this.inventory = inventory;
        this.inventoryPane = inventoryPane;
    }

    public Group getInventoryPane() {
        return inventoryPane;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void updateInventory() {
        List<Item> items = inventory.getItems();
        VBox vBox = new VBox();

        vBox.getChildren().clear(); // Clear existing items before adding new ones

        for (Item item : items) {
            vBox.getChildren().add(item.getImageView());
        }

        vBox.setSpacing(38);
        vBox.setTranslateY(80);
        vBox.setTranslateX(880);

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
