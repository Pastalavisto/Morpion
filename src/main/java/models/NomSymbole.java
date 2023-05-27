package models;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class NomSymbole implements Symbole {
    Color couleur;
    private final String nom;

    public NomSymbole(String text, Color color) {
        this.nom = text;
        this.couleur = color;
    }

    @Override
    public void setSymbole(Button button, int taille) {
        button.setText(nom);
        button.setTextFill(couleur);
    }
}
