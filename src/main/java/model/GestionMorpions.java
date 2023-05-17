package model;

import java.util.ArrayList;
import java.util.List;

public class GestionMorpions {
    public static List<Morpion> morpions;
    public static List<Joueur> joueurs;
    public static int taille;
    public static int indexJoueurCourantModif;

    public static void init() {
        morpions = new ArrayList<>();
        joueurs = new ArrayList<>();
        joueurs.add(new Joueur("Joueur 1", null));
    }
    public static void setTaille(int taille) {
        GestionMorpions.taille = taille;
    }

    public static void setIndexJoueurCourantModif(int indexJoueurCourantModif) {
        GestionMorpions.indexJoueurCourantModif = indexJoueurCourantModif;
    }
    public static void modifierSymboleJoueur(int indexJoueur, Symbole symbole) {
        joueurs.get(indexJoueur).setSymbole(symbole);
    }

    public static Joueur getJoueurCourant() {
        return joueurs.get(indexJoueurCourantModif);
    }
    public static void ajouterMorpion() {
        morpions.add(new Morpion(joueurs, taille));
    }

    public static Morpion getLastMorpion() {
        return morpions.get(morpions.size()-1);
    }

    public static void ajouterJoueur() {
        joueurs.add(new Joueur("Joueur " + (joueurs.size()+1), null));
    }

    public static void retirerJoueur() {
        joueurs.remove(joueurs.size()-1);
    }

}
