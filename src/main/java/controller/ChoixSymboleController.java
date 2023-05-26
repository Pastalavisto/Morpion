package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ChoixSymboleController implements Initializable {

    @FXML
    private FlowPane flowPaneImage;

    @FXML
    private RadioButton radioJoueur;

    @FXML
    private RadioButton radioPerso;

    @FXML
    private TextField textePerso;

    private static List<ImageView> images;

    @FXML
    void ajouterImagePane(ActionEvent event) {
        Button button = (Button) event.getSource();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        try {
            String path = fileChooser.showOpenDialog(button.getScene().getWindow()).getPath();
            ajouterImagePane(path);
        } catch (NullPointerException e) {
            System.out.println("Aucune image selectionnée");
        }
    }

    @FXML
    void changeRadio(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        if (radioButton == radioJoueur) {
            GestionMorpions.getJoueurCourant().setTexte("");
            textePerso.setDisable(true);
            radioJoueur.setSelected(true);
            radioPerso.setSelected(false);
            GestionMorpions.getJoueurCourant().setSymbole(new NomSymbole(GestionMorpions.getJoueurCourant().getNom()));
        } else {
            textePerso.setDisable(false);
            GestionMorpions.getJoueurCourant().setTexte(textePerso.getText());
            GestionMorpions.getJoueurCourant().setSymbole(new TextSymbole(textePerso.getText()));
            radioJoueur.setSelected(false);
            radioPerso.setSelected(true);
        }
        for (int i = 0; i < flowPaneImage.getChildren().size(); i++) {
            flowPaneImage.getChildren().get(i).setStyle("-fx-border-color: transparent");
        }
    }

    @FXML
    void changementTexte(KeyEvent event) {
        GestionMorpions.getJoueurCourant().setTexte(textePerso.getText());
        GestionMorpions.getJoueurCourant().setSymbole(new TextSymbole(textePerso.getText()));
    }

    private void ajouterImagePane(String path) {
        Button button = new Button();
        button.setPrefSize(Case.TAILLE_CASE, Case.TAILLE_CASE);
        button.setOnAction(event -> selectImagePane(button));
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Case.TAILLE_CASE);
        imageView.setFitWidth(Case.TAILLE_CASE);
        button.setGraphic(imageView);
        flowPaneImage.getChildren().add(button);
        images.add(imageView);
        File file = new File(path);
        File folder = new File("src/main/resources/images");
        file.renameTo(new File(folder, file.getName()));
    }
    private void selectImagePane(Button button) {
        for (int i = 0; i < flowPaneImage.getChildren().size(); i++) {
            flowPaneImage.getChildren().get(i).setStyle("-fx-border-color: transparent");
        }
        button.setStyle("-fx-border-color: red");
        ImageView imageView =(ImageView) button.getGraphic();
        GestionMorpions.getJoueurCourant().setSymbole(new ImageSymbole(imageView.getImage()));
        radioPerso.setSelected(false);
        radioJoueur.setSelected(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textePerso.setText(GestionMorpions.getJoueurCourant().getTexte());
        for (ImageView imageView : images) {
            Button button = new Button();
            button.setPrefSize(Case.TAILLE_CASE, Case.TAILLE_CASE);
            button.setOnAction(event -> selectImagePane(button));
            button.setGraphic(imageView);
            flowPaneImage.getChildren().add(button);
            button.setStyle("-fx-border-color: transparent");
        }
    }

    public static void init(){
        images = new ArrayList<>();
        File folder = new File("src/main/resources/images");
        File[] files = folder.listFiles();
        for (File file : files) {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(Case.TAILLE_CASE);
            imageView.setFitWidth(Case.TAILLE_CASE);
            images.add(imageView);
        }
    }

    @FXML
    void fermer(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

}
