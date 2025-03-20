package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.LifeManager;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Overlay {
    private LifeManager lifeManager;
    private Inventory inventory;
    private Button inv1;
    private Button inv2;
    private Button inv3;
    private Button inv4;
    private Button inv5;
    private Button inv6;
    private Group pane;

    public Overlay(Inventory inventory, LifeManager lifeManager) {
        this.lifeManager = lifeManager;
        this.inventory = inventory;

        inv1 = new Button("button");
        inv2 = new Button("button");
        inv3 = new Button("button");
        inv4 = new Button("button");
        inv5 = new Button("button");
        inv6 = new Button("button");

        VBox vBox = new VBox(inv1, inv2, inv3, inv4, inv5, inv6);
        vBox.setSpacing(63);
        vBox.setTranslateY(90);
        pane = new Group(vBox);
        vBox.setTranslateX(880);
    }

    public Group getInventoryPane() {
        return pane;
    }
    public Inventory getInventory() {
        return inventory;
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
