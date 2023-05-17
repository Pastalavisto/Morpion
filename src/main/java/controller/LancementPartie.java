package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import model.*;
import morpion.morpion.Application;

import java.io.IOException;

public class LancementPartie {
    @FXML
    private Button imageButtonJoueur1;

    @FXML
    private Button imageButtonJoueur2;

    @FXML
    private TextField textFieldJoueur1;

    @FXML
    private TextField textFieldJoueur2;

    @FXML
    private Slider sliderTaille;

    @FXML
    private Label labelTaille;

    @FXML
    private HBox hBoxJoueur;

    @FXML
    private Label labelNbrJoueur;

    @FXML
    private Slider sliderJoueur;


    @FXML
    void buttonLancementPartieClicked(ActionEvent event) throws IOException {
        int taille = Integer.parseInt(labelTaille.getText());
        GestionMorpions.setTaille(taille);
        GestionMorpions.ajouterMorpion();
        SceneController.addModalWindow(GestionMorpions.getLastMorpion().getGridPane(), Modality.WINDOW_MODAL);
    }

    @FXML
    void updateSliderTaille(MouseEvent event) {
        labelTaille.setText(String.valueOf((int) sliderTaille.getValue()));
    }

    @FXML
    void chooseSymbole(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String id = button.getId();
        int indexJoueur = Integer.parseInt(id.substring(id.indexOf("r") + 1));
        GestionMorpions.setIndexJoueurCourantModif(indexJoueur-1);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("choixSymbole.fxml"));
        SceneController.addModalWindow(fxmlLoader.load(), Modality.APPLICATION_MODAL);
    }

    @FXML
    void updateJoueur(MouseEvent event) {
        int acienNbrJoueur = Integer.parseInt(labelNbrJoueur.getText());
        labelNbrJoueur.setText(String.valueOf((int) sliderJoueur.getValue()));
        int nouveauNbrJoueur = Integer.parseInt(labelNbrJoueur.getText());
        if (nouveauNbrJoueur > acienNbrJoueur) {
            GestionMorpions.ajouterJoueur();
            VBox vbox = new VBox();
            TextField textField = new TextField();
            textField.setPromptText("Nom du joueur");
            Button button = new Button("Choisir un symbole");
            button.setId("buttonJoueur"+nouveauNbrJoueur);
            button.setOnAction(actionEvent -> {
                try {
                    chooseSymbole(actionEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            vbox.getChildren().add(textField);
            vbox.getChildren().add(button);
            hBoxJoueur.getChildren().add(vbox);
        } else if (nouveauNbrJoueur < acienNbrJoueur) {
            GestionMorpions.retirerJoueur();
            hBoxJoueur.getChildren().remove(hBoxJoueur.getChildren().size() - 1);
        }

    }

}
