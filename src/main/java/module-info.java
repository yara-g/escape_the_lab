module com.example.escape_the_lab {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    // opens com.example.escape_the_lab to javafx.fxml;
    exports com.example.escape_the_lab.controller;
    opens com.example.escape_the_lab.controller to javafx.fxml;
    exports com.example.escape_the_lab.ui;
    opens com.example.escape_the_lab.ui to javafx.fxml;
    exports com.example.escape_the_lab.model;
    opens com.example.escape_the_lab.model to javafx.fxml;
}