package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
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

    @FXML
    private ColorPicker colorPicker;
    private static List<ImageView> images;

    private ChoixSymbole model;
    private ChoixSymbole ancienModel;

    private static final int TAILLE_IMAGE = 100;

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
    void changerRadio(ActionEvent event) {
        RadioButton radioButton = (RadioButton) event.getSource();
        if (radioButton == radioJoueur) {
            model.setRadioBouton("radioJoueur");
            textePerso.setDisable(true);
            radioJoueur.setSelected(true);
            radioPerso.setSelected(false);
        } else if (radioButton == radioPerso) {
            model.setRadioBouton("radioPerso");
            textePerso.setDisable(false);
            radioJoueur.setSelected(false);
            radioPerso.setSelected(true);
        } else {
            model.setRadioBouton("radioImage");
            textePerso.setDisable(true);
            radioJoueur.setSelected(false);
            radioPerso.setSelected(false);
        }
        flowPaneImage.getChildren().get(model.getIndexImage()).setStyle("-fx-border-color: transparent");

    }

    @FXML
    void changementTexte(KeyEvent event) {
        model.setTextePerso(textePerso.getText());
    }

    @FXML
    void changerColor(ActionEvent event) {
        ColorPicker colorPicker = (ColorPicker) event.getSource();
        model.setColor(colorPicker.getValue());
    }

    private void ajouterImagePane(String path) {
        Button button = new Button();
        button.setPrefSize(TAILLE_IMAGE, TAILLE_IMAGE);
        button.setOnAction(event -> selectImagePane(button));
        Image image = new Image(path);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(TAILLE_IMAGE);
        imageView.setFitWidth(TAILLE_IMAGE);
        button.setGraphic(imageView);
        flowPaneImage.getChildren().add(button);
        images.add(imageView);
        File file = new File(path);
        File folder = new File("src/main/resources/images");
        file.renameTo(new File(folder, file.getName()));
    }
    private void selectImagePane(Button button) {
        model.setRadioBouton("radioImage");
        flowPaneImage.getChildren().get(model.getIndexImage()).setStyle("-fx-border-color: transparent");
        button.setStyle("-fx-border-color: red");
        ImageView imageView =(ImageView) button.getGraphic();
        model.setIndexImage(images.indexOf(imageView));
        radioPerso.setSelected(false);
        radioJoueur.setSelected(false);
    }

    public static Image getImage(int index){
        return images.get(index).getImage();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model = ParametresPartie.getInstance().getJoueurCourant().getModel();
        ancienModel = new ChoixSymbole(model);
        for (ImageView imageView : images) {
            Button button = new Button();
            button.setPrefSize(TAILLE_IMAGE, TAILLE_IMAGE);
            button.setOnAction(event -> selectImagePane(button));
            button.setGraphic(imageView);
            flowPaneImage.getChildren().add(button);
            button.setStyle("-fx-border-color: transparent");
        }
        if (model.getRadioBouton().equals("radioJoueur")){
            radioJoueur.setSelected(true);
        }else if (model.getRadioBouton().equals("radioPerso")){
            radioPerso.setSelected(true);
            textePerso.setDisable(false);
        }else {
            radioJoueur.setSelected(false);
            radioPerso.setSelected(false);
            flowPaneImage.getChildren().get(model.getIndexImage()).setStyle("-fx-border-color: red");
        }
        textePerso.setText(model.getTextePerso());
        colorPicker.setValue(model.getColor());
    }

    public static void init(){
        images = new ArrayList<>();
        File folder = new File("src/main/resources/images");
        File[] files = folder.listFiles();
        for (File file : files) {
            Image image = new Image(file.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(TAILLE_IMAGE);
            imageView.setFitWidth(TAILLE_IMAGE);
            images.add(imageView);
        }
    }

    @FXML
    void valider(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @FXML
    void annuler(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        ancienModel.copyTo(model);
        stage.close();
    }

    @FXML
    void reinitialiser(ActionEvent event) {
        int index = model.getIndexImage();
        model.annuler();
        if (model.getRadioBouton().equals("radioJoueur")){
            flowPaneImage.getChildren().get(index).setStyle("-fx-border-color: transparent");
            radioJoueur.setSelected(true);
        }else if (model.getRadioBouton().equals("radioPerso")){
            flowPaneImage.getChildren().get(index).setStyle("-fx-border-color: transparent");
            radioPerso.setSelected(true);
            textePerso.setDisable(false);
        }else {
            radioJoueur.setSelected(false);
            radioPerso.setSelected(false);
        }
        textePerso.setText(model.getTextePerso());
        colorPicker.setValue(model.getColor());
    }
}
