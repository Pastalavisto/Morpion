package model;

import java.util.ArrayList;
import java.util.List;

public class ParametresPartie {
    public static List<Morpion> morpions;
    public List<Joueur> joueurs;
    public int taille;
    public static Joueur joueurCourant;

    private int indexPremierJoueur;

    private boolean indexPremierJoueurAlea;

    private static ParametresPartie instance;

    public static void init() {
        morpions = new ArrayList<>();
    }

    public static ParametresPartie getInstance() {
        if(instance == null) {
            instance = new ParametresPartie();
        }
        return instance;
    }

    public void setIndexPremierJoueur(int indexPremierJoueur) {
        this.indexPremierJoueur = indexPremierJoueur;
    }

    public int getIndexPremierJoueur() {
        return indexPremierJoueur;
    }
    public void setTaille(int taille) {
        this.taille = taille;
    }

    public static void setJoueurCourant(Joueur joueurCourant) {
        ParametresPartie.joueurCourant = joueurCourant;
    }

    public Joueur getJoueurCourant() {
        return joueurCourant;
    }
    public int ajouterMorpion() {
        for (Joueur joueur : joueurs) {
            joueur.appliquerModel();
            if (joueur.IsBot()){
                if (joueur.getNiveauBot() == 1){
                    Bot bot = new BotNiveau1();
                    setBot(joueurs.indexOf(joueur), bot);
                }
                else if (joueur.getNiveauBot() == 2){
                    Bot bot = new BotNiveau2();
                    setBot(joueurs.indexOf(joueur), bot);
                }
            }else{
                Joueur j1 = new Joueur(null, null);
                joueur.copyTo(j1);
                joueurs.set(joueurs.indexOf(joueur), j1);
            }
        }
        if (indexPremierJoueurAlea){
            indexPremierJoueur = (int) (Math.random() * joueurs.size());
        }
        Morpion morpion = new Morpion(joueurs, taille,getIndexPremierJoueur(),indexPremierJoueurAlea);
        morpions.add(morpion);
        return morpions.size()-1;
    }
    public ParametresPartie() {
        joueurs = new ArrayList<>();
        joueurs.add(new Joueur("Joueur 1", null));
        joueurs.add(new Joueur("Joueur 2", null));
    }

    public static Morpion getLastMorpion() {
        return morpions.get(morpions.size()-1);

    }
    public void setBot(int index, Bot bot) {
        joueurs.get(index).copyTo(bot);
        joueurs.set(index, bot);
    }

    public void ajouterJoueur() {
        joueurs.add(new Joueur("Joueur " + (joueurs.size()+1), null));
    }

    public void setIndexPremierJoueurAlea(boolean indexPremierJoueurAlea) {
        this.indexPremierJoueurAlea = indexPremierJoueurAlea;
    }

    public void retirerJoueur() {
        retirerJoueur(joueurs.size()-1);
    }

    public void retirerJoueur(int index) {
        joueurs.remove(index);
    }

    public static void retirerMorpion(Morpion morpion) {
        morpions.remove(morpion);
    }

    public Joueur getJoueur(int i) {
        return joueurs.get(i);
    }

}
