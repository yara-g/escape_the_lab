package com.example.escape_the_lab.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class rCircuit extends Application {
    public void start(Stage stage) throws Exception {
        rCircuit room = new rCircuit();
        Scene scene = room.makeScene();
        stage.setScene(scene);

        stage.show();
    }

    public Scene makeScene() {
        /*
        left: drawer
        center: metal box with wires in them (idk what theyre called)
        right: blocked metal door
        if user tries to open the door, a little message tells them that its mechanically shut
        the metal box is open and it shows two disconnected wires. little paper at the bottom of the box.
        it says:
        actually im gonna make the electrical problem some other day bc idk how electricity works
         */

        HBox pane = new HBox(100);
        Scene scene = new Scene(pane, 1000, 650);

        ImageView drawer = new ImageView(new Image(getClass().getResource("/images/cat.png").toExternalForm()));
        drawer.setPreserveRatio(true);
        drawer.setFitHeight(300);
        drawer.setTranslateY(200);

        ImageView metalBox = new ImageView(new Image(getClass().getResource("/images/cat.png").toExternalForm()));
        metalBox.setPreserveRatio(true);
        metalBox.setTranslateY(150);
        metalBox.setFitHeight(300);

        ImageView door = new ImageView(new Image(getClass().getResource("/images/cat.png").toExternalForm()));
        door.setFitHeight(500);
        door.setFitWidth(200);

        pane.getChildren().addAll(drawer, metalBox, door);

        return scene;
    }
}
