package controller;

import app.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.GestionMorpions;
import model.Morpion;
import model.SceneController;

import java.io.IOException;

public class LancementPartieController {

    @FXML
    private Slider sliderTaille;

    @FXML
    private Label labelTaille;

    @FXML
    private FlowPane flowPaneJoueur;

    @FXML
    private Label labelNbrJoueur;


    @FXML
    void buttonLancementPartieClicked(ActionEvent event) throws IOException {
        int taille = Integer.parseInt(labelTaille.getText().substring(0, labelTaille.getText().indexOf("x")));
        for (int i = 0; i < flowPaneJoueur.getChildren().size(); i++) {
            VBox vbox = (VBox) flowPaneJoueur.getChildren().get(i);
            TextField textField = (TextField) vbox.getChildren().get(0);
            String nom = textField.getText();
            if (nom.equals("")) {
                nom = "Joueur " + (i + 1);
            }
            GestionMorpions.getJoueur(i).setNom(nom);
        }
        GestionMorpions.setTaille(taille);
        int index = GestionMorpions.ajouterMorpion();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("morpion.fxml"));
        Stage stage = SceneController.addModalWindow(fxmlLoader.load(), Modality.WINDOW_MODAL, "Morpion numéro " + (index+1));
        Morpion morpion = GestionMorpions.getLastMorpion();
        stage.setOnCloseRequest(event1 -> {
            GestionMorpions.retirerMorpion(morpion);
        });
    }

    @FXML
    void updateSliderTaille(MouseEvent event) {
        labelTaille.setText((int) sliderTaille.getValue() + "x" + (int) sliderTaille.getValue());
    }

    @FXML
    void chooseSymbole(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        String id = button.getId();
        int indexJoueur = Integer.parseInt(id.substring(id.indexOf("r") + 1));
        GestionMorpions.setIndexJoueurCourantModif(indexJoueur - 1);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("choixSymbole.fxml"));
        SceneController.addModalWindow(fxmlLoader.load(), Modality.APPLICATION_MODAL, "Choix du symbole");
    }

    @FXML
    void updateJoueur(ActionEvent event) {
        Button source = (Button) event.getSource();
        String val = source.getText();
        if (val.equals("+")) {
            labelNbrJoueur.setText(String.valueOf(Integer.parseInt(labelNbrJoueur.getText()) + 1));
            GestionMorpions.ajouterJoueur();
            VBox vbox = new VBox();
            TextField textField = new TextField();
            textField.setPromptText("Nom du joueur");
            textField.setPrefWidth(100);
            Button button = new Button("Choix du symbole");
            button.setId("buttonJoueur" + labelNbrJoueur.getText());
            button.setOnAction(actionEvent -> {
                try {
                    chooseSymbole(actionEvent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            vbox.getChildren().add(textField);
            vbox.getChildren().add(button);
            flowPaneJoueur.getChildren().add(vbox);
        } else if (val.equals("-") && Integer.parseInt(labelNbrJoueur.getText()) > 2) {
            labelNbrJoueur.setText(String.valueOf(Integer.parseInt(labelNbrJoueur.getText()) - 1));
            GestionMorpions.retirerJoueur();
            flowPaneJoueur.getChildren().remove(flowPaneJoueur.getChildren().size() - 1);
        }

    }



}
