package model;

public class Joueur {
    private String nom;
    private Symbole symbole;

    public Joueur(String nom, Symbole symbole) {
        this.nom = nom;
        this.symbole = symbole;
    }

    public String getNom() {
        return nom;
    }

    public Symbole getSymbole() {
        return symbole;
    }

    public void setSymbole(Symbole symbole) {
        this.symbole = symbole;
    }
}
