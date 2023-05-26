package controller;

import app.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GestionMorpions;
import model.Morpion;
import model.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MorpionController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label nomJoueurLabel;

    private Morpion model;

    @FXML
    private MenuItem menuItemReset;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemRegles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        model = GestionMorpions.getLastMorpion();
        nomJoueurLabel.setText("Joueur : " + model.getJoueurCourant().getNom());
        GridPane gridPane = model.getGridPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
        model.setNomJoueurLabel(nomJoueurLabel);
        menuItemReset.setOnAction(event -> reset(event));
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
        Stage stage = (Stage) borderPane.getScene().getWindow();
        GestionMorpions.retirerMorpion(model);
        stage.close();
    }

    @FXML
    void reset(ActionEvent event) {
        System.out.println("reset");
        model.reset();
        nomJoueurLabel.setText("Joueur : " + model.getJoueurCourant().getNom());
        GridPane gridPane = model.getGridPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
        model.setNomJoueurLabel(nomJoueurLabel);
    }

    @FXML
    void rules(ActionEvent event) throws IOException {
        System.out.println("rules");
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("regles.fxml"));
        SceneController.addModalWindow(fxmlLoader.load(), Modality.APPLICATION_MODAL , "Règles du jeu");

    }
}
