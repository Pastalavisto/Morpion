package model;

import javafx.scene.control.Button;

public class Case {
    private int x;
    private int y;
    private Joueur joueur;

    private Morpion morpion;
    private Button button;
    public Case(int x, int y, Morpion morpion) {
        this.x = x;
        this.y = y;
        this.morpion = morpion;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
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

    void reset() {
        joueur = null;
    }

    public Button getButton() {
        button = new Button();
        button.setPrefSize(100, 100);
        button.setOnAction(event -> {
            morpion.jouer(x,y);
        });
        return button;
    }

    public void surbrillance() {
        button.setStyle("-fx-background-color: #00ff00");
    }


}
