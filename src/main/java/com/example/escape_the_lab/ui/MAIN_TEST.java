package com.example.escape_the_lab.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MAIN_TEST extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        rCircuit room = new rCircuit();
        Scene scene = room.makeScene();
        stage.setScene(scene);

        stage.show();
    }
}
