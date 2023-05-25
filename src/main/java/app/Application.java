package app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GestionMorpions;
import model.SceneController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("application.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu Principal!");
        stage.setScene(scene);
        stage.show();
        SceneController.setWindow(stage);
        GestionMorpions.init();
    }

    public static void main(String[] args) {
        launch();
    }
}