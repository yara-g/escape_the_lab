module com.example.escape_the_lab {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.escape_the_lab to javafx.fxml;
    exports com.example.escape_the_lab;
}