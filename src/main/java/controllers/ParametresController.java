package controllers;

import app.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import models.Morpion;
import models.ParametresPartie;
import models.SceneController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ParametresController implements Initializable {

    @FXML
    private Slider sliderTaille;

    @FXML
    private Label labelTaille;

    @FXML
    private FlowPane flowPaneJoueur;

    @FXML
    private Label labelNbrJoueur;

    private ParametresPartie model;

    @FXML
    private RadioButton aleatoire;

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
            model.getJoueur(i).setNom(nom);
        }
        model.setTaille(taille);
        int index = model.ajouterMorpion((int) (Screen.getPrimary().getBounds().getHeight() / (taille * 2)));
        Morpion morpion = ParametresPartie.getLastMorpion();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/view/Morpion.fxml"));
        Stage stage = SceneController.addModalWindow(fxmlLoader.load(), Modality.WINDOW_MODAL, "Morpion numéro " + (index + 1));
        stage.setResizable(false);

        stage.setOnCloseRequest(event1 -> {
            ParametresPartie.retirerMorpion(morpion);
        });
        morpion.startIfBot();
        model.reset();
    }

    @FXML
    void updateSliderTaille(MouseEvent event) {
        labelTaille.setText((int) sliderTaille.getValue() + "x" + (int) sliderTaille.getValue());
    }

    @FXML
    void chooseSymbole(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String id = menuItem.getId();
        int indexJoueur = Integer.parseInt(id.substring(id.indexOf("r") + 1));
        ParametresPartie.setJoueurCourant(model.getJoueur(indexJoueur - 1));
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/view/ChoixSymbole.fxml"));
        Stage stage = SceneController.addModalWindow(fxmlLoader.load(), Modality.APPLICATION_MODAL, "Choix du symbole");
        stage.setResizable(false);
    }

    @FXML
    void updateJoueur(ActionEvent event) {
        Button source = (Button) event.getSource();
        String val = source.getText();
        if (val.equals("+")) {
            labelNbrJoueur.setText(String.valueOf(Integer.parseInt(labelNbrJoueur.getText()) + 1));
            model.ajouterJoueur();
            VBox vbox = new VBox();
            TextField textField = new TextField();
            textField.setPromptText("Nom du joueur");
            textField.setText("Joueur " + labelNbrJoueur.getText());
            textField.setPrefWidth(100);
            MenuButton button = new MenuButton("Options du joueur");
            MenuItem menuItem = new MenuItem("Choix du symbole");
            CheckMenuItem checkMenuItem = new CheckMenuItem("Ordinateur");
            checkMenuItem.setId("joueur" + labelNbrJoueur.getText());
            checkMenuItem.setOnAction(actionEvent -> {
                setBot(actionEvent);
            });
            Menu bot = new Menu("Niveau de l'ordinateur");
            RadioMenuItem radioMenuItem1 = new RadioMenuItem("Niveau 1");
            radioMenuItem1.setId("joueur" + labelNbrJoueur.getText());
            RadioMenuItem radioMenuItem2 = new RadioMenuItem("Niveau 2");
            radioMenuItem2.setId("joueur" + labelNbrJoueur.getText());
            radioMenuItem1.setSelected(true);
            radioMenuItem1.setOnAction(actionEvent -> {
                setNiveauBot(actionEvent);
            });
            radioMenuItem2.setOnAction(actionEvent -> {
                setNiveauBot(actionEvent);
            });
            bot.getItems().addAll(radioMenuItem1, radioMenuItem2);
            RadioMenuItem premierJoueur = new RadioMenuItem("Premier joueur");
            premierJoueur.setId("joueur" + labelNbrJoueur.getText());
            premierJoueur.setOnAction(actionEvent -> {
                setPremierJoueur(actionEvent);
            });
            button.getItems().addAll(menuItem, checkMenuItem, bot, premierJoueur);
            menuItem.setId("buttonJoueur" + labelNbrJoueur.getText());
            menuItem.setOnAction(actionEvent -> {
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
            model.retirerJoueur();
            flowPaneJoueur.getChildren().remove(flowPaneJoueur.getChildren().size() - 1);
        }

    }


    @FXML
    void setPremierJoueur(ActionEvent event) {
        if (event.getSource() instanceof RadioMenuItem radioMenuItem) {
            String id = radioMenuItem.getId();
            int indexJoueur = Integer.parseInt(id.substring(id.indexOf("r") + 1));
            if (radioMenuItem.isSelected()) {
                model.setIndexPremierJoueur(indexJoueur - 1);
                for (int i = 0; i < flowPaneJoueur.getChildren().size(); i++) {
                    VBox vbox = (VBox) flowPaneJoueur.getChildren().get(i);
                    MenuButton button = (MenuButton) vbox.getChildren().get(1);
                    RadioMenuItem radioMenuItem2 = (RadioMenuItem) button.getItems().get(3);
                    radioMenuItem2.setSelected(false);
                }
                radioMenuItem.setSelected(true);
            }
            model.setIndexPremierJoueurAlea(false);
            aleatoire.setSelected(false);
        } else {
            aleatoire.setSelected(true);
            for (int i = 0; i < flowPaneJoueur.getChildren().size(); i++) {
                VBox vbox = (VBox) flowPaneJoueur.getChildren().get(i);
                MenuButton button = (MenuButton) vbox.getChildren().get(1);
                RadioMenuItem radioMenuItem2 = (RadioMenuItem) button.getItems().get(3);
                radioMenuItem2.setSelected(false);
            }
            model.setIndexPremierJoueurAlea(true);
        }


    }


    @FXML
    void setNiveauBot(ActionEvent event) {
        RadioMenuItem radioMenuItem = (RadioMenuItem) event.getSource();
        String id = radioMenuItem.getId();
        int indexJoueur = Integer.parseInt(id.substring(id.indexOf("r") + 1));
        VBox vbox = (VBox) flowPaneJoueur.getChildren().get(indexJoueur - 1);
        MenuButton button = (MenuButton) vbox.getChildren().get(1);
        Menu menu = (Menu) button.getItems().get(2);
        RadioMenuItem radioMenuItem1 = (RadioMenuItem) menu.getItems().get(0);
        RadioMenuItem radioMenuItem2 = (RadioMenuItem) menu.getItems().get(1);
        if (radioMenuItem1 == radioMenuItem) {
            radioMenuItem2.setSelected(false);
            radioMenuItem1.setSelected(true);
            model.getJoueur(indexJoueur - 1).setNiveauBot(1);
        } else {
            radioMenuItem2.setSelected(true);
            radioMenuItem1.setSelected(false);
            model.getJoueur(indexJoueur - 1).setNiveauBot(2);
        }
    }

    @FXML
    void setBot(ActionEvent event) {
        CheckMenuItem checkMenuItem = (CheckMenuItem) event.getSource();
        String id = checkMenuItem.getId();
        int indexJoueur = Integer.parseInt(id.substring(id.indexOf("r") + 1));
        model.getJoueur(indexJoueur - 1).setIsBot(checkMenuItem.isSelected());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        model = ParametresPartie.getInstance(this);
    }

    public void reset() {
        for (int i = 0; i < flowPaneJoueur.getChildren().size(); i++) {
            VBox vbox = (VBox) flowPaneJoueur.getChildren().get(i);
            MenuButton button = (MenuButton) vbox.getChildren().get(1);
            RadioMenuItem radioMenuItem2 = (RadioMenuItem) button.getItems().get(3);
            radioMenuItem2.setSelected(false);
            CheckMenuItem checkMenuItem = (CheckMenuItem) button.getItems().get(1);
            checkMenuItem.setSelected(false);
            TextField textField = (TextField) vbox.getChildren().get(0);
            textField.setText("Joueur " + (i + 1));
            model.setIndexPremierJoueur(0);
            aleatoire.setSelected(false);
        }
    }
}
