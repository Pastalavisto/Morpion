package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import model.GestionMorpions;
import model.ImageSymbole;
import model.Symbole;

public class ChoixSymbole {

    @FXML
    void chooseImageInFile(ActionEvent event) {
        Button button = (Button) event.getSource();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        String path = fileChooser.showOpenDialog(button.getScene().getWindow()).getPath();
        ImageSymbole imageSymbole = new ImageSymbole(path);
        String id = button.getId();
        GestionMorpions.getJoueurCourant().setSymbole(imageSymbole);
    }

}
