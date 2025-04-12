package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.ui.Inventory;
import com.example.escape_the_lab.ui.Overlay;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import com.example.escape_the_lab.model.Player;
import com.example.escape_the_lab.model.Lab;

import java.util.Collection;
import java.util.Objects;

public class GameController extends Application {

    private static LifeManager lifeManager;
    private Lab currentLab;
    private static Stage primaryStage;
    private Inventory inventory;
    private StackPane root;
    private static Overlay overlay;
    private static Scene scene;
    private static final Player player = Player.getLastPlayer(); // reload the same player as last time
    MenuItem soundItem;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Settings");
        if (player.isSoundOn()) {
            soundItem = new MenuItem("Sound: On");
        } else {
            soundItem = new MenuItem("Sound: Off");
        }
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(soundItem);
        fileMenu.getItems().add(exitItem);

        soundItem.setOnAction(actionEvent -> {
            player.setSound(!player.isSoundOn());
            if (player.isSoundOn()) {
                soundItem.setText("Sound: On");
            } else {
                soundItem.setText("Sound: Off");
            }
        });

        exitItem.setOnAction(actionEvent -> {
            stage.close();
        });
        menuBar.getMenus().addAll(fileMenu);
        menuBar.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/styles.css")).toExternalForm());

        primaryStage = stage;
        stage.setResizable(false);
        //initialize lifeManager
        lifeManager = LifeManager.getInstance();
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
        root = new StackPane();
        ImageView startButton = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/start.png")).toExternalForm()));
        ImageView startButtonFr = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/reveiller.png")).toExternalForm()));
        startButton.setOnMouseClicked(e -> startLab());
        startButtonFr.setOnMouseClicked(e -> startLab());

        if (Objects.equals(player.getLanguage(), "english")) {
            root.getChildren().addAll(startGame, startButton);
        } else if (Objects.equals(player.getLanguage(), "french")) {
            root.getChildren().addAll(startGameFr, startButtonFr);
        }
        root.getChildren().add(enButton);
        root.getChildren().add(frButton);

        VBox root1 = new VBox(menuBar, root);

        scene = new Scene(root1, 1000, 650);

        // Set up language system.
        frButton.setOnMouseClicked(e -> {
            root = new StackPane(startGameFr);
            root.getChildren().add(enButton);
            root.getChildren().add(frButton);
            root.getChildren().add(startButtonFr);
            scene.setRoot(root);

            player.setLanguage("french");
        });
        enButton.setOnMouseClicked(e -> {
            root = new StackPane(startGame);
            root.getChildren().add(enButton);
            root.getChildren().add(frButton);
            root.getChildren().add(startButton);
            scene.setRoot(root);

            player.setLanguage("english");
        });

        stage.setScene(scene);
        stage.show();
    }

    private void startLab() {
        if (currentLab != null) {
            currentLab.startLab();
            lifeManager.showLives();
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

    public static Player getPlayer() {
        return player;
    }
}