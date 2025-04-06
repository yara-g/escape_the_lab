package com.example.escape_the_lab.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Item {
    private String name;
    private Image image;
    private ImageView imageView;

    public boolean isItemSelected() {
        return itemSelected;
    }

    private boolean itemSelected = false;

    public Item(String name, String imagePath) {
        this.name = name;
        this.image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        this.imageView = new ImageView(image);
        //TEMPORARY this works for the rest of the labs
        this.imageView.setFitWidth(50); // Set a reasonable width
        this.imageView.setFitHeight(50); // Set a reasonable height
        this.imageView.setPreserveRatio(true);

        imageView.setOnMouseClicked(e -> select());
    }

    public String getName() {
        return name;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Image getImage() {
        return image;
    }

    public void unselect() {
        this.itemSelected = false;
        System.out.println(name + " unselected.");
    }

    public void select() {
        this.itemSelected = true;
        System.out.println(name + " selected.");
    }

    public void setItem(Item item) {
        this.name = item.getName();
        this.image =  item.getImage();
        this.imageView =  item.getImageView();
    }
}
