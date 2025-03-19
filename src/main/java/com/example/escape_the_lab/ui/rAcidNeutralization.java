package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.controller.AcidNeutralizationLab;
import com.example.escape_the_lab.controller.FlameLab;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Arrays;
import java.util.List;

public class rAcidNeutralization {
private Stage stage;
    private AcidNeutralizationLab acidNeutralizationLab;
    private Pane arenaPane;
    ImageView inventoryImage = new ImageView(new Image(getClass().getResource("/images/inventory.png").toExternalForm()));
    

    public rAcidNeutralization(Stage stage, AcidNeutralizationLab acidNeutralizationLab) {
        this.stage = stage;
        this.acidNeutralizationLab = acidNeutralizationLab;
    }
    public Scene getMainScene() {
        Pane root = new Pane();

        ImageView acid1 = createSubstance("/images/substance.png", 50, 100);
        ImageView acid2 = createSubstance("/images/substance.png", 50, 200);
        ImageView base1 = createSubstance("/images/substance.png", 50, 300);

        /**
         * FOR OLIVIAS LAB TEMPORARY
         */
        Button b = new Button("Skip");
        FlameLab flameLab = new FlameLab();
        b.setOnAction(e -> {
            flameLab.startLab(stage);
        });

        arenaPane = new Pane();
        arenaPane.setStyle("-fx-border-color: black; -fx-border-width: 2;");
        arenaPane.setPrefSize(500, 400);
        arenaPane.setLayoutX(300);
        arenaPane.setLayoutY(100);

        List<ImageView> substances = Arrays.asList(acid1, acid2, base1);
acidNeutralizationLab.registerSubstances(substances);

        root.getChildren().addAll(b, acid1, acid2, base1, arenaPane, inventoryImage);
        return new Scene(root, 1000, 650);
    }

    private ImageView createSubstance(String imagePath, double x, double y) {
        ImageView substance = new ImageView(new Image(getClass().getResource(imagePath).toExternalForm()));
        substance.setFitWidth(100);
        substance.setFitHeight(100);
        substance.setLayoutX(x);
        substance.setLayoutY(y);
        return substance;
    }

    public void showMainScene() {
        stage.setScene(getMainScene());
        stage.show();
    }
}
    
    

