package model;

import javafx.scene.paint.Color;

public class Joueur {
    private String nom;
    private Symbole symbole;
    private ChoixSymbole model;
    public Joueur(String nom, Symbole symbole) {
        this.nom = nom;
        this.symbole = symbole;
        model = new ChoixSymbole();
    }

    public ChoixSymbole getModel() {
        return model;
    }

    public String getNom() {
        return nom;
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public void appliquerModel() {
        if (model.getRadioBouton().equals("radioJoueur")) {
            symbole = new NomSymbole(nom, model.getColor());
        } else if (model.getRadioBouton().equals("radioPerso")) {
            symbole = new TextSymbole(model.getTextePerso(), model.getColor());
        } else {
            symbole = new ImageSymbole(model.getImage());
        }
    }
}
