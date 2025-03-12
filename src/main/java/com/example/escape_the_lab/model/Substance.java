package com.example.escape_the_lab.model;

public class Substance {
        private String name;
        private double pH;
        private ImageView imageView;

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
    }


