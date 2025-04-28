package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.GameController;
import com.example.escape_the_lab.controller.LifeManager;
import com.example.escape_the_lab.model.Item;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.List;
import java.util.Objects;

public class Overlay {
    private LifeManager lifeManager;
    private Inventory inventory;
    private final Group inventoryPane; // Shared inventory pane
    private final Group overlayPane;
    private final Group lifePane;
    private ImageView helpButton = new ImageView(new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/images/help/helpBtn.png"))
    ));
    String clickSoundPath = Objects.requireNonNull(getClass().getResource("/sounds/click.mp3")).toExternalForm();
    Media clickMedia = new Media(clickSoundPath);
    MediaPlayer clickSoundPlayer = new MediaPlayer(clickMedia);

    public Overlay(Inventory inventory, LifeManager lifeManager) {
        this.lifeManager = lifeManager;
        this.inventory = inventory;
        inventoryPane = new Group();
        lifePane = new Group();
        helpButton = createHelpButton();

        overlayPane = new Group(lifePane, inventoryPane, helpButton);
    }

    private ImageView createHelpButton() {
        helpButton.setFitWidth(40);
        helpButton.setFitHeight(40);
        helpButton.setLayoutX(890);
        helpButton.setLayoutY(590);

        return helpButton;
    }

    public ImageView getHelpButton() {
        return helpButton;
    }

    public Group getOverlayPane() {
        return overlayPane;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void updateLifeManager() {
        HBox hearts = lifeManager.getLifeDisplay();
        hearts.setTranslateX(858);
        hearts.setTranslateY(26);
        lifePane.getChildren().clear();
        lifePane.getChildren().add(hearts);
    }

    public void updateInventory() {
        List<Item> items = inventory.getItems();
        VBox vBox = new VBox();

        vBox.getChildren().clear(); // Clear existing items before adding new ones

        for (Item item : items) {
            vBox.getChildren().add(item.getImageView());

            item.getImageView().setOnMousePressed(e -> {
                if (GameController.getPlayer().isSoundOn()) {
                   playClick();
                }
            });
        }

        vBox.setSpacing(17);
        vBox.setTranslateY(75);
        vBox.setTranslateX(858);

        inventoryPane.getChildren().clear(); // Clear the shared inventory pane
        inventoryPane.getChildren().add(vBox); // Add the updated VBox
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LifeManager getLifeManager() {
        return lifeManager;
    }

    public void playClick() {
        clickSoundPlayer.seek(new Duration(0));
        clickSoundPlayer.play();
    }

    public void setLifeManager(LifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }
}
