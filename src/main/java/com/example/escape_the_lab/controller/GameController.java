package com.example.escape_the_lab.controller;

import javafx.application.Application;
import javafx.geometry.Pos;
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

public class GameController extends Application {
    private Player player;
    private Lab currentLab;
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        // Initialize player and labs
        player = new Player();
        currentLab = new FlameLab();
        ImageView startGame = new ImageView(new Image(getClass().getResource("/images/start-bg.png").toExternalForm()));

        // Set up initial UI
        StackPane root = new StackPane(startGame);
        root.setAlignment(Pos.CENTER);
        Button startButton = new Button("Start Lab");
        startButton.setOnAction(e -> startLab());

        root.getChildren().add(startButton);
        Scene scene = new Scene(root, 1000, 650);
        stage.setScene(scene);
        stage.show();
    }

    private void startLab() {
        if (currentLab != null) {
            currentLab.startLab();
            // Transition to lab scene
        }
    }

    private void checkGameOver() {
        if (player.getLives() <= 0) {
            // Handle game over logic
        }
    }
   

    private void transitionToNextLab() {
        if (currentLab instanceof CircuitLab) {
            currentLab = new FlameLab();
        } else if (currentLab instanceof FlameLab) {
            currentLab = new SpringLab();
        } else if (currentLab instanceof AcidNeutralizationLab) {
            loadAcidNeutralization();
            currentLab = new AcidNeutralizationLab();
        }
        currentLab.setupLab();
        transitionToLabScene(currentLab);
    }

    private void transitionToLabScene(Lab lab) {
        Scene labScene = lab.createScene();
        primaryStage.setScene(labScene); // Use the stored primaryStage
    }
    
    public void loadAcidNeutralization() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcidNeutralizationLab_layout.fxml"));
        Parent labRoot = loader.load(); 
        
        AcidNeutralizationLab labController = loader.getController();

        labController.initializeLab(currentLab);
        
        Scene labScene = new Scene(labRoot, 1000, 650);
        primaryStage.setScene(labScene);
        primaryStage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}
  
}

