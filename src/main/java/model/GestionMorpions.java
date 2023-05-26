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
        joueurs.add(new Joueur("Joueur 2", null));
    }
    public static void setTaille(int taille) {
        GestionMorpions.taille = taille;
    }

    public static void setIndexJoueurCourantModif(int indexJoueurCourantModif) {
        GestionMorpions.indexJoueurCourantModif = indexJoueurCourantModif;
    }

    public static Joueur getJoueurCourant() {
        return joueurs.get(indexJoueurCourantModif);
    }
    public static int ajouterMorpion() {
        for (Joueur joueur : joueurs) {
            joueur.appliquerModel();
        }
        Morpion morpion = new Morpion(joueurs, taille,0);
        morpions.add(morpion);
        return morpions.size()-1;
    }

    public static Morpion getLastMorpion() {
        return morpions.get(morpions.size()-1);
    }

    public static void ajouterJoueur() {
        joueurs.add(new Joueur("Joueur " + (joueurs.size()+1), null));
    }

    public static void retirerJoueur() {
        retirerJoueur(joueurs.size()-1);
    }

    public static void retirerJoueur(int index) {
        joueurs.remove(index);
    }

    public static void retirerMorpion(Morpion morpion) {
        morpions.remove(morpion);
    }

    public static Joueur getJoueur(int i) {
        return joueurs.get(i);
    }
}
