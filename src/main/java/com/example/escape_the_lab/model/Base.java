package com.example.escape_the_lab.model;

public class Base extends Substance {
    public Base (String name, double pH, ImageView imageView) {
        super(name, pH, imageView);
}
    public int baseNumber() {
        System.out.println(sprite.getImage().getUrl().charAt(sprite.getImage().getUrl().length() - 5));
        return Integer.parseInt(String.valueOf(sprite.getImage().getUrl().charAt(sprite.getImage().getUrl().length() - 5)));
    }
}
