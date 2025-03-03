package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FlameLab extends Lab {
    //Flame test
    ImageView flameColorCrimson = new ImageView(new Image("file:")); //Li+
    ImageView flameColorGreen = new ImageView(new Image("file:")); //Ba2+
    ImageView flameColorLilac = new ImageView(new Image("file:")); //K+
    ImageView flameColorYellow = new ImageView(new Image("file:")); //Na+

    // Regulate a Bunsen burner <- find from room
    // A clean wire loop <- find
    // Test tubes: 3, be on table.
    // Tiny paper hint on table says: "Look around you"
    // Possible flames : 4 colors, right color is the wall paper.
    // Collect the flame to put on a slot on door
    // If same door opens if not burn your hand.

    // background
    // image view for lab, drawer or other, door, 2 random useless things.
    // lab tools, inside drawer, changes for things (swatch eyes in image, etc.)
    // 1000/650
    // start/end

    private String powder;

    @Override
    public void startLab() {

    }

    @Override
    public boolean checkSolution() {
        return false;
    }

    @Override
    public void failLab() {

    }
    public void changeFlameColor() {

    }
}
