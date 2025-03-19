package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.LifeManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Overlay {
    private LifeManager lifeManager;
    private Inventory inventory;
    private Scene mainScene;
    private Scene roomScene;
    private Button inv1;
    private Button inv2;
    private Button inv3;
    private Button inv4;
    private Button inv5;
    private Button inv6;

    public Overlay(Inventory inventory, LifeManager lifeManager, Scene roomScene) {
        this.lifeManager = lifeManager;
        this.inventory = inventory;
        this.roomScene = roomScene;

        inv1 = new Button("button");
        inv2 = new Button("button");
        inv3 = new Button("button");
        inv4 = new Button("button");
        inv5 = new Button("button");
        inv6 = new Button("button");

    }

    public Scene getMainScene() {
        return mainScene;
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
