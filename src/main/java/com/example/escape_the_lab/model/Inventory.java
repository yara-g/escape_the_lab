package com.example.escape_the_lab.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void resetInventory() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean hasItem(Item look) {
        for (Item item : items) {
            if (item.equals(look)) {
                return true;
            }
        }
        return false;
    }
}
