package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.SpringLab;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class rSpring {
    private Stage stage;
    private SpringLab springLab;
    //before starting the lab I was thinking of having some saying like this
    //"To escape, you must harness the power of harmonic motion. Choose wisely,
    // or be doomed to an eternity of oscillation!"
    //private Label messageLabel;

    public rSpring(Stage stage, SpringLab springLab) {
        this.stage = stage;
        this.springLab = springLab;
    }

    public Scene getMainScene() {
        Pane root = new Pane();
        // Add UI elements here
        return new Scene(root, 1000, 650);
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        Scene scene = makeScene();
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Spring Room");
//        primaryStage.show();
//    }

    public void showMainScene() {
        //the bed on the left to access springs
        ImageView bed = new ImageView(new Image(getClass().getResource("/images/bed.png").toExternalForm()));
        bed.setFitWidth(200);
        bed.setFitHeight(600);
        bed.setPreserveRatio(true);
        bed.setLayoutX(70);
        bed.setLayoutY(270);
        bed.setOnMouseClicked(event -> showSpringsScene()); // shows spring selection

        //the lab table in the middle
        ImageView labTable = new ImageView(new Image(getClass().getResource("/images/table.png").toExternalForm()));
        labTable.setFitWidth(200);
        labTable.setFitHeight(300);
        labTable.setPreserveRatio(true);
        labTable.setLayoutX(400);
        labTable.setLayoutY(250);

        //the drawers to the right to access masses
        ImageView drawers = new ImageView(new Image(getClass().getResource("/images/drawers.png").toExternalForm()));
        drawers.setFitWidth(200);
        drawers.setFitHeight(300);
        drawers.setPreserveRatio(true);
        drawers.setLayoutX(700);
        drawers.setLayoutY(250);
        drawers.setOnMouseClicked(event -> showDrawersScene()); // opens drawer/ mass selection

        Pane root = new Pane();
        root.getChildren().addAll(bed, labTable, drawers);
        stage.setScene(new Scene(root, 1000, 650));
    }

    //bed's spring scene with 4-5 kind of springs (with different spring constants)
    private void showSpringsScene() {
        Pane root = new Pane();

        ImageView bedSprings = new ImageView(new Image(getClass().getResource("/images/bedSprings.png").toExternalForm()));
        bedSprings.setFitWidth(800);
        bedSprings.setFitHeight(500);
        bedSprings.setPreserveRatio(true);
        bedSprings.setLayoutX(120);
        bedSprings.setLayoutY(70);

        //different springs
        ImageView spring1 = createSpringImage("/images/spring1.png", 30, 150, 390); //k, x, y
        ImageView spring2 = createSpringImage("/images/spring2.png", 50, 270, 390);  // Correct choice
        ImageView spring3 = createSpringImage("/images/spring3.png", 70, 410, 390);

        root.getChildren().addAll(bedSprings, spring1, spring2, spring3);
        stage.setScene(new Scene(root, 1000, 650));
    }

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

    //drawer scene with objects for masses
    private void showDrawersScene() {
        Pane root = new Pane();

        ImageView drawer = new ImageView(new Image(getClass().getResource("/images/openDrawer.png").toExternalForm()));
        drawer.setFitWidth(1000);
        drawer.setFitHeight(600);
        drawer.setPreserveRatio(true);
        drawer.setLayoutX(30);
        drawer.setLayoutY(30);

        //different masses
        ImageView mass1 = createMassImage("/images/mass1.png", 1.0, 100, 200); //book
        ImageView mass2 = createMassImage("/images/mass2.png", 2.0, 250, 200);  // Correct choice
        ImageView mass3 = createMassImage("/images/mass3.png", 3.0, 400, 200);
//        book.setFitWidth(200);
//        book.setFitHeight(600);
//        book.setPreserveRatio(true);
//        book.setLayoutX(100);
//        book.setLayoutY(70);

        root.getChildren().addAll(drawer, mass1, mass2, mass3);
        stage.setScene(new Scene(root, 1000, 650));
    }

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
}
