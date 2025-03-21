package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.SpringLab;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        // Bed on the left to access springs
        ImageView bed = new ImageView(new Image(getClass().getResource("/images/bed.png").toExternalForm()));
        bed.setFitWidth(200);
        bed.setFitHeight(600);
        bed.setPreserveRatio(true);
        bed.setLayoutX(70);
        bed.setLayoutY(270);
        bed.setOnMouseClicked(event -> showSpringsScene()); // Shows spring selection

        // Lab table in the middle
        ImageView labTable = new ImageView(new Image(getClass().getResource("/images/table.png").toExternalForm()));
        labTable.setFitWidth(200);
        labTable.setFitHeight(300);
        labTable.setPreserveRatio(true);
        labTable.setLayoutX(400);
        labTable.setLayoutY(250);

        // Drawers to the right to access masses
        ImageView drawers = new ImageView(new Image(getClass().getResource("/images/drawers.png").toExternalForm()));
        drawers.setFitWidth(200);
        drawers.setFitHeight(300);
        drawers.setPreserveRatio(true);
        drawers.setLayoutX(700);
        drawers.setLayoutY(250);
        drawers.setOnMouseClicked(event -> showDrawersScene()); // Opens drawer/mass selection

        Pane root = new Pane();
        root.getChildren().addAll(bed, labTable, drawers, inventoryImage, overlay.getInventoryPane());

        stage.setScene(new Scene(root, 1000, 650));

        // TEMPORARY - remove the button
        Button skipToNext = new Button("Skip to next");
        skipToNext.setOnAction(e -> {
            rCircuit lab = new rCircuit(stage, overlay);
            try {
                lab.startLab();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        root.getChildren().add(skipToNext);

        // Start an animation timer to continuously check the solution
        startSolutionCheck();
    }

    // Show springs scene with different types of springs
    private void showSpringsScene() {
        Pane root = new Pane();

        ImageView bedSprings = new ImageView(new Image(getClass().getResource("/images/bedSprings.png").toExternalForm()));
        bedSprings.setFitWidth(800);
        bedSprings.setFitHeight(500);
        bedSprings.setPreserveRatio(true);
        bedSprings.setLayoutX(120);
        bedSprings.setLayoutY(70);

        // Different springs
        ImageView spring1 = createSpringImage("/images/spring1.png", 30, 150, 390); //k, x, y
        ImageView spring2 = createSpringImage("/images/spring2.png", 50, 270, 390);  // Correct choice
        ImageView spring3 = createSpringImage("/images/spring3.png", 70, 410, 390);

        Button goBack = new Button("Go back");
        goBack.setOnAction(e -> {
            showMainScene();
        });


        root.getChildren().addAll(bedSprings, spring1, spring2, spring3, inventoryImage, goBack, overlay.getInventoryPane());
        stage.setScene(new Scene(root, 1000, 650));

        // Start an animation timer to continuously check the solution
        startSolutionCheck();
    }

    // Method to create a spring image and handle click events
    private ImageView createSpringImage(String imagePath, double k, double x, double y) {
        ImageView spring = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        spring.setFitWidth(500);
        spring.setFitHeight(150);
        spring.setPreserveRatio(true);
        spring.setLayoutX(x);
        spring.setLayoutY(y);
        spring.setOnMouseClicked(event -> {
            springLab.setSelectedSpringConstant(k);  // Call controller to set value
            System.out.println("Spring selected: " + k + " N/m");
        });
        return spring;
    }

    // Show drawers scene with different masses
    private void showDrawersScene() {
        Pane root = new Pane();

        ImageView drawer = new ImageView(new Image(getClass().getResource("/images/openDrawer.png").toExternalForm()));
        drawer.setFitWidth(1000);
        drawer.setFitHeight(600);
        drawer.setPreserveRatio(true);
        drawer.setLayoutX(30);
        drawer.setLayoutY(30);

        // Different masses
        ImageView mass1 = createMassImage("/images/mass1.png", 1.0, 100, 200); //book
        ImageView mass2 = createMassImage("/images/mass2.png", 2.0, 250, 200);  // Correct choice
        ImageView mass3 = createMassImage("/images/mass3.png", 3.0, 400, 200);

        root.getChildren().addAll(drawer, mass1, mass2, mass3, inventoryImage, overlay.getInventoryPane());
        stage.setScene(new Scene(root, 1000, 650));

        Button goBack = new Button("Go back");
        root.getChildren().add(goBack);

        goBack.setOnAction(e -> {
            showMainScene();
        });

        // Start an animation timer to continuously check the solution
        startSolutionCheck();
    }

    // Method to create a mass image and handle click events
    private ImageView createMassImage(String imagePath, double mass, double x, double y) {
        ImageView massImage = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        massImage.setFitWidth(100);
        massImage.setFitHeight(100);
        massImage.setPreserveRatio(true);
        massImage.setLayoutX(x);
        massImage.setLayoutY(y);
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
            lab.startLab();
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
