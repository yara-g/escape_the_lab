package com.example.escape_the_lab.controller;

import com.example.escape_the_lab.model.Item;
import com.example.escape_the_lab.model.Player;
import com.example.escape_the_lab.model.Inventory;
import javafx.scene.Group;
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
    private final Group inventoryPane;
    private Group overlayPane;
    private final Group lifePane;
    Player player = GameController.getPlayer();
    ImageView helpButtonE = new ImageView(new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/images/help/helpBtn.png"))
    ));
    ImageView helpButtonF = new ImageView(new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/images/help/31.png"))
    ));
    ImageView helpButton;

    String themeSoundPath = Objects.requireNonNull(Overlay.class.getResource("/sounds/theme.mp3")).toExternalForm();
    Media themeMedia = new Media(themeSoundPath);
    MediaPlayer themeSoundPlayer = new MediaPlayer(themeMedia);


    public Overlay(Inventory inventory, LifeManager lifeManager) {
        this.lifeManager = lifeManager;
        this.inventory = inventory;
        inventoryPane = new Group();
        lifePane = new Group();
        if (player.getLanguage().equals("english")) {
            overlayPane = new Group(lifePane, inventoryPane, helpButtonE);
        } else {
            overlayPane = new Group(lifePane, inventoryPane, helpButtonF);
        }
    }

    public ImageView getHelpButton() {
        if (player.getLanguage().equals("english")) {
            return helpButtonE;
        } else {
            return helpButtonF;
        }
    }

    public void updateHelpLang() {
        if (player.getLanguage().equals("english")) {
            overlayPane = new Group(lifePane, inventoryPane, helpButtonE);
        } else {
            overlayPane = new Group(lifePane, inventoryPane, helpButtonF);
        }
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

        inventoryPane.getChildren().clear();
        inventoryPane.getChildren().add(vBox);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public LifeManager getLifeManager() {
        return lifeManager;
    }

    public static void playClick() {
        String clickSoundPath = Objects.requireNonNull(Overlay.class.getResource("/sounds/click.mp3")).toExternalForm();
        Media clickMedia = new Media(clickSoundPath);
        MediaPlayer clickSoundPlayer = new MediaPlayer(clickMedia);
        if (GameController.player.isSoundOn()) {
            clickSoundPlayer.seek(new Duration(0));
            clickSoundPlayer.play();
        }
    }

    public void setLifeManager(LifeManager lifeManager) {
        this.lifeManager = lifeManager;
    }

    public void beginTheme() {
        if (!GameController.player.isSoundOn()) {
            themeSoundPlayer.setMute(true);
        }
        themeSoundPlayer.setCycleCount(100);
        themeSoundPlayer.play();
    }

    public void reloadTheme() {
        if (GameController.player.isSoundOn()) {
            themeSoundPlayer.setMute(false);
        } else {
            themeSoundPlayer.setMute(true);
        }
    }
}
