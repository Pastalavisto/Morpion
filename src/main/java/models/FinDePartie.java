package models;

import controllers.FinDePartieController;

import java.io.IOException;
import java.util.List;

public class FinDePartie {

    Morpion morpion;

    public FinDePartie(Morpion morpion) {
        this.morpion = morpion;
        FinDePartieController.setModel(this);
    }

    public void rejouer() throws IOException {
        morpion.getMorpionController().reset();
    }

    public void close() {
        morpion.getMorpionController().close();
    }

    public List<Joueur> getJoueurs() {
        return morpion.getJoueurs();
    }

    public void appliquerModif() {
        morpion.appliquerModif();
    }

    public void changerNomJoueur(int i, String nom) {
        morpion.changerNomJoueur(i, nom);
    }
}
