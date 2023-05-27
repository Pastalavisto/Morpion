package model;

import javafx.scene.paint.Color;

public class Joueur {
    private String nom;
    private Symbole symbole;
    private ChoixSymbole model;
    private Boolean isBot;
    private int niveauBot;
    private int score;
    public Joueur(String nom, Symbole symbole) {
        this.nom = nom;
        this.symbole = symbole;
        model = new ChoixSymbole();
        score = 0;
        isBot = false;
        niveauBot = 1;
    }

    public ChoixSymbole getModel() {
        return model;
    }

    public void setIsBot(Boolean isBot) {
        this.isBot = isBot;
        System.out.println("isBot = " + isBot);
    }

    public Boolean IsBot() {
        return isBot;
    }
    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public int getNiveauBot() {
        return niveauBot;
    }

    public void setNiveauBot(int niveauBot) {
        this.niveauBot = niveauBot;
    }

    public void setScore(int score) {
        this.score=score;
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

    public void copyTo(Joueur joueur) {
        joueur.setNom(nom);
        joueur.symbole = symbole;
        joueur.setModel(model);
        joueur.setIsBot(isBot);
    }

    private void setModel(ChoixSymbole model) {
        this.model = model;
    }
}
