package app;

import controllers.ChoixSymboleController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.ParametresPartie;
import models.SceneController;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/view/Parametres.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Lancement de la partie");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        SceneController.setWindow(stage);
        ParametresPartie.init();
        ChoixSymboleController.init();
    }
}