module morpion.morpion {
    requires javafx.controls;
    requires javafx.fxml;


    opens app to javafx.fxml;
    exports app;

    opens controller to javafx.fxml;
    exports controller;

}