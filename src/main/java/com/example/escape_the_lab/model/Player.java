package com.example.escape_the_lab.model;

import com.example.escape_the_lab.ui.Inventory;

public class Player {
    private int lives;
    private Inventory inventory;

    public Player() {
        this.lives = 3;
        this.inventory = new Inventory();
    }

    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    //to increase lives (could be used for power-ups)
    public void gainLife() {
        if (lives < 3) {
            lives++;
        }
    }

    //to get the current number of lives
    public int getLives() {
        return lives;
    }

    //to use an item from the inventory
//    public void useItem(String item) {
//        inventory.useItem(item);
//    }
//
//    // Getter for inventory (to interact with inventory)
//    public Inventory getInventory() {
//        return inventory;
//    }
}

