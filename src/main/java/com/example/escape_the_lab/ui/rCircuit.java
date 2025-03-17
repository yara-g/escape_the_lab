package com.example.escape_the_lab.ui;

import com.example.escape_the_lab.model.Lab;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class rCircuit extends Application {
    private Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Scene scene = makeScene();
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
        it says something like: the battery has an electric potential of 5V. the forward voltage of the LED is 3.2V. and the current needed is 0.02 A.
        find the resistance needed to make it light up. note: this is a special LED that will explode if the current is too high and even too low.
         */

        HBox pane = new HBox(100);
        Scene scene = new Scene(pane, 1000, 650);

        ImageView drawer = new ImageView(new Image(getClass().getResource("/images/drawers.png").toExternalForm()));
        drawer.setPreserveRatio(true);
        drawer.setFitHeight(300);
        drawer.setTranslateY(200);

        drawer.setOnMouseClicked(e -> {
            showInsideDrawer();
        });

        ImageView metalBox = new ImageView(new Image(getClass().getResource("/images/metal-box.png").toExternalForm()));
        metalBox.setPreserveRatio(true);
        metalBox.setTranslateY(150);
        metalBox.setFitHeight(200);

        metalBox.setOnMouseClicked(e -> {
            showInsideMetalBox();
        });

        ImageView door = new ImageView(new Image(getClass().getResource("/images/door.png").toExternalForm()));
        door.setFitHeight(500);
        door.setPreserveRatio(true);
        door.setTranslateX(-200);
        door.setTranslateY(50);

        door.setOnMouseClicked(e -> {
            showDoorMessage();
        });

        pane.getChildren().addAll(drawer, metalBox, door);
        Button temp = new Button("Skip to next");
        temp.setTranslateX(-1200);
        temp.setMinWidth(90);
        temp.setOnAction(e -> {
            rCircuit lab = new rCircuit();
            try {
                lab.start(stage);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        pane.getChildren().add(temp);

        return scene;
    }

    private void showInsideDrawer() {
        Pane drawerPane = new Pane();
        Scene drawerScene = new Scene(drawerPane, 1000, 650);
        Label res1 = new Label("resistor 1");
        res1.setTranslateX(200);
        res1.setTranslateY(200);
        Label res2 = new Label("resistor 2");
        res2.setTranslateX(400);
        res2.setTranslateY(400);
        Label res3 = new Label("resistor 3");
        res3.setTranslateX(400);
        res3.setTranslateY(300);
        Label res4 = new Label("resistor 4");
        res4.setTranslateX(500);
        res4.setTranslateY(500);
        drawerPane.getChildren().addAll(res1, res2, res3, res4);

        BackgroundImage myBI= new BackgroundImage(new Image(getClass().getResource("/images/in-drawer.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(1000, 650, true, true, true, true));
        drawerPane.setBackground(new Background(myBI));

        Button goBack = new Button("Go back");
        drawerPane.getChildren().add(goBack);

        goBack.setOnAction(e -> {
            stage.setScene(makeScene());
        });

        stage.setScene(drawerScene);
    }

    private void showInsideMetalBox() {

    }

    private void showDoorMessage() {
        System.out.println("the door is mechanically locked and way too heavy for you to push it.");
    }
}
