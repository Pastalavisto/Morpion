package models;

public class BotNiveau1 extends Bot {
    public BotNiveau1(String nom, Symbole symbole) {
        super(nom, symbole);
    }

    public BotNiveau1() {
        this("", null);
    }

    @Override
    public Coord jouer(Case[][] grille) {
        int x = (int) (Math.random() * grille.length);
        int y = (int) (Math.random() * grille.length);
        return new Coord(x, y);
    }
}
