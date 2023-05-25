package model;

import javafx.scene.control.Button;

public class NomSymbole implements Symbole{
    private String nom;

    public NomSymbole(String text) {
        this.nom = text;
    }
    @Override
    public void setSymbole(Button button) {
        button.setText(nom);
    }
}
