package model;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SceneController {
    private static Window window;
    public static void changeScene(Scene scene) {
        Stage stage = (Stage) window;
        stage.setScene(scene);
    }

    public static void setWindow(Window window) {
        SceneController.window = window;
    }

    public static Stage addModalWindow(Pane pane,Modality modality,String nom) {
        Stage stage = new Stage();
        stage.initModality(modality);
        stage.setScene(new Scene(pane));
        stage.setTitle(nom);
        stage.show();
        return stage;
    }

}
