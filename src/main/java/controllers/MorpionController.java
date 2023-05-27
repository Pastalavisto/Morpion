package controllers;

import app.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Morpion;
import models.ParametresPartie;
import models.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MorpionController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private FlowPane flowPaneJoueur;

    private Morpion model;

    @FXML
    private MenuItem menuItemReset;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemRegles;

    @FXML
    private Label labelResultat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = ParametresPartie.getLastMorpion();
        model.setMorpionController(this);
        model.setFlowPaneJoueur(flowPaneJoueur);
        model.setLabelResultat(labelResultat);
        GridPane gridPane = model.getGridPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
        menuItemReset.setOnAction(event -> {
            try {
                reset(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        menuItemClose.setOnAction(event -> close(event));
        menuItemRegles.setOnAction(event -> {
            try {
                rules(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    void close(ActionEvent event) {
        close();
    }

    public void close() {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        ParametresPartie.retirerMorpion(model);
        stage.close();
    }

    @FXML
    void reset(ActionEvent event) throws IOException {
        reset();
    }

    public void reset() throws IOException {
        model.reset();
        GridPane gridPane = model.getGridPane();
        borderPane.setCenter(gridPane);
        gridPane.setAlignment(Pos.CENTER);
        model.startIfBot();
    }

    @FXML
    void rules(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/view/Regles.fxml"));
        SceneController.addModalWindow(fxmlLoader.load(), Modality.APPLICATION_MODAL, "Règles du jeu");

    }
}
