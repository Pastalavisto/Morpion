package model;

public abstract class Bot extends Joueur{
    public Bot(String nom, Symbole symbole) {
        super(nom, symbole);
    }


    public abstract Coord jouer(Case[][] grille);
}
