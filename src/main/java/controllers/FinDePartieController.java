package controllers;

import app.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.FinDePartie;
import models.Joueur;
import models.ParametresPartie;
import models.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FinDePartieController implements Initializable {

    private static FinDePartie model;
    @FXML
    private FlowPane flowPaneJoueur;

    public static void setModel(FinDePartie model) {
        FinDePartieController.model = model;
    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) flowPaneJoueur.getScene().getWindow();
        stage.close();
        model.close();
    }

    @FXML
    void rejouer(ActionEvent event) throws IOException {
        Stage stage = (Stage) flowPaneJoueur.getScene().getWindow();
        stage.close();
        for (int i = 0; i < flowPaneJoueur.getChildren().size(); i++) {
            VBox vbox = (VBox) flowPaneJoueur.getChildren().get(i);
            TextField textField = (TextField) vbox.getChildren().get(0);
            String nom = textField.getText();
            if (nom.equals("")) {
                nom = "Joueur " + (i + 1);
            }
            model.changerNomJoueur(i, nom);
        }
        model.appliquerModif();
        model.rejouer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Joueur> joueurs = model.getJoueurs();
        for (Joueur joueur : joueurs) {
            VBox vBox = new VBox();
            TextField textField = new TextField(joueur.getNom());
            vBox.getChildren().add(textField);

            vBox.getChildren().add(new Label(String.valueOf(joueur.getScore())));
            Button choixSymbole = new Button();
            choixSymbole.setText("Choix du symbole");
            choixSymbole.setOnAction(event -> {
                ParametresPartie.setJoueurCourant(joueur);
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/view/ChoixSymbole.fxml"));
                try {
                    Stage stage = SceneController.addModalWindow(fxmlLoader.load(), Modality.APPLICATION_MODAL, "Choix du symbole");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            vBox.getChildren().add(choixSymbole);
            flowPaneJoueur.getChildren().add(vBox);
        }
    }

}
