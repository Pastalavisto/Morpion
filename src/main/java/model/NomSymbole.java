package model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class NomSymbole implements Symbole{
    private String nom;
    Color couleur;
    public NomSymbole(String text) {
        this.nom = text;
        this.couleur = Color.BLACK;
        System.out.println("NomSymbole");
    }
    @Override
    public void setSymbole(Button button) {
        button.setText(nom);
        button.setTextFill(couleur);
    }

    @Override
    public void setCouleur(Color value) {
        this.couleur = value;

    }

    @Override
    public void setSymbole(Button button, String val) {
        this.nom = val;
        button.setText(nom);
        button.setTextFill(couleur);
    }
}
