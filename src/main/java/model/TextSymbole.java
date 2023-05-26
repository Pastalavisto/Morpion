package model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class TextSymbole implements Symbole{
    private String text;
    Color couleur;

    public TextSymbole(String text, Color color) {
        this.text = text;
        this.couleur = color;
    }
    @Override
    public void setSymbole(Button button) {
        button.setText(text);
        button.setTextFill(couleur);
    }

}
