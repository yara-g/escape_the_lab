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
    private ImageView inv1;
    private ImageView inv2;
    private ImageView inv3;
    private ImageView inv4;
    private ImageView inv5;
    private ImageView inv6;
    private Group pane;

    public Overlay(Inventory inventory, LifeManager lifeManager) {
        this.lifeManager = lifeManager;
        this.inventory = inventory;
    }

    public Group getInventoryPane() {
        return pane;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void updateInventory() {
        List<Item> items = inventory.getItems();
        ImageView[] inventorySpots = new ImageView[] {inv1, inv2, inv3, inv4, inv5, inv6};
        VBox vBox = new VBox();

        for (int i = 0; i < items.size(); i++) {
            inventorySpots[i] = items.get(i).getImageView();
            vBox.getChildren().add(inventorySpots[i]);
            System.out.println("added " + items.get(i).getName());
        }

        vBox.setSpacing(28);
        vBox.setTranslateY(80);
        pane = new Group(vBox);
        vBox.setTranslateX(880);
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
