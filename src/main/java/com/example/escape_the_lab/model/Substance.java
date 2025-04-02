package com.example.escape_the_lab.model;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Objects;

public class Substance {
        private String name;
        private double pH;
        private ImageView imageView;
        public ImageView sprite, home;
        private boolean active, focused;
        public StackPane display = new StackPane();
        public double initialPositionX, initialPositionY;

        public Substance(String name, double pH, ImageView sprite, double initialPositionX, double initialPositionY) {
            this.name = name;
            this.pH = pH;
            this.initialPositionX = initialPositionX;
            this.initialPositionY = initialPositionY;
            this.sprite = sprite;
            this.sprite.setScaleX(-1);
            //this.home = home;
            setupDisplay();
        }
        public Substance(ImageView sprite) {
            this("Unnamed", 7.0, sprite, sprite.getLayoutX(), sprite.getLayoutY());
            // Default name = "Unnamed", pH = 7.0 (neutral)
        }

        private void setupDisplay() {
            VBox displaySubstanceData = new VBox(5);


            displaySubstanceData.setAlignment(Pos.CENTER);
            displaySubstanceData.setTranslateY(-70);

            display.getChildren().addAll(sprite, displaySubstanceData);
        }
        // Getters and setters
        public String getName() {
            return name;
        }

        public double getPh() {
            return pH;
        }

        public ImageView getSprite() {
            return sprite;
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
        public static boolean existsIn(ArrayList<Substance> list, ImageView image) {
            for (Substance substance : list) {
                if (substance.sprite.equals(image)) {
                    return true;
                }
            }
            return false;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Substance that = (Substance) obj;
            return Objects.equals(name, that.name); // Compare based on substance name
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public String toString() {
            return "Substance{name='" + name + "'}";
        }

    }




