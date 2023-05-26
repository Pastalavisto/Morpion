package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import model.Case;
import model.GestionMorpions;
import model.Morpion;

import java.net.URL;
import java.util.ResourceBundle;

public class MorpionController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label nomJoueurLabel;

    private Morpion morpion;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        morpion = GestionMorpions.getLastMorpion();
        nomJoueurLabel.setText("Joueur : " + morpion.getJoueurCourant().getNom());
        GridPane gridPane = morpion.getGridPane();

        gridPane.setAlignment(Pos.CENTER);

        borderPane.setCenter(gridPane);
        morpion.setNomJoueurLabel(nomJoueurLabel);
    }

    @FXML
    void close(ActionEvent event) {

    }

    @FXML
    void reset(ActionEvent event) {
        morpion.reset();
    }

    @FXML
    void rules(ActionEvent event) {

    }
}
