package controller;

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
import model.Case;
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

    private Morpion morpion;

    @FXML
    private MenuItem menuItemReset;

    @FXML
    private MenuItem menuItemClose;

    @FXML
    private MenuItem menuItemRegles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        morpion = GestionMorpions.getLastMorpion();
        nomJoueurLabel.setText("Joueur : " + morpion.getJoueurCourant().getNom());
        GridPane gridPane = morpion.getGridPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
        morpion.setNomJoueurLabel(nomJoueurLabel);
        menuItemReset.setOnAction(event -> reset(event));
        menuItemClose.setOnAction(event -> close(event));
        menuItemRegles.setOnAction(event -> rules(event));
    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void reset(ActionEvent event) {
        System.out.println("reset");
        morpion.reset();
        nomJoueurLabel.setText("Joueur : " + morpion.getJoueurCourant().getNom());
        GridPane gridPane = morpion.getGridPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
        morpion.setNomJoueurLabel(nomJoueurLabel);
    }

    @FXML
    void rules(ActionEvent event) {
        System.out.println("rules");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("regles.fxml"));
        try {
            SceneController.addModalWindow(fxmlLoader.load(), Modality.WINDOW_MODAL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
