package model;

import javafx.scene.paint.Color;

public class Joueur {
    private String nom;
    private Symbole symbole;

    private String texte;

    public Joueur(String nom, Symbole symbole) {
        this.texte = "";
        this.nom = nom;
        this.symbole = symbole;
    }

    public String getNom() {
        return nom;
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTexte() {
        return texte;
    }

    public void setSymbole(Symbole symbole) {
        this.symbole = symbole;
    }

    public void setCouleur(Color value) {
        this.symbole.setCouleur(value);
    }
}
