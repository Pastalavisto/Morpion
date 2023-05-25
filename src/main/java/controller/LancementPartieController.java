package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import model.*;
import app.Application;

import java.io.IOException;

public class LancementPartieController {

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
        for (int i = 0; i < hBoxJoueur.getChildren().size(); i++){
            VBox vbox = (VBox) hBoxJoueur.getChildren().get(i);
            TextField textField = (TextField) vbox.getChildren().get(0);
            String nom = textField.getText();
            GestionMorpions.getJoueur(i).setNom(nom);
        }
        GestionMorpions.setTaille(taille);
        GestionMorpions.ajouterMorpion();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("morpion.fxml"));
        SceneController.addModalWindow(fxmlLoader.load(), Modality.WINDOW_MODAL);
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
        SceneController.addModalWindow(fxmlLoader.load(), Modality.WINDOW_MODAL);
    }

    @FXML
    void updateJoueur(MouseEvent event) {
        int ancienNbrJoueur = Integer.parseInt(labelNbrJoueur.getText());
        labelNbrJoueur.setText(String.valueOf((int) sliderJoueur.getValue()));
        int nouveauNbrJoueur = Integer.parseInt(labelNbrJoueur.getText());
        if (nouveauNbrJoueur > ancienNbrJoueur) {
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
        } else if (nouveauNbrJoueur < ancienNbrJoueur) {
            GestionMorpions.retirerJoueur();
            hBoxJoueur.getChildren().remove(hBoxJoueur.getChildren().size() - 1);
        }

    }

}
