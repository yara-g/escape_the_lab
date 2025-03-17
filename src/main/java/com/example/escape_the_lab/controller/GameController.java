package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.ui.Inventory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.escape_the_lab.model.Player;
import com.example.escape_the_lab.model.Lab;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

// HOW TO ACCESS THE LANGUAGE SYSTEM:
// IN YOUR OWN LAB, CREATE A GAME CONTROLLER.
// WRITE boolean l = controller.language;
// DEPENDING ON THE BOOLEAN, USE LISTS OF DIFFERENT LANGUAGE IMAGES.

public class GameController extends Application {

    public static boolean language = true; // True English False French.
    private LifeManager lifeManager;
    private Player player;
    private Lab currentLab;
    private Stage primaryStage;
    private Inventory inventory;
    private StackPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;

        // Initialize player and labs
        player = new Player();
        inventory = new Inventory();
        currentLab = new SpringLab(stage);

        Item healthPotion = new Item("Health Potion", "/images/health_potion.png");
        inventory.addItem(healthPotion);

        ImageView healthPotionImageView = healthPotion.getImageView();
        healthPotionImageView.setFitWidth(50);
        healthPotionImageView.setFitHeight(50);

        healthPotionImageView.setOnMouseDragged(e -> {
            // Logic to handle dragging
        });

        //initialize lifeManager
        lifeManager = LifeManager.getInstance();

        //Start screen setup
        ImageView startGame = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));
        ImageView startGameFr = new ImageView(new Image(getClass().getResource("/images/start-bg-fr.png").toExternalForm()));
        ImageView enButton = new ImageView(new Image(getClass().getResource("/images/en.png").toExternalForm()));
        ImageView frButton = new ImageView(new Image(getClass().getResource("/images/fr.png").toExternalForm()));
        // Set up initial UI
        root = new StackPane(startGame);
        root.getChildren().add(enButton);
        root.getChildren().add(frButton);

        ImageView startButton = new ImageView(new Image(getClass().getResource("/images/start.png").toExternalForm()));
        ImageView startButtonFr = new ImageView(new Image(getClass().getResource("/images/reveiller.png").toExternalForm()));
        startButton.setOnMouseClicked(e -> startLab());
        startButtonFr.setOnMouseClicked(e -> startLab());
        root.getChildren().add(startButton);

        Scene scene = new Scene(root, 1000, 650);

        // Set up language system.
        frButton.setOnMouseClicked(e -> {
            root = new StackPane(startGameFr);
            root.getChildren().add(enButton);
            root.getChildren().add(frButton);
            root.getChildren().add(startButtonFr);
            scene.setRoot(root);
            GameController.language = false;
        });
        enButton.setOnMouseClicked(e -> {
            root = new StackPane(startGame);
            root.getChildren().add(enButton);
            root.getChildren().add(frButton);
            root.getChildren().add(startButton);
            scene.setRoot(root);
            GameController.language = true;
        });

        stage.setScene(scene);
        stage.show();
    }

    private void startLab() {
        if (currentLab != null) {
            currentLab.startLab();
            //transitionToLabScene(currentLab);
            // Transition to lab scene

            lifeManager.showLives();
            // Update the life display based on current player's lives
            lifeManager.updateLives(player.getLives());

        }
    }

    private void checkGameOver() {
        if (player.getLives() <= 0) {
            // Handle game over logic
            showGameOverScreen();
        }
    }

    private void showGameOverScreen() {
        // Shows the game over screen when lives run out
        Button restartButton = new Button("Restart");
        Button exitButton = new Button("Exit");

        restartButton.setOnAction(e -> {
            player = new Player();
            LifeManager.getInstance().updateLives(player.getLives());

            // Transition back to the first lab scene
            currentLab = new SpringLab(primaryStage);
            currentLab.startLab();
            transitionToLabScene(currentLab);
        });

        exitButton.setOnAction(e -> primaryStage.close());

        StackPane gameOverLayout = new StackPane();
        gameOverLayout.getChildren().addAll(restartButton, exitButton);

        Scene gameOverScene = new Scene(gameOverLayout, 1000, 650);
        primaryStage.setScene(gameOverScene);
    }

    public void transitionToNextLab() {
        if (currentLab instanceof CircuitLab) {
//        } else if (currentLab instanceof FlameLab) {
//            currentLab = new SpringLab(primaryStage);
        } else if (currentLab instanceof AcidNeutralizationLab) {
            //loadAcidNeutralization();
            currentLab = new AcidNeutralizationLab(primaryStage);
        }
        currentLab.setupLab();
        transitionToLabScene(currentLab);
    }

    private void transitionToLabScene(Lab lab) {
        Scene labScene = lab.createScene();
        primaryStage.setScene(labScene); // Use the stored primaryStage
    }

    public void loadAcidNeutralizationFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcidNeutralizationLab_layout.fxml"));
            Parent labRoot = loader.load();

            AcidNeutralizationLab labController = loader.getController();

            labController.initializeLab(currentLab);

            Scene labScene = new Scene(labRoot, 1000, 650);
            primaryStage.setScene(labScene);
            primaryStage.setAlwaysOnTop(true);
            primaryStage.show();
            primaryStage.setAlwaysOnTop(false);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
