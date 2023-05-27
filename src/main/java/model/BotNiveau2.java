package model;

public class BotNiveau2 extends Bot{
    public BotNiveau2(String nom, Symbole symbole) {
        super(nom, symbole);
    }

    public BotNiveau2() {
        this("",null);
    }
    @Override
    public Coord jouer(Case[][] grille) {
        return new Coord(1, 0);
    }
}
