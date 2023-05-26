package model;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class Morpion {
    private Case[][] grille;
    private List<Joueur> joueurs;
    private int indexJoueurCourant;

    Label nomJoueurLabel;
    private boolean estFini;
    private int taille;
    private GridPane gridPane;
    public Morpion(List<Joueur> joueurs, int taille) {
        this.taille = taille;
        this.joueurs = joueurs;
        this.grille = new Case[taille][taille];
        for(int i = 0; i < taille; i++) {
            for(int j = 0; j < taille; j++){
                this.grille[i][j] = new Case(i, j, this);
            }
        }
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        for(int i = 0; i < grille.length; i++) {
            for(int j = 0; j < grille[i].length; j++){
                gridPane.add(grille[i][j].getButton(), i, j);
            }
        }
        return gridPane;
    }
    public Joueur getJoueurCourant() {
        return joueurs.get(indexJoueurCourant);
    }
    public void jouer(int i, int j) {
        if(!this.grille[i][j].estCocher() && !estFini) {
            this.grille[i][j].cocherCase(getJoueurCourant());
            if (estGagner()) {
                System.out.println("Gagné");
                estFini = true;
            } else {
                changerJoueur();
            }
        }
    }

    private void changerJoueur() {
        if (indexJoueurCourant +1 == joueurs.size()) {
            indexJoueurCourant = 0;
        } else {
            indexJoueurCourant++;
        }
        nomJoueurLabel.setText("C'est à " + getJoueurCourant().getNom() + " de jouer");
    }
    public void reset() {
        for(int i = 0; i < grille.length; i++) {
            for(int j = 0; j < grille[i].length; j++){
                grille[i][j].reset();
            }
        }
    }

    public void setNomJoueurLabel(Label nomJoueurLabel) {
        this.nomJoueurLabel = nomJoueurLabel;
    }
    public Case [][] getGrille() {
        return grille;
    }

    public int getTaille() {
        return taille;
    }

    public boolean estGagner() {
        return estGagnerLigne() || estGagnerColonne() || estGagnerDiagonale() || estGagnerDiagonaleInverse();
    }
    private boolean estGagnerDiagonaleInverse() {
        boolean estGagner = true;
        for (int i = taille -1; i>0;i--){
            if (!grille[i][taille-i-1].estCocher() || grille[i][taille-i-1].getJoueur() != grille[i-1][taille-i].getJoueur()){
                estGagner = false;
            }
        }
        if (estGagner){
            System.out.println("diagonale inverse gagnée");
            for (int i = taille -1; i>=0;i--){
                grille[i][taille-i-1].surbrillance();
            }
        }
        return estGagner;
    }

    private boolean estGagnerDiagonale() {
        boolean estGagner = true;
        for (int i = 1; i<taille;i++){
            if (!grille[i][i].estCocher() || grille[i][i].getJoueur() != grille[i-1][i-1].getJoueur()){
                estGagner = false;
            }
        }
        if (estGagner){
            System.out.println("diagonale gagnée");
            for (int i = 0; i<taille;i++){
                grille[i][i].surbrillance();
            }
        }
        return estGagner;
    }

    private boolean estGagnerColonne() {
        boolean estGagner = false;
        for (int i = 0; i<taille;i++){
            boolean colonneGagnee = true;
            for (int j = 1; j<taille;j++){
                if (!grille[i][j].estCocher() || grille[i][j].getJoueur() != grille[i][j-1].getJoueur()){
                    colonneGagnee = false;
                }
            }
            if (colonneGagnee){
                estGagner = true;
                System.out.println("colonne "+i+" gagnée");
                for (int j = 0; j<taille;j++){
                    grille[i][j].surbrillance();
                }
            }
        }
        return estGagner;
    }

    private boolean estGagnerLigne() {
        boolean estGagner = false;
        for (int i = 0; i<taille;i++){
            boolean ligneGagnee = true;
            for (int j = 1; j<taille;j++){
                if (!grille[j][i].estCocher() || grille[j][i].getJoueur() != grille[j-1][i].getJoueur()){
                    ligneGagnee = false;
                }
            }
            if (ligneGagnee){
                estGagner = true;
                System.out.println("ligne "+i+" gagnée");
                for (int j = 0; j<taille;j++){
                    grille[j][i].surbrillance();
                }
            }
        }
        return estGagner;
    }

    public void surbrillance(int i, int j) {
        grille[i][j].surbrillance();
    }

}
