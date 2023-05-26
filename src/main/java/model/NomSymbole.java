package model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class NomSymbole implements Symbole{
    private String nom;
    Color couleur;
    public NomSymbole(String text, Color color) {
        this.nom = text;
        this.couleur = color;
    }
    @Override
    public void setSymbole(Button button) {
        button.setText(nom);
        button.setTextFill(couleur);
    }
}
