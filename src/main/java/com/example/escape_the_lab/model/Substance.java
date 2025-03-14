package com.example.escape_the_lab.model;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Substance {
        private String name;
        private double pH;
        private ImageView imageView;
        public ImageView sprite, home;
        private boolean active, focused;
        public StackPane display = new StackPane();

        public Substance(String name, double pH, ImageView imageView) {
            this.name = name;
            this.pH = pH;
            this.imageView = imageView;
        }

        // Getters and setters
        public String getName() {
            return name;
        }

        public double getPh() {
            return pH;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    public int substanceNumber() {
        System.out.println(sprite.getImage().getUrl().charAt(sprite.getImage().getUrl().length() - 5));
        return Integer.parseInt(String.valueOf(sprite.getImage().getUrl().charAt(sprite.getImage().getUrl().length() - 5)));
    }
     public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        if (focused) {
            display.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        } else {
            display.setBorder(null);
        }
        this.focused = focused;
    }
    public void setHome(ImageView home) {
        this.home = home;
    }
    /**
     * checks is the substance is active
     * @return if the substance is active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * makes the substance active
     * @param active is the subatnce active
     */
    public void setActive(boolean active) {
        this.active = active;
        activeStatusChanged();
    }
    public void activeStatusChanged() {
        if (active) {
            sprite.setOpacity(1);
            home.setOpacity(0.3);
        } else {
            sprite.setOpacity(0);
            home.setOpacity(1);
        }
    }

    public int acidNumber() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }


