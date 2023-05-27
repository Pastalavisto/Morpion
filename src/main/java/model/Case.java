package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.stage.Screen;

import java.io.IOException;

public class Case {
    public static int TAILLE_CASE = (int)Screen.getPrimary().getBounds().getHeight() / 10;
    private Coord coord;
    private Joueur joueur;
    private Morpion morpion;
    private Button button;
    public Case(int x, int y, Morpion morpion) {
        coord = new Coord(x, y);
        this.morpion = morpion;
    }

    int getX() {
        return coord.getX();
    }

    int getY() {
        return coord.getY();
    }

    public static void setTailleCase(int tailleCase) {
        TAILLE_CASE = tailleCase;
    }

    Joueur getJoueur() {
        return joueur;
    }

    void cocherCase(Joueur joueur) {
        this.joueur = joueur;
        getJoueur().getSymbole().setSymbole(button);
    }

    boolean estCocher() {
        return joueur != null;
    }


    public Button getButton() {
        button = new Button();
        button.setPrefSize(TAILLE_CASE, TAILLE_CASE);
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
