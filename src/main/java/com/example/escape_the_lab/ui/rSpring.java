package com.example.escape_the_lab.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class rSpring extends Application {
    private Stage primaryStage;
    //before starting the lab I was thinking of having some saying like this
    //"To escape, you must harness the power of harmonic motion. Choose wisely,
    // or be doomed to an eternity of oscillation!"
    //private Label messageLabel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Scene scene = makeScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Spring Room");
        primaryStage.show();
    }

    private Scene makeScene() {
        //the bed on the left
        ImageView bed = new ImageView(new Image(getClass().getResource("/images/bed.png").toExternalForm()));
        bed.setFitWidth(200);
        bed.setFitHeight(600);
        bed.setPreserveRatio(true);
        bed.setLayoutX(70);
        bed.setLayoutY(270);

        bed.setOnMouseClicked(event -> {
            // When the bed is clicked, switch to a scene in it (springs)
            System.out.println("Bed clicked! Changing to Springs Scene...");
            showSpringsScene();
        });

        //the lab table in the middle
        ImageView labTable = new ImageView(new Image(getClass().getResource("/images/table.png").toExternalForm()));
        labTable.setFitWidth(200);
        labTable.setFitHeight(300);
        labTable.setPreserveRatio(true);
        labTable.setLayoutX(400);
        labTable.setLayoutY(250);

        //the chest drawers to the right with the
        ImageView drawers = new ImageView(new Image(getClass().getResource("/images/drawers.png").toExternalForm()));
        drawers.setFitWidth(200);
        drawers.setFitHeight(300);
        drawers.setPreserveRatio(true);
        drawers.setLayoutX(700);
        drawers.setLayoutY(250);

        drawers.setOnMouseClicked(event -> {
            // When the drawers are clicked, switch to a scene in it (objects/masses)
            showDrawersScene();
        });

        Pane root = new Pane();
        root.getChildren().addAll(bed, labTable, drawers);

        return new Scene(root, 1000, 650);
    }

    //drawer scene with objects for masses
    private void showDrawersScene() {
        ImageView drawer = new ImageView(new Image(getClass().getResource("/images/openDrawer.png").toExternalForm()));
        drawer.setFitWidth(1000);
        drawer.setFitHeight(600);
        drawer.setPreserveRatio(true);
        drawer.setLayoutX(30);
        drawer.setLayoutY(30);

        ImageView book = new ImageView(new Image(getClass().getResource("/images/books.png").toExternalForm()));
        book.setFitWidth(200);
        book.setFitHeight(600);
        book.setPreserveRatio(true);
        book.setLayoutX(100);
        book.setLayoutY(70);

        Pane newRoot = new Pane();
        newRoot.getChildren().addAll(drawer, book);

        // Set the new scene
        Scene newScene = new Scene(newRoot, 1000, 650);
        primaryStage.setScene(newScene);
    }

    //bed's spring scene with 4-5 kind of springs (with different spring constants)
    private void showSpringsScene() {
        ImageView bedSprings = new ImageView(new Image(getClass().getResource("/images/bedSprings.png").toExternalForm()));
        bedSprings.setFitWidth(800);
        bedSprings.setFitHeight(500);
        bedSprings.setPreserveRatio(true);
        bedSprings.setLayoutX(120);
        bedSprings.setLayoutY(70);

        //spring 1
        ImageView springs = new ImageView(new Image(getClass().getResource("/images/springs.png").toExternalForm()));
        springs.setFitWidth(500);
        springs.setFitHeight(150);
        springs.setPreserveRatio(true);
        springs.setLayoutX(150);
        springs.setLayoutY(390);

        //spring 2
        ImageView springs2 = new ImageView(new Image(getClass().getResource("/images/springs.png").toExternalForm()));
        springs2.setFitWidth(500);
        springs2.setFitHeight(150);
        springs2.setPreserveRatio(true);
        springs2.setLayoutX(270);
        springs2.setLayoutY(390);

        //spring 3
        ImageView springs3 = new ImageView(new Image(getClass().getResource("/images/springs.png").toExternalForm()));
        springs3.setFitWidth(500);
        springs3.setFitHeight(150);
        springs3.setPreserveRatio(true);
        springs3.setLayoutX(410);
        springs3.setLayoutY(390);

        Pane newRoot = new Pane();
        newRoot.getChildren().addAll(bedSprings, springs, springs2, springs3);

        Scene newScene2 = new Scene(newRoot, 1000, 650);
        primaryStage.setScene(newScene2);
    }
}
