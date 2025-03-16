package com.example.escape_the_lab.model;

import javafx.scene.image.ImageView;

public abstract class Base extends Substance {
    public Base (String name, double pH, ImageView imageView) {
        super(name, pH, imageView);
}
    @Override
    public int baseNumber() {
        System.out.println(sprite.getImage().getUrl().charAt(sprite.getImage().getUrl().length() - 5));
        return Integer.parseInt(String.valueOf(sprite.getImage().getUrl().charAt(sprite.getImage().getUrl().length() - 5)));
    }
}
