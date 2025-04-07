package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.Substance;
import com.example.escape_the_lab.ui.Overlay;
import com.example.escape_the_lab.ui.rAcidNeutralization;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.util.Objects;

public class AcidNeutralizationLab extends Lab {
    public ArrayList<Substance> activeSubstances = new ArrayList<>();
    public ArrayList<Substance> droppedSubstances = new ArrayList<>();
    @FXML
    ImageView AcidSprite1, AcidSprite2, AcidSprite3, AcidSprite4, AcidSprite5, AcidImage1, AcidImage2, AcidImage3, AcidImage4, AcidImage5;
    @FXML
    StackPane AcidHome1, AcidHome2, AcidHome3, AcidHome4, AcidHome5, AcidDisplay1, AcidDisplay2, AcidDisplay3, AcidDisplay4, AcidDisplay5;
    @FXML
    Pane arenaPane;
    @FXML
    VBox AcidBank;
    private Lab lab;
    Stage primaryStage;
    Substance substance1;
    Substance substance2;
    Substance substance3;
    Substance substance4;
    Substance substance5;
    boolean succeedLab = false;
    rAcidNeutralization acidNeutralizationLabUI;
    private LifeManager lifeManager;
    Item chosenItem;
    Item substanceItem1 = new Item("Hydrochloric Acid", "/images/pt2.png");
    Item substanceItem2 = new Item("Sulfuric Acid", "/images/pt3.png");
    Item substanceItem3 = new Item("Sodium Hydroxide", "/images/pt4.png");
    Item substanceItem4 = new Item("Ammonia", "/images/pt5.png");
    Item substanceItem5 = new Item("Acetic Acid", "/images/pt6.png");

    public Overlay overlay;
    KillPlayer killPlayer;
    private final ImageView backGroundA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/bgA.png")).toExternalForm()));
    private final ImageView treeA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/treeA.png")).toExternalForm()));
    private final ImageView houseA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/houseA.png")).toExternalForm()));
    private final ImageView doorA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/doorA.png")).toExternalForm()));
    private final ImageView bigFlowerA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/bigFlowerA.png")).toExternalForm()));
    private final ImageView hintFlowerA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/hintFlowerA.png")).toExternalForm()));
    private final ImageView acidFloorA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/acidA.png")).toExternalForm()));
    private final ImageView inventory = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/inventory.png")).toExternalForm()));

    public AcidNeutralizationLab(Stage stage) {
        this.primaryStage = stage;
        this.overlay = GameController.getOverlay();
        this.acidNeutralizationLabUI = new rAcidNeutralization(stage);
        this.lifeManager = GameController.getLifeManager();
    }

    @FXML
    private void initialize() {
        initializeLab(lab);
        AcidSprite1.setOpacity(0);
        AcidSprite2.setOpacity(0);
        AcidSprite3.setOpacity(0);
        AcidSprite4.setOpacity(0);
        AcidSprite5.setOpacity(0);

        setupDrag();
        setTarget();
        if (AcidBank == null) {
            System.out.println("arenaPane is still null!");
        }
        for (Substance s : activeSubstances) {
            System.out.println("Initialized: " + s.getName());
        }
    }

    @Override
    public void startLab() {
        primaryStage.setScene(createScene());
        primaryStage.show();
    }

    @Override
    public boolean checkSolution() {
        if (droppedSubstances.isEmpty()) {
            return false;
        }
        boolean hasNeutralized = false;
        detectDroppedSubstances();

        for (Substance substance : droppedSubstances) {
            if (substance.equals(substance3) && substance.equals(substance5)) {
                hasNeutralized = true;
            }
            detectDroppedSubstances();
        }

        return hasNeutralized;
    }

    public void failLab() {
        showFailedScreen();
    }

    public void setupLab() {
        createScene();
    }

    @Override
    public Scene createScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AcidNeutralizationLab_layout.fxml"));
            loader.setController(this);
            Parent labRoot = loader.load();
            doorOpen(primaryStage);
            bigFlowerA.setOnMouseClicked(mouseEvent ->
                    pressBigFlower());
            houseA.setOnMouseClicked(event -> {
                primaryStage.setScene(createCollectionRoomScene());
            });

            arenaPane.getChildren().addAll(backGroundA, acidFloorA, doorA, treeA, houseA, hintFlowerA, bigFlowerA);
            arenaPane.getChildren().addAll(inventory, overlay.getOverlayPane());

            return new Scene(labRoot, 1000, 650);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void pressBigFlower() {
ImageView scientistImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/scientist.jpg")).toExternalForm()));
        scientistImage.setFitHeight(200);
        scientistImage.setPreserveRatio(true);
        scientistImage.setLayoutX(100);
        scientistImage.setLayoutY(120);

        Label contextLabel = new Label("A scientist accidentally spilled some HCl with a concentration\n" +
                "of 0.1 M on the ground. Neutralize it to be able to pass and unlock the door!\n To unlock a substance, what is the pH of this solution of HCl?");
        contextLabel.setStyle("""
    -fx-background-color: white;
    -fx-border-color: black;
    -fx-border-radius: 10px;
    -fx-background-radius: 10px;
    -fx-padding: 15px;
    -fx-font-size: 16px;
""");
        contextLabel.setWrapText(true);
        contextLabel.setMaxWidth(400);
        contextLabel.setLayoutX(250);
        contextLabel.setLayoutY(100);

        StackPane speechBubble = new StackPane(contextLabel);
        speechBubble.setLayoutX(250);
        speechBubble.setLayoutY(100);

        Slider phSlider = new Slider(0, 14, 0);
        phSlider.setShowTickMarks(true);
        phSlider.setShowTickLabels(true);
        phSlider.setBlockIncrement(0.1);
        phSlider.setMajorTickUnit(1);
        phSlider.setMinorTickCount(1);
        phSlider.setLayoutX(300);
        phSlider.setLayoutY(280);
        phSlider.setMaxWidth(350);
        phSlider.setPrefWidth(350);

        Label phValueLabel = new Label("pH: 0.0");
        phValueLabel.setStyle("-fx-font-size: 14px;");
        phValueLabel.setLayoutX(300);
        phValueLabel.setLayoutY(325);

        phSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            phValueLabel.setText(String.format("pH: %.1f", newValue));
        });

        Button submitButton = new Button("Enter");
        submitButton.setLayoutX(300);
        submitButton.setLayoutY(350);

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 14px;");
        resultLabel.setLayoutX(300);
        resultLabel.setLayoutY(375);

        VBox substanceContainer = new VBox(10);
        substanceContainer.setAlignment(Pos.CENTER);
        substanceContainer.setLayoutX(300);
        substanceContainer.setLayoutY(400);

        submitButton.setOnAction(e -> {
            double pH = phSlider.getValue();

            if (Math.abs(pH - 1.0) < 0.1) { // pH should be around 1 for HCl
                resultLabel.setText("Correct! You unlocked a new substance.");
                resultLabel.setStyle("-fx-text-fill: green;");

                Item substanceUnlocked = new Item("substance unlocked", "/images/substanceUnlocked.png");
                ImageView iv = substanceUnlocked.getImageView();
                iv.setFitWidth(60); 
                iv.setPreserveRatio(true);
                handleSubstanceCollection(substanceUnlocked);

                substanceContainer.getChildren().add(iv);
            } else {
                resultLabel.setText("Incorrect! Try again.");
                resultLabel.setStyle("-fx-text-fill: red;");
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            primaryStage.setScene(createScene());

        });
        closeButton.setLayoutX(300);
        closeButton.setLayoutY(475);

        Pane layout = new Pane();

        layout.getChildren().addAll(scientistImage, speechBubble, phSlider, phValueLabel, submitButton, resultLabel, substanceContainer, closeButton, inventory, overlay.getOverlayPane());

        Scene flowerScene = new Scene(layout, 1000, 650);
        primaryStage.setScene(flowerScene);
        primaryStage.show();
    }

    public void initializeLab(Lab lab) {
        this.lab = lab;
        substance1 = new Substance("Hydrochloric Acid", 1.0, AcidSprite1, 100, 100);
        substance2 = new Substance("Sulfuric Acid", 2.0, AcidSprite2, 100, 100);
        substance3 = new Substance("Sodium Hydroxide", 13.0, AcidSprite3, 100, 100);
        substance4 = new Substance("Ammonia", 11.0, AcidSprite4, 100, 100);
        substance5 = new Substance("Acetic Acid", 4.7, AcidSprite5, 100, 100);

        activeSubstances.add(substance1);
        activeSubstances.add(substance2);
        activeSubstances.add(substance3);
        activeSubstances.add(substance4);
        activeSubstances.add(substance5);
    }

    /**
     * Set up draggable substances from the VBox
     */
    private void setupDrag() {
        StackPane[] substances = {AcidHome1, AcidHome2, AcidHome3, AcidHome4, AcidHome5};
        for (StackPane substance : substances) {
            ImageView img = new ImageView();
            for (Node child : substance.getChildren()) {
                if (child instanceof ImageView) {
                    img = (ImageView) child;
                }
            }
            // make each substance draggable
            Image finalImage = img.getImage();
            substance.setOnDragDetected(event -> {
                Dragboard db = substance.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();//stores data
                content.putImage(finalImage);
                db.setContent(content);
            });

            // set initial size (or reset it if needed)
            img.setFitWidth(100);
            img.setFitHeight(100);
        }
    }

    //    /**
//     * make the substance draggable within the arena after it's dropped
//     */
    private void Draggable(Substance substance) {
        substance.display.setOnMousePressed(event -> substance.display.setUserData(new double[]{event.getSceneX(), event.getSceneY()}));

        substance.display.setOnMouseDragged(event -> {
            try {
                // update the stored mouse coordinates
                substance.display.setUserData(new double[]{event.getSceneX(), event.getSceneY()});

                // allow the substance to be dragged back to the VBox
                makeSubstanceRemovable(substance);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
//
//    /**
//     * Set up the arena to accept dropped subtances
//     */

    private void setTarget() {
        arenaPane.setOnDragOver(event -> {
            if (event.getGestureSource() != arenaPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        });
        arenaPane.setOnDragDropped(this::addSubstanceToArena);
    }
//
//    /**
//     * Add the substance display to the arena Pane
//     *
//     * @param event The DragEvent from the drag and drop action of the user
//     * moving the substance
//     */

    private void addSubstanceToArena(DragEvent event) {
        Dragboard db = event.getDragboard();

        if (db.hasImage()) {
            StackPane draggedDisplay = (StackPane) event.getGestureSource();

            Substance currentSubstance = createNewSubstance(draggedDisplay);

            try {
                // Move the substance to the arena
                assert currentSubstance != null; // There should be a substance selected
                currentSubstance.sprite.setFitWidth(100);
                currentSubstance.sprite.setFitHeight(100);
                Objects.requireNonNull(currentSubstance).setActive(true);

                // Set the position of the substance to where it was dropped
                currentSubstance.display.setLayoutX(event.getX() - 50);
                currentSubstance.display.setLayoutY(event.getY() - 50);

                currentSubstance.initialPositionX = currentSubstance.display.getLayoutX();
                currentSubstance.initialPositionY = currentSubstance.display.getLayoutY();

                // Add the substance to the arena if it's not already there
                if (!arenaPane.getChildren().contains(currentSubstance.display)) {
                    arenaPane.getChildren().add(currentSubstance.display);
                    currentSubstance.sprite.setOpacity(1);
                    droppedSubstances.add(currentSubstance);

                }

                // make the substance draggable within the arena
                Draggable(currentSubstance);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            detectDroppedSubstances();
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }

    }

    //    /**
//     * Sets up the substance and substance bank to handle the substance being dragged back to
//     * the substance bank
//     *
//     * @param substance the Substance that is being dragged
//     */
    private void makeSubstanceRemovable(Substance substance) {
        substance.display.setOnDragDetected(event -> {
            Dragboard db = substance.display.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putImage(substance.sprite.getImage());
            db.setContent(content);
        });

        // Set up the acidBank to accept subastances dragged back from the arena
        AcidBank.setOnDragOver(event -> {
            if (event.getGestureSource() != AcidBank && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });

        // handle the drop when the substance is dragged back to the acidBank
        AcidBank.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();

            if (db.hasImage()) {
                removeSubstance(substance);
                activeSubstances.remove(substance);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
        });
    }
//
//    /**
//     * removes the subsatnce from the arena
//     *
//     * @param substance selected substance
//     */

    private void removeSubstance(Substance substance) {
        substance.display.setOpacity(0);
        substance.sprite.setFitWidth(100);
        substance.sprite.setFitHeight(100);

        switch (substance.substanceNumber()) {
            case 1 -> AcidHome1.getChildren().addLast(substance.display);
            case 2 -> AcidHome2.getChildren().addLast(substance.display);
            case 3 -> AcidHome3.getChildren().addLast(substance.display);
            case 4 -> AcidHome4.getChildren().addLast(substance.display);
            case 5 -> AcidHome5.getChildren().addLast(substance.display);

        }

        Draggable(substance);
        substance.setActive(false);
        arenaPane.getChildren().remove(substance.display);

    }
//    /**
//     * Create a new instance of the Substance class
//     *
//     * @param substanceDisplay the StackPane to be assigned to the new Substance
//     * @return the new Substance object
//     */

    private Substance createNewSubstance(StackPane substanceDisplay) {

        Substance currentSusbtance = null;
        ImageView substanceImage = new ImageView();
        for (Node node : substanceDisplay.getChildren()) {
            if (node instanceof ImageView) {
                substanceImage = (ImageView) node;
            }
        }

        if (!Substance.existsIn(activeSubstances, substanceImage)) {
            Substance newSubstance = new Substance(substanceImage);
            switch (newSubstance.substanceNumber()) {
                case 1 -> newSubstance = substance1;
                case 2 -> newSubstance = substance2;
                case 3 -> newSubstance = substance3;
                case 4 -> newSubstance = substance4;
                case 5 -> newSubstance = substance5;

            }
            switch (newSubstance.substanceNumber()) {
                case 1 -> newSubstance.setHome(AcidSprite1);
                case 2 -> newSubstance.setHome(AcidSprite2);
                case 3 -> newSubstance.setHome(AcidSprite3);
                case 4 -> newSubstance.setHome(AcidSprite4);
                case 5 -> newSubstance.setHome(AcidSprite5);
            }

            for (Substance other : activeSubstances) {
                other.setFocused(false);
            }

            newSubstance.setFocused(true);

            // Handle focus of ducks
            newSubstance.display.pressedProperty().addListener(event -> {

                for (Substance other : activeSubstances) {
                    other.setFocused(false);
                }
            });

            activeSubstances.add(newSubstance);

            currentSusbtance = newSubstance;
        } else {
            for (Substance substance : activeSubstances) {
                if (substance.sprite.equals(substanceImage)) {
                    currentSusbtance = substance;
                }
            }
        }
        return currentSusbtance;

    }

    private void detectDroppedSubstances() {
        for (Substance substance : droppedSubstances) {
            System.out.println("Dropped substance: " + substance.getName());
        }
        System.out.println("Dropped substances: " + droppedSubstances);
        if (droppedSubstances.contains(substance3) && droppedSubstances.contains(substance5)) {
            System.out.println("Success! You neutralized the floor");
            arenaPane.setStyle("");
            succeedLab = true;
            doorOpen(primaryStage);
            arenaPane.getChildren().remove(acidFloorA);
            showSuccessScreen();
            // Add logic to proceed to the next level
        } else if (droppedSubstances.size() >= 2) {
            showFailedScreen();

            System.out.println("Incorrect substances! You lose a life.");
            // Add logic to reduce player lives
        }
    }


    private void showSuccessScreen() {
        succeedLab = true;
        Stage successStage = new Stage();
        successStage.setTitle("Lab Complete!");

        Label successLabel = new Label("You successfully neutralized the solution!\n\n "
                + "Hydrochloric acid (HCl) was neutralized by two different bases: \n"
                + "NaOH and CaCO₃. NaOH reacted in a typical acid-base reaction"
                + "to form salt and water,\n while CaCO₃ created a bubbling effect by releasing CO₂ gas.");
        successLabel.setStyle("-fx-font-size: 16px;");
        successLabel.setWrapText(true); // Allow automatic text wrapping
        successLabel.setMaxWidth(450);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> successStage.close());

        VBox layout = new VBox(10, successLabel, closeButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        Scene successScene = new Scene(layout, 1000, 650);
        successStage.setScene(successScene);
        successStage.show();
    }

    private void showFailedScreen() {
//        Stage failedStage = new Stage();
//        failedStage.setTitle("Lab Failed!");
//
//        Label failedLabel = new Label("You have mixed the wrong substances!\n You just "
//                + "lost a life. \n Hint: This is a special type of neutralization! Since "
//                + "HCl is a strong acid, it requires two different bases to be fully neutralized");
//        failedLabel.setStyle("-fx-font-size: 16px;");
//        failedLabel.setWrapText(true); // Allow automatic text wrapping
//        failedLabel.setMaxWidth(450);
//        Button closeButton = new Button("OK");
//        closeButton.setOnAction(e -> failedStage.close());
//
//        VBox layout = new VBox(10, failedLabel, closeButton);
//        layout.setAlignment(Pos.CENTER);
//        layout.setPadding(new Insets(20));
//
//        Scene failedScene = new Scene(layout, 1000, 650);
//        failedStage.setScene(failedScene);
//        failedStage.show();

        KillPlayer.killPlayer("You have mixed the wrong substances and dissolved in the acid!\n" +
                "Hint: This is a special type of neutralization! HCl is a strong acid, it requires 2 different bases to" +
                " be fully neutralized", primaryStage, primaryStage.getScene(), overlay);
        arenaPane.getChildren().removeAll(substance1.getSprite(), substance2.getSprite(), substance3.getSprite(), substance4.getSprite(), substance5.getSprite());
    }

    public void doorOpen(Stage stage) {
        if (succeedLab) {
            doorA.setOnMouseClicked((event) -> {
                        System.out.println("Opening door");
                        Label congratsLabel = new Label("🎉 Congrats! You escaped the lab! 🎉");
                        congratsLabel.setStyle("""
                                    -fx-font-size: 36px;
                                    -fx-text-fill: black;
                                    -fx-font-weight: bold;
                                """);

                        Button exitButton = new Button("Exit");
                        exitButton.setStyle("-fx-font-size: 18px;");
                        exitButton.setOnAction(e -> stage.close());

                        VBox layout = new VBox(30, congratsLabel, exitButton);
                        layout.setAlignment(Pos.CENTER);
                        layout.setStyle("-fx-background-color: #f0f8ff;");

                        Scene congratsScene = new Scene(layout, 800, 600);
                        stage.setScene(congratsScene);
                    }
            );
        }
        if (!succeedLab) {
            doorA.setOnMouseClicked((event) -> {
                System.out.println("Door is locked...");
            });
        }
    }


    private Scene createCollectionRoomScene() {
        Pane root = new Pane();

        StackPane centerPane = new StackPane();
        centerPane.setPrefSize(800, 650);
        centerPane.setLayoutX(100);
        centerPane.setLayoutY(100);

        ImageView table = new ImageView(new Image(
                Objects.requireNonNull(getClass().getResource("/images/table.png")).toExternalForm()
        ));
        table.setFitWidth(500);
        table.setPreserveRatio(true);

        HBox substanceRow = new HBox(10);
        substanceRow.setAlignment(Pos.CENTER);
        substanceRow.setTranslateY(-130);
        addSubstanceToRow(substanceRow, substanceItem1);
        addSubstanceToRow(substanceRow, substanceItem2);
        addSubstanceToRow(substanceRow, substanceItem3);
        addSubstanceToRow(substanceRow, substanceItem4);


        centerPane.getChildren().addAll(table, substanceRow);


        Label instructionLabel = new Label("Click on the substances to collect them:");
        instructionLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        instructionLabel.setLayoutX(10);
        instructionLabel.setLayoutY(10);

        Button returnButton = new Button("Return to Lab");
        returnButton.setLayoutX(150);
        returnButton.setLayoutY(150);
        returnButton.setOnAction(e -> primaryStage.setScene(createScene()));

        handleSubstanceCollection(substanceItem1);
        handleSubstanceCollection(substanceItem2);
        handleSubstanceCollection(substanceItem3);
        handleSubstanceCollection(substanceItem4);
        root.getChildren().addAll(centerPane, instructionLabel, returnButton, inventory, overlay.getOverlayPane());


        return new Scene(root, 1000, 650);
    }


    private void addSubstanceToRow(HBox row, Item item) {
        ImageView iv = item.getImageView();
        iv.setFitWidth(60);
        iv.setPreserveRatio(true);
        iv.setOnMouseClicked(e -> {
            chosenItem = item;
            iv.setVisible(false);
            overlay.getInventory().addItem(item);
            overlay.updateInventory();
        });
        row.getChildren().add(iv);
    }

    private void handleSubstanceCollection(Item substanceItem) {
        substanceItem.getImageView().setOnMouseClicked(e -> {
            overlay.getInventory().addItem(substanceItem);
            overlay.updateInventory();
            showCollectedSubstanceInLab(substanceItem);
        });
    }

    private void showCollectedSubstanceInLab(Item substanceItem) {
        if (substanceItem == substanceItem1) {
            AcidImage1.setOpacity(1.0);
        } else if (substanceItem == substanceItem2) {
            AcidImage2.setOpacity(1.0);
        } else if (substanceItem == substanceItem3) {
            AcidImage3.setOpacity(1.0);
        } else if (substanceItem == substanceItem4) {
            AcidImage4.setOpacity(1.0);
        }
    }
}
