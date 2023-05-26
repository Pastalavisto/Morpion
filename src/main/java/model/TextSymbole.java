package model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class TextSymbole implements Symbole{
    private String text;
    Color couleur;

    public TextSymbole(String text) {
        this.text = text;
        this.couleur = Color.BLACK;
    }
    @Override
    public void setSymbole(Button button) {
        button.setText(text);
        button.setTextFill(couleur);
    }

    @Override
    public void setCouleur(Color value) {
        this.couleur = value;
    }

    @Override
    public void setSymbole(Button button, String val) {
        this.text = val;
        button.setText(text);
        button.setTextFill(couleur);
    }
}
