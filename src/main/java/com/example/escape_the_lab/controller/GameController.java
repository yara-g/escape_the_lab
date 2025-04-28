package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.ui.Inventory;
import com.example.escape_the_lab.ui.Overlay;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import com.example.escape_the_lab.model.Player;
import com.example.escape_the_lab.model.Lab;
import javafx.util.Duration;

import java.util.Objects;

public class GameController extends Application {

    private static LifeManager lifeManager;
    private Lab currentLab;
    private static Stage primaryStage;
    private Inventory inventory;
    private StackPane root;
    private static Overlay overlay;
    private static Scene scene;
    public static final Player player = Player.getLastPlayer(); // reload the same player as last time
    MenuItem soundItem;
    VBox root1;
    static String clickSoundPath = Objects.requireNonNull(GameController.class.getResource("/sounds/click.mp3")).toExternalForm();
    static Media clickMedia = new Media(clickSoundPath);
    static MediaPlayer clickSoundPlayer = new MediaPlayer(clickMedia);

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
                playClick();
                soundItem.setText("Sound: On");
            } else {
                soundItem.setText("Sound: Off");
            }
        });

        exitItem.setOnAction(actionEvent -> {
            playClick();
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
        startButton.setOnMouseClicked(e -> {
            playClick();
            startLab();
        });
        startButtonFr.setOnMouseClicked(e -> {
            playClick();
            startLab();
        });

        if (Objects.equals(player.getLanguage(), "english")) {
            root.getChildren().addAll(startGame, startButton);
        } else if (Objects.equals(player.getLanguage(), "french")) {
            root.getChildren().addAll(startGameFr, startButtonFr);
        }
        root.getChildren().add(enButton);
        root.getChildren().add(frButton);

        root1 = new VBox(menuBar, root);

//        scene = new Scene(root1, 1000, 650);
        scene = getLoginScreen();
        // Set up language system.
        frButton.setOnMouseClicked(e -> {
            root.getChildren().clear();
            root.getChildren().addAll(startGameFr,startButtonFr, enButton, frButton);
            playClick();
            player.setLanguage("french");
        });
        enButton.setOnMouseClicked(e -> {
            root.getChildren().clear();
            root.getChildren().addAll(startGame,startButton, enButton, frButton);
            playClick();
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

    private Scene getLoginScreen() {
        GridPane grid = new GridPane();
        VBox vBox = new VBox(grid);
        HBox hBox = new HBox(vBox);

        Label placeholder = new Label();
        Label username = new Label("Username:");
        Button enter = new Button("ENTER");
        enter.setBackground(new Background(
                new BackgroundFill(
                        Color.RED,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));
        enter.setStyle("-fx-text-fill: black");
        TextField usernameInput = new TextField();
        username.setTextFill(Color.RED);
        usernameInput.setBackground(new Background(
                new BackgroundFill(
                        Color.RED,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));
        usernameInput.setStyle("-fx-text-fill: black");
        Label password = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setBackground(new Background(
                new BackgroundFill(
                        Color.RED,
                        CornerRadii.EMPTY,
                        Insets.EMPTY
                )
        ));
        passwordField.setStyle("-fx-text-fill: black");
        Button skip = new Button("skip");
        password.setTextFill(Color.RED);
        vBox.getChildren().addAll(enter, placeholder, skip);

        skip.setOnAction(e -> {
            primaryStage.setScene(new Scene(root1, 1000, 650));
        });

        enter.setOnAction(e -> {
            String user = usernameInput.getText();
            String pass = passwordField.getText();
            playClick();

            if (user.equals(player.getUsername()) && pass.equals(player.getPassword())) {
                primaryStage.setScene(new Scene(root1, 1000, 650));
            } else {
                Label label = new Label("Wrong password or username.");
                label.setStyle("-fx-text-fill: red");
                vBox.getChildren().set(2, label);
            }
        });

        GridPane.setConstraints(username, 0, 0);
        GridPane.setConstraints(usernameInput, 1, 0);
        GridPane.setConstraints(password, 0, 1);
        GridPane.setConstraints(passwordField, 1, 1);
        grid.getChildren().addAll(username, password, usernameInput, passwordField);
        vBox.setSpacing(20);
        grid.setHgap(10);
        grid.setVgap(5);

        vBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-background-color: black");
        hBox.setAlignment(Pos.CENTER);
        return new Scene(hBox, 1000, 650);
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

    public static void playClick() {
        if (player.isSoundOn()) {
            clickSoundPlayer.seek(new Duration(0));
            clickSoundPlayer.play();
        }
    }
}