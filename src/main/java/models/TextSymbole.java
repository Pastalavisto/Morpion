package models;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class TextSymbole implements Symbole {
    Color couleur;
    private final String text;

    public TextSymbole(String text, Color color) {
        this.text = text;
        this.couleur = color;
    }

    @Override
    public void setSymbole(Button button, int taille) {
        button.setText(text);
        button.setTextFill(couleur);
    }

}
