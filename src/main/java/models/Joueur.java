package models;

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

    private void setModel(ChoixSymbole model) {
        this.model = model;
    }

    public void setIsBot(Boolean isBot) {
        this.isBot = isBot;
    }

    public Boolean IsBot() {
        return isBot;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNiveauBot() {
        return niveauBot;
    }

    public void setNiveauBot(int niveauBot) {
        this.niveauBot = niveauBot;
    }

    public Symbole getSymbole() {
        return symbole;
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
}
