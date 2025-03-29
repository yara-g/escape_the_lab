package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.SpringLab;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;


public class rSpring {
    private Stage stage;
    private SpringLab springLab;
    ImageView inventoryImage = new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm()));
    Overlay overlay;

    // Constructor
    public rSpring(Stage stage, SpringLab springLab, Overlay overlay) {
        this.stage = stage;
        this.springLab = springLab;
        this.overlay = overlay;
    }

    // Main scene
    public Scene getMainScene() {
        Pane root = new Pane();
        // Add UI elements here

        return new Scene(root, 1000, 650);
    }

    // Show main scene with interactive components
    public void showMainScene() {
        ImageView bg = createImageView("/images/AAASpringLab/bg.jpg", 10, 10, 1000, 950);

        ImageView chandelier = createImageView("/images/AAASpringLab/chandelier.png", 330, 40, 200, 200);

        ImageView door = createImageView("/images/AAASpringLab/door..png", 100, 170, 200, 600);
        door.setOnMouseClicked(event -> showDoorScene()); // Shows spring selection

        ImageView chair = createImageView("/images/AAASpringLab/chair.png", 450, 300, 200, 200);
        chair.setOnMouseClicked(event -> showSpringsScene());

        ImageView drawer = createImageView("/images/AAASpringLab/drawer.png", 600, 300, 200, 400);
        drawer.setOnMouseClicked(event -> showLabScene()); // Opens drawer/mass selection

        ImageView shelves = createImageView("/images/AAASpringLab/shelves.png", 600, 160, 200, 400);
        shelves.setOnMouseClicked(event -> showShelvesScene()); // Opens drawer/mass selection

//        ImageView table = createImageView("/images/table.png", 600, 150, 200, 300);
//        shelves.setOnMouseClicked(event -> showLabScene()); // Opens drawer/mass selection

        // TEMPORARY - remove the button
        Button skipToNext = new Button("Skip to next");
        skipToNext.setOnAction(e -> {
            rCircuit lab = new rCircuit(stage, overlay);
            try {
                lab.start();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Pane root = new Pane();
        root.getChildren().addAll(bg, chandelier, door, chair, drawer, shelves, inventoryImage, overlay.getOverlayPane(), skipToNext);
        stage.setScene(new Scene(root, 1000, 650));

        // Start an animation timer to continuously check the solution
        startSolutionCheck();
    }

    private void showDoorScene() {
        ImageView door = createImageView("/images/AAASpringLab/door..png", 120, 70, 800, 500);
    }

    // Show springs scene with different types of springs
    private void showSpringsScene() {
        ImageView bedSprings = createImageView("/images/AAASpringLab/chair.png", 120, 70, 800, 600);

        // Different springs
        ImageView spring1 = createSpringImage("/images/spring1.png", 30, 150, 390);
        ImageView spring2 = createSpringImage("/images/spring2.png", 50, 270, 390);  // Correct choice
        ImageView spring3 = createSpringImage("/images/spring3.png", 70, 410, 390);

        Button goBack = new Button("Go back");
        goBack.setOnAction(e -> {
            showMainScene();
        });

        Pane root = new Pane();
        root.getChildren().addAll(bedSprings, spring1, spring2, spring3, inventoryImage, goBack, overlay.getOverlayPane());
        stage.setScene(new Scene(root, 1000, 650));

        startSolutionCheck();
    }

    // Show drawers scene with different masses
    private void showShelvesScene() {
        ImageView shelves = createImageView("/images/AAASpringLab/shelves.png", 30, 5, 1150, 1150);

        ImageView mass1 = createMassImage("/images/AAASpringLab/clockStatue.png", 1.0, 390, 150); //book
        ImageView mass2 = createMassImage("/images/AAASpringLab/books.png", 2.0, 500, 340);  // Correct choice
        ImageView mass3 = createMassImage("/images/AAASpringLab/globe.png", 3.0, 240, 420);

        Button goBack = new Button("Go back");
        goBack.setLayoutX(20);
        goBack.setLayoutY(600);
        goBack.setOnAction(e -> {
            showMainScene();
        });

        Pane root = new Pane();
        root.getChildren().addAll(shelves, mass1, mass2, mass3, inventoryImage, overlay.getOverlayPane(), goBack);
        stage.setScene(new Scene(root, 1000, 650));

        startSolutionCheck();
    }

    private void showLabScene() {
        ImageView springStand = createImageView("/images/AAASpringLab/spring-Stand.png", 180, 95, 500, 500);
        ImageView table = createImageView("/images/AAASpringLab/table.png", 5, 400, 1000, 1000);
        ImageView selectedSpring = createImageView("/images/spring2.png", 440, 200, 100, 300);

        // Lab table setup for drag-and-drop
//        ImageView labTable = createImageView("/images/table.png", 400, 250, 200, 300);

        Slider compressionSlider = new Slider(0, 100, 0);
        compressionSlider.setLayoutX(90);
        compressionSlider.setLayoutY(130);
        compressionSlider.setPrefHeight(300);
        compressionSlider.setRotate(-90);

        Label sliderLabel = new Label("Spring Compression");
        sliderLabel.setLayoutX(100);
        sliderLabel.setLayoutY(150);

        compressionSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double scale = 1 - (newVal.doubleValue() / 200);
            //selectedSpring.setPivotY(selectedSpring.getLayoutY() + selectedSpring.getFitHeight());
            selectedSpring.setScaleY(scale);
            selectedSpring.setLayoutY(200 + (newVal.doubleValue() / 2));  // Move bottom of spring UP

        });

        Button goBack = new Button("Go back");
        goBack.setLayoutX(20);
        goBack.setLayoutY(600);
        goBack.setOnAction(e -> showMainScene());

        Button playButton = new Button("Play");
        playButton.setLayoutX(400);
        playButton.setLayoutY(600);
        playButton.setOnAction(event -> startSpringOscillation(selectedSpring, compressionSlider.getValue()));

//        Button attemptEscapeButton = new Button("Attempt Escape");
//        attemptEscapeButton.setLayoutX(850);
//        attemptEscapeButton.setLayoutY(500);
//        attemptEscapeButton.setOnAction(event -> springLab.attemptEscape());

        Pane root = new Pane();
        root.getChildren().addAll(table, springStand, selectedSpring, sliderLabel, compressionSlider, inventoryImage, overlay.getOverlayPane(), playButton, goBack);
        stage.setScene(new Scene(root, 1000, 650));
    }

    private void startSpringOscillation(ImageView spring, double compressionValue) {
        double maxCompression = compressionValue;  // Max compression value (same as slider)
        double originalY = spring.getLayoutY();
        double period = 2000;  // One full oscillation (milliseconds)
        spring.setLayoutY(200);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(spring.scaleYProperty(), 1, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.seconds(1), new KeyValue(spring.scaleYProperty(), 1 - maxCompression / 200, Interpolator.EASE_BOTH)),
                new KeyFrame(Duration.seconds(2), new KeyValue(spring.scaleYProperty(), 1, Interpolator.EASE_BOTH))

//            new KeyFrame(Duration.ZERO, event -> {
//                double newVal = 0;  // Start at uncompressed state
//                double scale = 1 - (newVal / 200);
//                spring.setScaleY(scale);
//                spring.setLayoutY(originalY + (newVal / 2));
//            }, new KeyValue(spring.scaleYProperty(), 1, Interpolator.EASE_BOTH)),
//
//            new KeyFrame(Duration.millis(period / 4), event -> {
//                double newVal = maxCompression / 2;  // Fully compressed state
//                double scale = 1 - (newVal / 200);
//                spring.setScaleY(scale);
//                spring.setLayoutY(originalY + (newVal / 2));
//            }, new KeyValue(spring.scaleYProperty(), 0.5, Interpolator.EASE_BOTH)),
//
//            new KeyFrame(Duration.millis(period / 2), event -> {
//                double newVal = maxCompression;  // Fully compressed state
//                double scale = 1 - (newVal / 200);
//                spring.setScaleY(scale);
//                spring.setLayoutY(originalY + (newVal / 2));
//            }, new KeyValue(spring.scaleYProperty(), 0.25, Interpolator.EASE_BOTH)),
//
//            new KeyFrame(Duration.millis(3 * period / 4), event -> {
//                double newVal = maxCompression / 2;  // Partially decompressed
//                double scale = 1 - (newVal / 200);
//                spring.setScaleY(scale);
//                spring.setLayoutY(originalY + (newVal / 2));
//            }, new KeyValue(spring.scaleYProperty(), 0.5, Interpolator.EASE_BOTH)),
//
//            new KeyFrame(Duration.millis(period), event -> {
//                double newVal = 0;  // Back to uncompressed state
//                double scale = 1 - (newVal / 200);
//                spring.setScaleY(scale);
//                spring.setLayoutY(originalY + (newVal / 2));
//            }, new KeyValue(spring.scaleYProperty(), 1, Interpolator.EASE_BOTH))
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
    }

    private ImageView createImageView(String path, double x, double y, double width, double height) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(path).toExternalForm()));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        return imageView;
    }

    //create a spring image and handle click events
    private ImageView createSpringImage(String imagePath, double k, double x, double y) {
        ImageView spring = createImageView(imagePath, x, y, 50, 100);

        spring.setOnMouseClicked(event -> {
            springLab.setSelectedSpringConstant(k);  // Call controller to set value
            System.out.println("Spring selected: " + k + " N/m");
        });
        return spring;
    }

    //create a mass image and handle click events
    private ImageView createMassImage(String imagePath, double mass, double x, double y) {
        ImageView massImage = createImageView(imagePath, x, y, 150, 150);

        massImage.setOnMouseClicked(event -> {
            springLab.setSelectedMass(mass);  // Call controller to set value
            System.out.println("Mass selected: " + mass + " kg");
        });
        return massImage;
    }

    // Start the rCircuit lab
    private void startRCircuitLab() {
        rCircuit lab = new rCircuit(stage, overlay);
        try {
            lab.start();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // Start an AnimationTimer to periodically check if the solution is correct
    private void startSolutionCheck() {
        AnimationTimer solutionCheckTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (springLab.checkSolution()) {
                    startRCircuitLab();
                    stop();  // Stop the timer after transitioning to the next lab
                }
            }
        };
        solutionCheckTimer.start(); // Start the timer
    }
}
