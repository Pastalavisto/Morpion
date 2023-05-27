package models;

import javafx.scene.control.Button;

import java.io.IOException;

public class Case {
    public int taille;
    private final Coord coord;
    private Joueur joueur;
    private final Morpion morpion;
    private Button button;

    public Case(int x, int y, Morpion morpion) {
        coord = new Coord(x, y);
        this.morpion = morpion;
        taille = morpion.getTailleCase();

    }

    int getX() {
        return coord.getX();
    }

    int getY() {
        return coord.getY();
    }

    Joueur getJoueur() {
        return joueur;
    }

    void cocherCase(Joueur joueur) {
        this.joueur = joueur;
        getJoueur().getSymbole().setSymbole(button, taille);
    }

    boolean estCocher() {
        return joueur != null;
    }


    public Button getButton() {
        button = new Button();
        button.setPrefSize(taille, taille);
        button.setOnAction(event -> {
            try {
                morpion.jouer(coord);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return button;
    }

    public void surbrillance() {
        button.setStyle("-fx-background-color: #00ff00");
    }


    public void griser() {
        button.setDisable(true);
    }
}
