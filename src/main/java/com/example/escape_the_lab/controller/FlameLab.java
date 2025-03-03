package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Lab;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class FlameLab extends Lab {
    // Flame test
    // Possible flames : 4 colors, right color is the wall paper.
    private final ImageView flameColorCrimsonLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorGreenLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorLilacLab = new ImageView(new Image("file:")); //
    private final ImageView flameColorYellowLab = new ImageView(new Image("file:")); //
    // Chosen flame color.
    // Collect the flame to put on a slot on door
    // My hand! It burns! (wrong color)
    // Thank God I don't have to play with fire anymore... (right color)
    private ImageView flameColor;
    private final ImageView flameColorCrimsonTool = new ImageView(new Image("file:"));
    private final ImageView flameColorGreenTool = new ImageView(new Image("file:"));
    private final ImageView flameColorLilacTool = new ImageView(new Image("file:"));
    private final ImageView flameColorYellowTool = new ImageView(new Image("file:"));
    // Powders to find from microscope (and they appear as the chemical formula)
    // Are those...? (If inventory doesn't have powder yet)
    private final ImageView licl = new ImageView(new Image("file:1.webp"));
    private final ImageView bacl2 = new ImageView(new Image("file:"));
    private final ImageView kcl = new ImageView(new Image("file:"));
    private final ImageView nacl = new ImageView(new Image("file:"));
    private final ImageView liclTool = new ImageView(new Image("file:1.webp"));
    private final ImageView bacl2Tool = new ImageView(new Image("file:"));
    private final ImageView kclTool = new ImageView(new Image("file:"));
    private final ImageView naclTool = new ImageView(new Image("file:"));
    // Bunsen burner blue flame. Found from
    // Now let me cook.
    private final ImageView bunsenBurner = new ImageView(new Image("file:"));
    private final ImageView BunsenBurnerTool = new ImageView(new Image("file:"));
    private final ImageView bunsenBurnerLab = new ImageView(new Image("file:"));
    // Wire loop. Found from
    private final ImageView wireLoop = new ImageView(new Image("file:"));
    private final ImageView wireLoopTool = new ImageView(new Image("file:"));
    private final ImageView wireLoopLab = new ImageView(new Image("file:"));
    // Test tubes: 3, be on table.
    // If try to put in used tube: It's already filled.
    private final ImageView testTubesLab = new ImageView(new Image("file:"));
    // Tiny paper hint on table says: "Look around you"
    // Other images needed.
    private final ImageView telescope = new ImageView(new Image("file:"));
    private final ImageView drawerLab = new ImageView(new Image("file:"));
    private final ImageView labSet = new ImageView(new Image("file:"));
    private final ImageView drawerTel = new ImageView(new Image("file:"));
    private final ImageView door = new ImageView(new Image("file:"));
    // Close up of pics: "Big chemistry fan huh..."
    private final ImageView wall = new ImageView(new Image("file:"));
    private final ImageView frames = new ImageView(new Image("file:"));
    private final ImageView bats = new ImageView(new Image("file:"));
    private final ImageView batsFly = new ImageView(new Image("file:"));

    // background
    // 1000/650
    // start/end

    @Override
    public void startLab() {
        // Set visibility for start.
        batsFly.setVisible(false);
        batsFly.setMouseTransparent(true);

        // Call methods.
        scareBats();

        // Set up main layout.
        StackPane mainLayout = new StackPane();
        mainLayout.getChildren().addAll(wall, drawerTel, telescope, drawerLab, frames, labSet, door);
    }

    private void scareBats() {
        Timeline batTime = new Timeline(new KeyFrame(Duration.seconds(2), event -> {batsFly.setVisible(false);}));
        bats.setOnMouseClicked(e -> {
            bats.setMouseTransparent(true);
            bats.setVisible(false);
            batsFly.setVisible(true);
            batTime.playFromStart();
        });
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
