/// line 58-79, 92-99
package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.model.Lab;
import com.example.escape_the_lab.model.Player;
import com.example.escape_the_lab.model.Substance;
import com.example.escape_the_lab.ui.Help;
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
    private Player player = Player.getLastPlayer();
    boolean language = false;


    Item chosenItem;
    Item redTool = new Item("Calcium Carbonate", "/images/AAAAcidLab/rTA.png"); // yes
    Item yellowTool = new Item("Sulfuric Acid", "/images/AAAAcidLab/yTA.png");
    Item greenTool = new Item("Sodium Hydroxide", "/images/AAAAcidLab/gTA.png"); // yes
    Item pinkTool = new Item("Ammonia", "/images/AAAAcidLab/pTA.png");
    Item purpleTool = new Item("Water", "/images/AAAAcidLab/puTA.png");

    private final ImageView red = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/rA.png")).toExternalForm()));
    private final ImageView yellow = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/yA.png")).toExternalForm()));
    private final ImageView pink = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/pA.png")).toExternalForm()));
    private final ImageView purple = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/puA.png")).toExternalForm()));
    private final ImageView green = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/gDropA1.png")).toExternalForm()));

    private final ImageView redDrop = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/rDropA3.png")).toExternalForm()));
    private final ImageView yellowDrop = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/yDropA4.png")).toExternalForm()));
    private final ImageView pinkDrop = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/pDropA2.png")).toExternalForm()));
    private final ImageView purpleDrop = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/puDropA5.png")).toExternalForm()));
    private final ImageView greenDrop = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/gDropA1.png")).toExternalForm()));

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

    private final ImageView houseZA = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/houseZA.png")).toExternalForm()));
    private final ImageView noAcid = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/noAcidA.png")).toExternalForm()));
    private final ImageView enter = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/enterA.png")).toExternalForm()));
    private final ImageView entrer = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/entrerA.png")).toExternalForm()));
    private final ImageView flowerZ = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/flowerZA.png")).toExternalForm()));
    private final ImageView flowerZF = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/flowerZFA.png")).toExternalForm()));

    public AcidNeutralizationLab(Stage stage) {
        this.primaryStage = stage;
        this.overlay = GameController.getOverlay();
        this.acidNeutralizationLabUI = new rAcidNeutralization(stage);
        this.lifeManager = GameController.getLifeManager();
    }

    /**
     * initialize the lab
     *
     * @param lab the lab
     */
    public void initializeLab(Lab lab) {
        this.lab = lab;
        substance1 = new Substance("Sodium Hydroxide", 14.0, AcidSprite1, 100, 100);
        substance2 = new Substance("Ammonia", 11.0, AcidSprite2, 100, 100);
        substance3 = new Substance("Calcium Carbonate", 10.0, AcidSprite3, 100, 100);
        substance4 = new Substance("Sulfuric Acid", 2.75, AcidSprite4, 100, 100);
        substance5 = new Substance("Water", 7.0, AcidSprite5, 100, 100);

        activeSubstances.add(substance1);
        activeSubstances.add(substance2);
        activeSubstances.add(substance3);
        activeSubstances.add(substance4);
        activeSubstances.add(substance5);
    }

    /**
     * initialize the lab
     */
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

    }

    /**
     * start the lab
     */
    @Override
    public void startLab() {
        primaryStage.setScene(createScene());
        primaryStage.show();
        inventory.setMouseTransparent(true);
    }

    /**
     * checks the solution of the lab
     *
     * @return if the lab is solved or not
     */
    @Override
    public boolean checkSolution() {
        if (droppedSubstances.isEmpty()) {
            return false;
        }
        boolean hasNeutralized = false;
        detectDroppedSubstances();

        for (Substance substance : droppedSubstances) {
            if (substance.equals(substance3) && substance.equals(substance1)) {
                hasNeutralized = true;
            }
            detectDroppedSubstances();
        }
        return hasNeutralized;
    }

    /**
     * shows the scene when the lab if failed
     */
    public void failLab() {
        showFailedScreen();
    }

    /**
     * creates set up the lab
     */
    public void setupLab() {
        createScene();
    }

    /**
     * creates the scene of the lab
     *
     * @return
     */
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
            if (player.getLanguage().equals("french")) {
                language = true;
            } else {
                language = false;
            }
            overlay.getHelpButton().setOnMouseClicked(e -> {
                Image helpImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/help/helpBtn.png")));
                Help.show("This is a special type of neutralization! HCl is a strong acid, it requires 2 different " +
                        "basses to be fully neutralized", helpImage);
            });
            arenaPane.getChildren().addAll(backGroundA, acidFloorA, doorA, treeA, houseA, hintFlowerA, bigFlowerA);
            arenaPane.getChildren().addAll(inventory, overlay.getOverlayPane());

            return new Scene(labRoot, 1000, 650);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * shows the scene when the flower is pressed depending on the language. There is a slider that checks if the input answer is right and if it is
     * right then a substance appears and can be collected
     */
    private void pressBigFlower() {
        Slider phSlider = new Slider(0, 14, 0);
        phSlider.setShowTickMarks(true);
        phSlider.setShowTickLabels(true);
        phSlider.setBlockIncrement(1);
        phSlider.setMajorTickUnit(1);
        phSlider.setMinorTickCount(1);
        phSlider.setLayoutX(145);
        phSlider.setLayoutY(230);
        phSlider.setMaxWidth(150);
        phSlider.setPrefWidth(150);

        Label phValueLabel = new Label("pH: 0.0");
        phValueLabel.setStyle("-fx-font-size: 14px;");
        phValueLabel.setLayoutX(125);
        phValueLabel.setLayoutY(270);

        phSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            phValueLabel.setText(String.format("pH: %.1f", newValue));
        });

        Button submitButton = new Button("Enter");
        submitButton.setLayoutX(125);
        submitButton.setLayoutY(300);

        Button submitButtonFr = new Button("Entré");
        submitButtonFr.setLayoutX(125);
        submitButtonFr.setLayoutY(300);

        Label resultLabel = new Label();
        resultLabel.setStyle("-fx-font-size: 13px;");
        resultLabel.setLayoutX(105);
        resultLabel.setLayoutY(325);

        VBox substanceContainer = new VBox(5);
        substanceContainer.setAlignment(Pos.CENTER);
        substanceContainer.setLayoutX(160);
        substanceContainer.setLayoutY(340);

        submitButton.setOnAction(e -> {
            double pH = phSlider.getValue();

            if (Math.abs(pH - 1.0) < 0.1) {

                resultLabel.setText("Correct! You unlocked a new substance.");
                resultLabel.setStyle("-fx-text-fill: green;");


                ImageView clickableImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/gDropA1.png")).toExternalForm()));
                ;
                clickableImage.setFitWidth(100);

                clickableImage.setPreserveRatio(true);
                substanceContainer.getChildren().add(clickableImage);

                Item substanceUnlocked = new Item("substance unlocked", "/images/AAAAcidLab/gTA.png");

                clickableImage.setOnMouseClicked(event -> {
                    overlay.getInventory().addItem(substanceUnlocked);
                    overlay.updateInventory();
                    showCollectedSubstanceInLab(substanceUnlocked);
                    substanceContainer.getChildren().remove(clickableImage);
                });


            } else {
                resultLabel.setText("Incorrect! Try again.");
                resultLabel.setStyle("-fx-text-fill: red;");

            }
        });
        submitButtonFr.setOnAction(e -> {
            double pH = phSlider.getValue();

            if (Math.abs(pH - 1.0) < 0.1) {
                resultLabel.setText("Correcte! Tu as débloqué une nouvelle substance.");
                resultLabel.setStyle("-fx-text-fill: green;");


                ImageView clickableImage = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/gDropA1.png")).toExternalForm()));
                clickableImage.setFitWidth(100);

                clickableImage.setPreserveRatio(true);
                substanceContainer.getChildren().add(clickableImage);

                Item substanceUnlocked = new Item("substance unlocked", "/images/AAAAcidLab/gTA.png");

                clickableImage.setOnMouseClicked(event -> {
                    overlay.getInventory().addItem(substanceUnlocked);
                    overlay.updateInventory();
                    showCollectedSubstanceInLab(substanceUnlocked);
                    substanceContainer.getChildren().remove(clickableImage);
                });

            } else {
                resultLabel.setText("Incorrecte! Réessayer.");
                resultLabel.setStyle("-fx-text-fill: red;");
            }
        });

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> {
            primaryStage.setScene(createScene());

        });
        closeButton.setLayoutX(125);
        closeButton.setLayoutY(345);
        Button closeButtonFr = new Button("Fermé");
        closeButton.setOnAction(event -> {
            primaryStage.setScene(createScene());

        });
        closeButtonFr.setLayoutX(125);
        closeButtonFr.setLayoutY(345);

        Pane layout = new Pane();
        Pane layoutFr = new Pane();

        if (language == true) {
            layoutFr.getChildren().addAll(flowerZF, phSlider, phValueLabel, submitButtonFr, resultLabel, substanceContainer, closeButtonFr, inventory, overlay.getOverlayPane());
            Scene flowerSceneFr = new Scene(layoutFr, 1000, 650);
            primaryStage.setScene(flowerSceneFr);
        } else {
            layout.getChildren().addAll(flowerZ, phSlider, phValueLabel, submitButton, resultLabel, substanceContainer, closeButton, inventory, overlay.getOverlayPane());
            Scene flowerScene = new Scene(layout, 1000, 650);
            primaryStage.setScene(flowerScene);
        }
        primaryStage.show();
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
                ClipboardContent content = new ClipboardContent();
                content.putImage(finalImage);
                db.setContent(content);
            });
            // set initial size (or reset it if needed)
            img.setFitWidth(100);
            img.setFitHeight(100);
        }
    }

    /**
    * make the substance draggable within the arena after it's dropped
     * */
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
   /**
    * Set up the arena to accept dropped subtances
     */
    private void setTarget() {
        arenaPane.setOnDragOver(event -> {
            if (event.getGestureSource() != arenaPane && event.getDragboard().hasImage()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        });
        arenaPane.setOnDragDropped(this::addSubstanceToArena);
    }

    /**
     * Add the substance display to the arena Pane
     *
     * @param event The DragEvent from the drag and drop action of the user
     * moving the substance
     */
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

        /**
     * Sets up the substance and substance bank to handle the substance being dragged back to
     * the substance bank
     *
     * @param substance the Substance that is being dragged
     */
    private void makeSubstanceRemovable(Substance substance) {
        substance.display.setOnDragDetected(event -> {
            Image dragImage = substance.sprite.getImage();
            if (dragImage != null) {
                Dragboard db = substance.display.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putImage(dragImage);
                db.setContent(content);
            } else {
                System.out.println("Drag image is null. Drag cancelled.");
            }
            event.consume();
        });

        // Set up the acidBank to accept substances dragged back from the arena
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
                droppedSubstances.remove(substance);
                event.setDropCompleted(true);
            } else {
                event.setDropCompleted(false);
            }
        });
    }

    /**
     * removes the subsatnce from the arena
     *
     * @param substance selected substance
     */
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
        makeSubstanceRemovable(substance);
        substance.setActive(false);
        arenaPane.getChildren().remove(substance.display);
    }

        /**
     * Create a new instance of the Substance class
     *
     * @param substanceDisplay the StackPane to be assigned to the new Substance
     * @return the new Substance object
     */
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

            // Handle focus of substances
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

    /**
     * detects which substances are dragged and dropped on the pane
     */
    private void detectDroppedSubstances() {
        for (Substance substance : droppedSubstances) {
            System.out.println("Dropped substance: " + substance.getName());
        }
        System.out.println("Dropped substances: " + droppedSubstances);

        boolean has3 = false;
        boolean has5 = false;

        for (Substance s : droppedSubstances) {
            if (s.substanceNumber() == 3) {
                has3 = true;
            }
            if (s.substanceNumber() == 1) {
                has5 = true;
            }
        }

        if (has3 && has5) {
            System.out.println("Success! You neutralized the floor");
            arenaPane.setStyle("");
            succeedLab = true;
            doorOpen(primaryStage);
            arenaPane.getChildren().remove(acidFloorA);
            showSuccessScreen();
        } else if (droppedSubstances.size() >= 2) {
            showFailedScreen();
            System.out.println("Incorrect substances! You lose a life.");
        }
    }

    /**
     * shows the scene when the player succeeded the lab
     */
    private void showSuccessScreen() {
        succeedLab = true;
        Stage successStage = new Stage();
        if (language == true) {
            successStage.setTitle("Labo Complété!");

            Label successLabelFr = new Label("Vous avez neutralisé la solution avec succès!\n " +
                    " L'acide chlorhydrique (HCl) a été neutralisé par deux bases différentes: \n " +
                    " NaOH et CaCO₃. NaOH a réagi selon une réaction acide-base classique" +
                    "pour former du sel et de l'eau,\n tandis que CaCO₃ a créé un effet de bulles en libérant du CO₂.");
            successLabelFr.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
            successLabelFr.setWrapText(true);
            successLabelFr.setMaxWidth(450);
            Button closeButtonFr = new Button("Fermé");
            closeButtonFr.setOnAction(e -> successStage.close());
            closeButtonFr.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14px;");

            VBox layoutFr = new VBox(10, successLabelFr, closeButtonFr);
            layoutFr.setAlignment(Pos.CENTER);
            layoutFr.setPadding(new Insets(20));
            layoutFr.setStyle("-fx-background-color: black;");

            Scene successSceneFr = new Scene(layoutFr, 1000, 650);
            successStage.setScene(successSceneFr);
        } else {
            successStage.setTitle("Lab Complete!");

            Label successLabel = new Label("You successfully neutralized the solution!\n "
                    + "Hydrochloric acid (HCl) was neutralized by two different bases: "
                    + "NaOH and CaCO₃. NaOH reacted in a typical acid-base reaction"
                    + "to form salt and water,\n while CaCO₃ created a bubbling effect by releasing CO₂ gas.");
            successLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: white;");
            successLabel.setWrapText(true);
            successLabel.setMaxWidth(450);
            Button closeButton = new Button("OK");
            closeButton.setOnAction(e -> successStage.close());
            closeButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 14px;");

            VBox layout = new VBox(10, successLabel, closeButton);
            layout.setAlignment(Pos.CENTER);
            layout.setPadding(new Insets(20));
            layout.setStyle("-fx-background-color: black;");

            Scene successScene = new Scene(layout, 1000, 650);
            successStage.setScene(successScene);
        }
        successStage.show();
    }

    /**
     * shows the scene when the player fails the lab
     */
    private void showFailedScreen() {
        resetSubstancesToHome();
        if (language == true) {
            KillPlayer.killPlayer("Vous avez mélangé les mauvaises substances et les avez dissoutes dans l'acide !\n" +
                    " Indice : Il s'agit d'un type de neutralisation particulier ! HCl est un acide fort ; il nécessite deux bases différentes pour être totalement neutralisé.", primaryStage, primaryStage.getScene(), overlay);
        } else {
            KillPlayer.killPlayer("You have mixed the wrong substances and dissolved in the acid!\n" +
                    "Hint: This is a special type of neutralization! HCl is a strong acid, it requires 2 different bases to" +
                    " be fully neutralized", primaryStage, primaryStage.getScene(), overlay);
        }

        arenaPane.getChildren().removeAll(substance1.getSprite(), substance2.getSprite(), substance3.getSprite(), substance4.getSprite(), substance5.getSprite());
    }

    /**
     * resets the substances back to their home when being dragged and dropped back
     */
    private void resetSubstancesToHome() {
        for (Substance s : droppedSubstances) {
            ImageView sprite = s.getSprite();
            ImageView home = s.getHome();

            if (sprite.getImage() == null) {
                sprite.setImage(s.getOriginalImage());
            }

            arenaPane.getChildren().remove(sprite);

            sprite.setLayoutX(home.getLayoutX());
            sprite.setLayoutY(home.getLayoutY());

            if (!((Pane) home.getParent()).getChildren().contains(sprite)) {
                ((Pane) home.getParent()).getChildren().add(sprite);
            }
        }
        droppedSubstances.clear();
    }

    /**
     * if the lab is succeeded, it shows the congratulation stage
     * @param stage the congrats stage
     */
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
                        Label congratsLabelFr = new Label("🎉 Félicitations! Vous avez échappé le labo! 🎉");
                        congratsLabel.setStyle("""
                                    -fx-font-size: 36px;
                                    -fx-text-fill: black;
                                    -fx-font-weight: bold;
                                """);

                        Button exitButton = new Button("Exit");
                        exitButton.setStyle("-fx-font-size: 18px;");
                        exitButton.setOnAction(e -> stage.close());

                        Button exitButtonFr = new Button("Fermé");
                        exitButton.setStyle("-fx-font-size: 18px;");
                        exitButton.setOnAction(e -> stage.close());

                        VBox layout = new VBox(30, congratsLabel, exitButton);
                        layout.setAlignment(Pos.CENTER);
                        layout.setStyle("-fx-background-color: #f0f8ff;");

                        VBox layoutFr = new VBox(30, congratsLabelFr, exitButtonFr);
                        layout.setAlignment(Pos.CENTER);
                        layout.setStyle("-fx-background-color: #f0f8ff;");

                        if (language == true) {
                            Scene congratsSceneFr = new Scene(layoutFr, 1000, 650);
                            stage.setScene(congratsSceneFr);
                        } else {
                            Scene congratsScene = new Scene(layout, 1000, 650);
                            stage.setScene(congratsScene);
                        }
                    }
            );
        }
        if (!succeedLab) {
            doorA.setOnMouseClicked((event) -> {
                System.out.println("Door is locked...");
            });
        }
    }

    /**
     * creates the room where the substances are in the house to collect them in the inventory
     * @return the scene of the house zoom
     */
    private Scene createCollectionRoomScene() {
        Pane root = new Pane();

        ImageView houseBackground = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/images/AAAAcidLab/houseZA.png")).toExternalForm()));
        houseBackground.setFitWidth(1000);
        houseBackground.setFitHeight(650);

        red.setPreserveRatio(true);
        red.setLayoutX(50);
        red.setLayoutY(290);
        red.setFitWidth(700);

        pink.setPreserveRatio(true);
        pink.setLayoutX(260);
        pink.setLayoutY(-425);
        pink.setFitWidth(1500);

        yellow.setPreserveRatio(true);
        yellow.setLayoutX(213);
        yellow.setLayoutY(-100);
        yellow.setFitWidth(1800);

        purple.setPreserveRatio(true);
        purple.setLayoutX(130);
        purple.setLayoutY(1);
        purple.setFitWidth(1000);

        Button returnButton = new Button("Close");
        returnButton.setLayoutX(125);
        returnButton.setLayoutY(125);
        returnButton.setOnAction(e -> primaryStage.setScene(createScene()));

        Button returnButtonFr = new Button("Fermé");
        returnButton.setLayoutX(125);
        returnButton.setLayoutY(125);
        returnButton.setOnAction(e -> primaryStage.setScene(createScene()));

        setupCollectible(red, redTool);
        setupCollectible(pink, pinkTool);
        setupCollectible(yellow, yellowTool);
        setupCollectible(purple, purpleTool);
        if (language == true) {
            root.getChildren().addAll(houseBackground, purple, yellow, pink, red, returnButtonFr, inventory, overlay.getOverlayPane());
        }
        root.getChildren().addAll(houseBackground, purple, yellow, pink, red, returnButton, inventory, overlay.getOverlayPane());
        return new Scene(root, 1000, 650);
    }

    /**
     * allows the player to collect an item and have it in the inventory as a different image
     * @param view the image that the player presses on
     * @param item the item added to the inventory
     */
    private void setupCollectible(ImageView view, Item item) {
        view.setOnMouseClicked(e -> {
            view.setVisible(false);
            overlay.getInventory().addItem(item);
            overlay.updateInventory();
        });
    }

    /**
     * shows which substances are collected in the lab
     * @param substanceItem the item collected
     */
    private void showCollectedSubstanceInLab(Item substanceItem) {
        if (substanceItem == redTool) {
            AcidImage1.setOpacity(1.0);
        } else if (substanceItem == yellowTool) {
            AcidImage2.setOpacity(1.0);
        } else if (substanceItem == greenTool) {
            AcidImage3.setOpacity(1.0);
        } else if (substanceItem == pinkTool) {
            AcidImage4.setOpacity(1.0);
        }
    }
}
