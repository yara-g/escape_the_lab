package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.ui.Inventory;
import com.example.escape_the_lab.ui.Overlay;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.escape_the_lab.model.Player;
import com.example.escape_the_lab.model.Lab;
import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class GameController extends Application {

    public static boolean language = true; // True English False French.
    private static LifeManager lifeManager;
    private Player player;
    private Lab currentLab;
    private static Stage primaryStage;
    private Inventory inventory;
    private StackPane root;
    private static Overlay overlay;
    private static Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        stage.setResizable(false);
        //initialize lifeManager
        lifeManager = LifeManager.getInstance();
        // Initialize player and labs
        player = new Player();
        inventory = new Inventory();
        overlay = new Overlay(inventory, lifeManager);
        currentLab = new CircuitLab();

        overlay.updateLifeManager();

        //Start screen setup
        ImageView startGame = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/start-bg.png")).toExternalForm()));
        ImageView startGameFr = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/start-bg-fr.png")).toExternalForm()));
        ImageView enButton = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/en.png")).toExternalForm()));
        ImageView frButton = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/fr.png")).toExternalForm()));
        // Set up initial UI
        root = new StackPane(startGame);
        root.getChildren().add(enButton);
        root.getChildren().add(frButton);

        ImageView startButton = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/start.png")).toExternalForm()));
        ImageView startButtonFr = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/reveiller.png")).toExternalForm()));
        startButton.setOnMouseClicked(e -> startLab());
        startButtonFr.setOnMouseClicked(e -> startLab());
        root.getChildren().add(startButton);

        scene = new Scene(root, 1000, 650);

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
            lifeManager.showLives();
            lifeManager.updateLives(player.getLives());
        }
    }
//
//    private void checkGameOver() {
//        if (player.getLives() <= 0) {
//            // Handle game over logic
//            showGameOverScreen();
//        }
//    }

//    private void showGameOverScreen() {
//        // Shows the game over screen when lives run out
//        Button restartButton = new Button("Restart");
//
//        restartButton.setOnAction(e -> {
//            player = new Player();
//            LifeManager.getInstance().updateLives(player.getLives());
//
//            // Transition back to the first lab scene
//            currentLab = new CircuitLab();
//            currentLab.startLab();
//            transitionToLabScene(currentLab);
//        });
//        StackPane gameOverLayout = new StackPane();
//        gameOverLayout.getChildren().addAll(restartButton);
//        Scene gameOverScene = new Scene(gameOverLayout, 1000, 650);
//        primaryStage.setScene(gameOverScene);
//    }

//    public void transitionToNextLab() {
//        if (currentLab instanceof CircuitLab) {
//        } else if (currentLab instanceof FlameLab) {
//            currentLab = new SpringLab(primaryStage);
//        } else if (currentLab instanceof AcidNeutralizationLab) {
//            loadAcidNeutralization();
//            currentLab = new AcidNeutralizationLab(primaryStage);
//        }
//        currentLab.setupLab();
//        transitionToLabScene(currentLab);
//    }

//    private void transitionToLabScene(Lab lab) {
//        Scene labScene = lab.createScene();
//        primaryStage.setScene(labScene); // Use the stored primaryStage
//    }

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

    public static LifeManager getLifeManager() {
        return lifeManager;
    }

    public static Scene getScene() {
        return scene;
    }

    public static Stage getStage() {
        return primaryStage;
    }

    public static Overlay getOverlay() {
        return overlay;
    }
}