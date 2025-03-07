package com.example.escape_the_lab.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
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

        FlowPane pane = new FlowPane();
        Scene scene = new Scene(pane, 1000, 650);

        ImageView drawer = new ImageView(new Image("images/cat.png"));
        drawer.setPreserveRatio(true);
        drawer.setFitHeight(300);
        drawer.setTranslateY(300);

        ImageView metalBox = new ImageView(new Image("images/cat.png"));
        metalBox.setPreserveRatio(true);
        metalBox.setFitHeight(300);
        metalBox.setTranslateY(200);
        metalBox.setTranslateX(200);

        ImageView door = new ImageView(new Image("images/cat.png"));
        door.setFitHeight(600);
        door.setFitWidth(200);
        door.setTranslateY(300);
        door.setTranslateX(300);

        pane.getChildren().addAll(drawer, metalBox, door);


        return scene;
    }
}
