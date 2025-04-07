package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.*;
import com.example.escape_the_lab.model.Item;
import javafx.animation.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.Timeline;

import java.util.Objects;


public class rSpring {
    private Stage stage;
    Pane root;
    private SpringLab springLab;
    ImageView inventoryImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/inventory.png")).toExternalForm()));
    Overlay overlay;
    private Timeline timeline;
    private boolean isDoorUnlocked = false;
    private boolean springPlaced = false;
    private boolean massPlaced = false;

    private Item placedSpringItem = null;
    private Item placedMassItem = null;

    private Item chosenItem;
    private ImageView selectedSpring = new ImageView();
    private ImageView selectedMass = new ImageView();

    //private Item placeHolder;
    Item placeHolder = new Item("Place Holder", "/images/placeHolder.jpeg");

    ImageView shelves = createImageView("/images/AAASpringLab/shelves.png", 30, 5, 1150, 1150);
    ImageView mass1 = createMassImage("/images/AAASpringLab/clockStatue.png", 390, 150); //book
    ImageView mass2 = createMassImage("/images/AAASpringLab/books.png", 500, 340);  // Correct choice
    ImageView mass3 = createMassImage("/images/AAASpringLab/globe.png", 240, 420);

    ImageView chair = createImageView("/images/AAASpringLab/chair.png", 120, 70, 800, 600);
    ImageView spring1 = createSpringImage("/images/spring1.png", 150, 390);
    ImageView spring2 = createSpringImage("/images/spring2.png", 270, 390);  // Correct choice
    ImageView spring3 = createSpringImage("/images/spring3.png", 410, 390);

    Item spring1Item = new Item("100N/m", "/images/spring1.png");
    Item spring2Item = new Item("200N/m", "/images/spring2.png");
    Item spring3Item = new Item("300N/m", "/images/spring3.png");

    Item mass1Item = new Item("4kg", "/images/AAASpringLab/clockStatue.png");
    Item mass2Item = new Item("3kg", "/images/AAASpringLab/books.png");
    Item mass3Item = new Item("5kg", "/images/AAASpringLab/globe.png");

    //lab scene
    ImageView springStand = createImageView("/images/AAASpringLab/spring-Stand.png", 180, 95, 500, 500);
    ImageView table = createImageView("/images/AAASpringLab/table.png", 5, 400, 1000, 1000);

    // Constructor
    public rSpring(Stage stage, SpringLab springLab) {
        this.stage = stage;
        this.springLab = springLab;
        this.overlay = GameController.getOverlay();
        selectedSpring = new ImageView();
        selectedMass = new ImageView();
    }

    public void showIntroScene() {
        ImageView bg = createImageView("/images/AAASpringLab/1stBg.png", 0, 0, 1000, 650);
        bg.setPreserveRatio(false);

        // Mysterious Message
        Label introLabel = new Label("Something must fall.\nSomething must stretch.\n\n"
                + "Choose wisely.\nOr stay... forever oscillating.");
        introLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-alignment: center;");
        introLabel.setLayoutX(380);
        introLabel.setLayoutY(180);

        // Button to start
        Button beginButton = new Button("Enter");
        beginButton.setLayoutX(470);
        beginButton.setLayoutY(400);
        beginButton.setOnAction(e -> showMainScene());

        Pane introRoot = new Pane();
        introRoot.getChildren().addAll(bg, introLabel, beginButton);
        Scene introScene = new Scene(introRoot, 1000, 650);
        stage.setScene(introScene);
    }

    public void showMainScene() {
        ImageView bg = createImageView("/images/AAASpringLab/bg.jpg", 10, 10, 1000, 950);

        ImageView chandelier = createImageView("/images/AAASpringLab/chandelier.png", 330, 40, 200, 200);

        ImageView door = createImageView("/images/AAASpringLab/door..png", 100, 170, 200, 600);
        Label doorLockedMessage = new Label("The door is locked. Try oscillating around!");
        doorLockedMessage.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");
        doorLockedMessage.setLayoutX(250);
        doorLockedMessage.setLayoutY(500);
        doorLockedMessage.setVisible(false);
        door.setOnMouseClicked(event -> {
            if (isDoorUnlocked) {
                showDoorOpenScene();
            } else {
                doorLockedMessage.setVisible(true);

                FadeTransition fade = new FadeTransition(Duration.seconds(3), doorLockedMessage);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setOnFinished(e -> root.getChildren().remove(doorLockedMessage));
                fade.play();
            }
        });

        ImageView chair = createImageView("/images/AAASpringLab/chair.png", 450, 300, 200, 200);
        chair.setOnMouseClicked(event -> showSpringsScene());

//        ImageView springStand = createImageView("/images/AAASpringLab/spring-Stand.png", 250, 300, 200, 200);
//        chair.setOnMouseClicked(event -> showLabScene());

        ImageView drawer = createImageView("/images/AAASpringLab/drawer.png", 600, 300, 200, 400);
        drawer.setOnMouseClicked(event -> showLabScene());

        ImageView shelves = createImageView("/images/AAASpringLab/shelves.png", 600, 160, 200, 400);
        shelves.setOnMouseClicked(event -> showShelvesScene());

        // TEMPORARY - remove the button
        Button skipToNext = new Button("Escape");
        skipToNext.setOnAction(e -> {
            FlameLab f = new FlameLab();
            f.startLab(stage);
            overlay.getInventory().resetInventory();
            overlay.updateInventory();
        });

        Pane root = new Pane();
        root.getChildren().addAll(bg, chandelier, door, chair, drawer, shelves, inventoryImage, overlay.getOverlayPane(), skipToNext, doorLockedMessage);

        stage.setScene(new Scene(root, 1000, 650));

        // Start an animation timer to continuously check the solution
        startSolutionCheck();
    }

    private void showDoorOpenScene() {
        ImageView doorOpen = createImageView("/images/AAASpringLab/openDoor.jpg", 0, 0, 1000, 650);
        doorOpen.setPreserveRatio(false);

        Button goBack = new Button("Go back");
        goBack.setLayoutX(20);
        goBack.setLayoutY(600);
        goBack.setOnAction(e -> showMainScene());

        Button skipToNext = new Button("Skip to next");
        skipToNext.setLayoutX(480);
        skipToNext.setLayoutY(325);
        skipToNext.setOnAction(e -> {
            FlameLab f = new FlameLab();
            f.startLab(stage);
            overlay.getInventory().resetInventory();
            overlay.updateInventory();
        });

        Pane root = new Pane();
        root.getChildren().addAll(doorOpen, goBack, skipToNext);
        stage.setScene(new Scene(root, 1000, 650));
    }

    // Show springs scene with different types of springs
    private void showSpringsScene() {

        spring1Item.getImageView().setOnMouseClicked(e -> {
            chosenItem = spring1Item;
        });

        spring2Item.getImageView().setOnMouseClicked(e -> {
            chosenItem = spring2Item;
        });

        spring3Item.getImageView().setOnMouseClicked(e -> {
            chosenItem = spring3Item;
        });

        Button goBack = new Button("Go back");
        goBack.setOnAction(e -> {
            showMainScene();
        });

        spring1.setOnMouseClicked(e -> {
            spring1.setVisible(false);
            overlay.getInventory().addItem(spring1Item);
            overlay.updateInventory();
        });

        spring2.setOnMouseClicked(e -> {
            spring2.setVisible(false);
            overlay.getInventory().addItem(spring2Item);
            overlay.updateInventory();
        });

        spring3.setOnMouseClicked(e -> {
            spring3.setVisible(false);
            overlay.getInventory().addItem(spring3Item);
            overlay.updateInventory();
        });

        Pane root = new Pane();
        root.getChildren().addAll(chair, spring1, spring2, spring3, inventoryImage, goBack, overlay.getOverlayPane());
        stage.setScene(new Scene(root, 1000, 650));

        startSolutionCheck();
    }

    // Show drawers scene with different masses
    private void showShelvesScene() {

        mass1Item.getImageView().setOnMouseClicked(e -> {
            chosenItem = mass1Item;
        });

        mass2Item.getImageView().setOnMouseClicked(e -> {
            chosenItem = mass2Item;
        });

        mass3Item.getImageView().setOnMouseClicked(e -> {
            chosenItem = mass3Item;
        });

        mass1.setOnMouseClicked(e -> {
            mass1.setVisible(false);
            overlay.getInventory().addItem(mass1Item);
            overlay.updateInventory();
        });

        mass2.setOnMouseClicked(e -> {
            mass2.setVisible(false);
            overlay.getInventory().addItem(mass2Item);
            overlay.updateInventory();
        });

        mass3.setOnMouseClicked(e -> {
            mass3.setVisible(false);
            overlay.getInventory().addItem(mass3Item);
            overlay.updateInventory();
        });

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
        Button goBack = new Button("Go back");
        goBack.setLayoutX(20);
        goBack.setLayoutY(600);
        goBack.setOnAction(e -> showMainScene());

        springStand.setOnMouseClicked(e -> {
            if (chosenItem != null && !chosenItem.getName().equals("placeholder")) {
                //useItem(e);
                if (chosenItem.getName().contains("N")){
                    selectedSpring.setImage(chosenItem.getImage());
                    selectedSpring.setFitWidth(100);
                    selectedSpring.setFitHeight(100);
                    selectedSpring.setLayoutX(440);
                    selectedSpring.setLayoutY(200);
                    placedSpringItem = chosenItem;
                    springPlaced = true;

                    if (massPlaced) {
                        selectedMass.setLayoutY(250);
                    }

                } else if (chosenItem.getName().contains("g")) {
                    selectedMass.setImage(chosenItem.getImage());
                    selectedMass.setFitWidth(100);
                    selectedMass.setFitHeight(100);
                    selectedMass.setLayoutX(440);

                    if (springPlaced) {
                        // If spring is placed, offset mass below
                        selectedMass.setLayoutY(250);
                    } else {
                        // If spring hasn't been placed yet, put mass at the spring's default position
                        selectedMass.setLayoutY(200);
                    }

                    placedMassItem = chosenItem;
                    massPlaced = true;

                } else {
                    System.out.println("Invalid item type.");
                    return;
                }

                overlay.getInventory().removeItem(chosenItem);
                overlay.updateInventory();
                chosenItem = placeHolder;
            }
        });

        selectedSpring.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && placedSpringItem != null) {
                selectedSpring.setImage(null);
                overlay.getInventory().addItem(placedSpringItem);
                overlay.updateInventory();
                placedSpringItem = null;
                springPlaced = false;

                if (massPlaced) {
                    // Move the mass up to where the spring was
                    selectedMass.setLayoutY(200);
                }
            }
        });

        selectedMass.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2 && placedMassItem != null) {
                selectedMass.setImage(null);
                overlay.getInventory().addItem(placedMassItem);
                overlay.updateInventory();
                placedMassItem = null;
                massPlaced = false;
            }
        });

        Label nothingPlacedMessage = new Label("You haven’t placed anything on the stand...");
        nothingPlacedMessage.setStyle("-fx-font-size: 18px; -fx-text-fill: red;");
        nothingPlacedMessage.setLayoutX(250); // adjust as needed
        nothingPlacedMessage.setLayoutY(500);
        nothingPlacedMessage.setOpacity(1.0);
        nothingPlacedMessage.setVisible(false);

        Button playButton = new Button("Target");
        playButton.setLayoutX(400);
        playButton.setLayoutY(600);
        playButton.setOnAction(e -> {
            boolean springMissing = selectedSpring.getImage() == null;
            boolean massMissing = selectedMass.getImage() == null;

            if (springMissing && massMissing) {
                nothingPlacedMessage.setVisible(true);

                FadeTransition fade = new FadeTransition(Duration.seconds(3), nothingPlacedMessage);
                fade.setFromValue(1.0);
                fade.setToValue(0.0);
                fade.setOnFinished(event2 -> root.getChildren().remove(nothingPlacedMessage));
                fade.play();
            } else if (!springMissing && massMissing) {
                //if only mass is placed
                int currentLives = overlay.getLifeManager().getLives();

                if (currentLives == 1) {
                    KillPlayer.killPlayer("You didn’t oscillate your mind hard enough...\n" +
                            "and so you’ve been stranded as the days swing back and forth",
                            stage, stage.getScene(), overlay);
                } else{
                    KillPlayer.killPlayer("You didn’t even attempt to oscillate an object...\n" +
                            "And so you’ve been stranded... As the days oscillate endlessly.", stage, stage.getScene(), overlay);
                }

            } else {
                // TODO: create springOscillation
                startSpringOscillation(selectedSpring, selectedMass);
            }
        });

//        Button attemptEscapeButton = new Button("Attempt Escape");
//        attemptEscapeButton.setLayoutX(850);
//        attemptEscapeButton.setLayoutY(500);
//        attemptEscapeButton.setOnAction(event -> springLab.attemptEscape());

        Pane root = new Pane();
        root.getChildren().addAll(table, springStand, selectedSpring, selectedMass, inventoryImage,
                overlay.getOverlayPane(), playButton, goBack, nothingPlacedMessage);
        stage.setScene(new Scene(root, 1000, 650));
    }

    private void showFailureScene(String message) {
        //"You tried...\nBut your aim was off.\n\n The button was never pressed...\n Now the chamber sways without end."

        int currentLives = overlay.getLifeManager().getLives();

        if (currentLives == 1) {
            KillPlayer.killPlayer(
                    message,
                    stage,
                    stage.getScene(),
                    overlay
            );
            return;
        } else {
            overlay.getLifeManager().decreaseLife();
        }

        ImageView bg = createImageView("/images/AAASpringLab/1stBg.png", 0, 0, 1000, 650);
        bg.setPreserveRatio(false);

        Label label = new Label(message);
        label.setStyle("-fx-font-size: 20px; -fx-text-fill: white; -fx-alignment: center;");
        label.setLayoutX(350);
        label.setLayoutY(180);

        Button retryButton = new Button("Retry");
        retryButton.setLayoutX(470);
        retryButton.setLayoutY(400);
        retryButton.setOnAction(e -> {
//            LifeManager.getInstance().decreaseLife();
            showMainScene();
        });

        Pane root = new Pane(bg, label, retryButton);
        Scene scene = new Scene(root, 1000, 650);
        stage.setScene(scene);
    }

    private double getSpringConstantFromName(String name) {
        switch (name) {
            case "100N": return 100;
            case "200N": return 200;
            case "300N": return 300;
            default: return 0;
        }
    }

    private double getMassFromName(String name) {
        switch (name) {
            case "3g": return 3;
            case "4g": return 4;
            case "5g": return 5;
            default: return 0;
        }
    }

    private String getItemNameFromImage(Image img) {
        if (img == mass1Item.getImage()) return mass1Item.getName();
        if (img == mass2Item.getImage()) return mass2Item.getName();
        if (img == mass3Item.getImage()) return mass3Item.getName();
        if (img == spring1Item.getImage()) return spring1Item.getName();
        if (img == spring2Item.getImage()) return spring2Item.getName();
        if (img == spring3Item.getImage()) return spring3Item.getName();
        return "";
    }

    private void startSpringOscillation(ImageView selectedSpring, ImageView selectedMass) {
        if (selectedSpring.getImage() == null || selectedMass.getImage() == null) return;

        String springName = getItemNameFromImage(selectedSpring.getImage());
        String massName = getItemNameFromImage(selectedMass.getImage());

        double k = getSpringConstantFromName(springName);
        double m = getMassFromName(massName);
        double omega = Math.sqrt(k / m); // Angular frequency

        // mass oscillation
        TranslateTransition massOscillation = new TranslateTransition();
        massOscillation.setNode(selectedMass);
        massOscillation.setDuration(Duration.seconds(2));
        massOscillation.setCycleCount(TranslateTransition.INDEFINITE);
        massOscillation.setAutoReverse(true);
        massOscillation.setByY(50); // amplitude swing

        //spring oscillation
        ScaleTransition springOscillation = new ScaleTransition();
        springOscillation.setNode(selectedSpring);
        springOscillation.setDuration(Duration.seconds(2));
        springOscillation.setCycleCount(ScaleTransition.INDEFINITE);
        springOscillation.setAutoReverse(true);
        springOscillation.setFromY(1.0);
        springOscillation.setToY(1.3);

        massOscillation.play();
        springOscillation.play();

        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(e -> {
            massOscillation.stop();
            springOscillation.stop();

            if (placedSpringItem != null && placedMassItem != null &&
                    placedSpringItem.getName().contains("200") &&
                    placedMassItem.getName().contains("4")) {
                isDoorUnlocked = true;
                //showDoorOpenScene();
//            (Math.abs(omega - Math.sqrt(200 / 4.0)) < 0.1) {
//                isDoorUnlocked = true;
//                showDoorOpenScene(); // Only open door if correct
            } else {
                isDoorUnlocked = false;
                KillPlayer.killPlayer("You missed the target...\nThe oscillations carried on without purpose. Trapped in repetition, you remain.", stage, stage.getScene(), overlay);
            }
        });

        pause.play();
    }

    private ImageView createImageView(String path, double x, double y, double width, double height) {
        ImageView imageView = new ImageView(new Image(getClass().getResource(path).toExternalForm()));
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    //create a spring image and handle click events
    private ImageView createSpringImage(String imagePath, double x, double y) {
        ImageView spring = createImageView(imagePath, x, y, 50, 100);

//        spring.setOnMouseClicked(event -> {
//            springLab.setSelectedSpringConstant(springConstant);  // Call controller to set value
//            System.out.println("Spring selected: " + springConstant + " N/m");
//        });
        return spring;
    }

    //create a mass image and handle click events
    private ImageView createMassImage(String imagePath, double x, double y) {
        ImageView massImage = createImageView(imagePath, x, y, 150, 150);

//        massImage.setOnMouseClicked(event -> {
//            springLab.setSelectedMass(mass);  // Call controller to set value
//            System.out.println("Mass selected: " + mass + " kg");
//        });
        return massImage;
    }

    // Start the rCircuit lab
    private void startRCircuitLab() {
        rCircuit lab = new rCircuit(stage);
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
