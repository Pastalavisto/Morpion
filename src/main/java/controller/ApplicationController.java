package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import model.SceneController;
import app.Application;

import java.io.IOException;

public class ApplicationController {

    @FXML
    void lancementPartieButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("lancementPartie.fxml"));
        SceneController.changeScene(new Scene(fxmlLoader.load()));
    }
}