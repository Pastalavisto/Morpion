package app;

import controller.ChoixSymboleController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ParametresPartie;
import model.SceneController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("parametres.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Lancement de la partie");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        SceneController.setWindow(stage);
        ParametresPartie.init();
        ChoixSymboleController.init();
    }

    public static void main(String[] args) {
        launch();
    }
}