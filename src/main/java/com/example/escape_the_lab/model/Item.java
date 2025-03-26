package com.example.escape_the_lab.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Item {
    private String name;
    private Image image;
    private ImageView imageView;


    public Item(String name, String imagePath) {
        this.name = name;
        this.image = new Image(getClass().getResourceAsStream(imagePath));
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(50); // Set a reasonable width
        this.imageView.setFitHeight(50); // Set a reasonable height
        this.imageView.setPreserveRatio(true); // Maintain aspect ratio

        imageView.setOnMouseClicked(e -> {
            use();
        });
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

    public void use() {
        System.out.println(name + " used.");
    }
}
