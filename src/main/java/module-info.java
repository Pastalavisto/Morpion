module morpion.morpion {
    requires javafx.controls;
    requires javafx.fxml;


    opens morpion.morpion to javafx.fxml;
    exports morpion.morpion;
    exports controller;
    opens controller to javafx.fxml;
}