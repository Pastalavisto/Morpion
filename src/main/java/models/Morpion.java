package models;

import app.Application;
import controllers.MorpionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Morpion {
    FlowPane flowPaneJoueur;
    private final Case[][] grille;
    private final List<Joueur> joueurs;
    private int indexJoueurCourant;
    private int indexJoueurDepart;
    private final boolean aleatoire;
    private Label labelResultat;
    private boolean estFini;
    private final int taille;
    private GridPane gridPane;

    private int tailleCase;
    private MorpionController morpionController;

    private FinDePartie finDePartie;

    public Morpion(List<Joueur> joueurs, int taille, int indexJoueurDepart, boolean aleatoire, int tailleCase) {
        this.tailleCase = tailleCase;
        this.aleatoire = aleatoire;
        this.indexJoueurDepart = indexJoueurDepart;
        indexJoueurCourant = indexJoueurDepart;
        this.taille = taille;
        this.joueurs = joueurs;
        this.grille = new Case[taille][taille];
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                this.grille[i][j] = new Case(i, j, this);

            }
        }
    }

    public MorpionController getMorpionController() {
        return morpionController;
    }

    public void setMorpionController(MorpionController morpionController) {
        this.morpionController = morpionController;
    }

    public void setFlowPaneJoueur(FlowPane flowPaneJoueur) {
        this.flowPaneJoueur = flowPaneJoueur;
        int index = 0;
        for (Joueur joueur : joueurs) {
            VBox vBox = new VBox();
            vBox.getChildren().add(new Label(joueur.getNom()));
            vBox.getChildren().add(new Label(String.valueOf(joueur.getScore())));
            flowPaneJoueur.getChildren().add(vBox);
            if (index == indexJoueurCourant) {
                vBox.getChildren().get(0).setStyle("-fx-background-color: #00ff00");
            }
            index++;
        }

    }

    public FinDePartie getFinDePartie() {
        return finDePartie;
    }

    public void setLabelResultat(Label labelResultat) {
        this.labelResultat = labelResultat;
    }

    public GridPane getGridPane() {
        gridPane = new GridPane();
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                gridPane.add(grille[i][j].getButton(), i, j);
            }
        }
        return gridPane;
    }

    public Joueur getJoueurCourant() {
        return joueurs.get(indexJoueurCourant);
    }

    public void jouer(Coord coord) throws IOException {
        if (!this.grille[coord.getX()][coord.getY()].estCocher() && !estFini && !egalite()) {
            this.grille[coord.getX()][coord.getY()].cocherCase(getJoueurCourant());
            if (!estGagner() && !egalite()) {
                changerJoueur();
            } else {
                finDePartie = new FinDePartie(this);
                FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/view/FinDePartie.fxml"));
                Stage stage = SceneController.addModalWindow(fxmlLoader.load(), Modality.APPLICATION_MODAL, "Fin de partie");
                stage.setResizable(false);
            }
        }
        if (getJoueurCourant() instanceof Bot && !estFini) {
            this.jouer(((Bot) getJoueurCourant()).jouer(getGrille()));
        }
    }

    public void startIfBot() throws IOException {
        if (getJoueurCourant() instanceof Bot) {
            this.jouer(((Bot) getJoueurCourant()).jouer(getGrille()));
        }
    }

    private void changerJoueur() throws IOException {
        VBox vBox = (VBox) flowPaneJoueur.getChildren().get(indexJoueurCourant);
        vBox.getChildren().get(0).setStyle("-fx-background-color: #ffffff");
        if (indexJoueurCourant + 1 == joueurs.size()) {
            indexJoueurCourant = 0;
        } else {
            indexJoueurCourant++;
        }
        if (getJoueurCourant() instanceof Bot) {
            this.jouer(((Bot) getJoueurCourant()).jouer(getGrille()));
        }
        vBox = (VBox) flowPaneJoueur.getChildren().get(indexJoueurCourant);
        vBox.getChildren().get(0).setStyle("-fx-background-color: #00ff00");
    }

    public void reset() {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = new Case(i, j, this);
            }
        }
        VBox vbox = (VBox) flowPaneJoueur.getChildren().get(indexJoueurCourant);
        vbox.getChildren().get(0).setStyle("-fx-background-color: #ffffff");
        estFini = false;
        if (aleatoire) {
            indexJoueurDepart = (int) (Math.random() * joueurs.size());
        }
        indexJoueurCourant = indexJoueurDepart;
        vbox = (VBox) flowPaneJoueur.getChildren().get(indexJoueurCourant);
        vbox.getChildren().get(0).setStyle("-fx-background-color: #00ff00");
        labelResultat.setText("");

    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public Case[][] getGrille() {
        return grille;
    }

    public boolean egalite() {
        boolean egalite = true;
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (!grille[i][j].estCocher()) {
                    egalite = false;
                }
            }
        }
        if (egalite && !estFini) {
            estFini = true;
            griser();
            labelResultat.setText("Egalité");
        }
        return egalite;
    }

    public boolean estGagner() {
        boolean res = estGagnerLigne() || estGagnerColonne() || estGagnerDiagonale() || estGagnerDiagonaleInverse();
        if (res) {
            getJoueurCourant().setScore(getJoueurCourant().getScore() + 1);
            VBox vBox = (VBox) flowPaneJoueur.getChildren().get(indexJoueurCourant);
            Label label = (Label) vBox.getChildren().get(1);
            label.setText(String.valueOf(getJoueurCourant().getScore()));
            estFini = true;
            griser();
            labelResultat.setText(getJoueurCourant().getNom() + " a gagné");
        }
        return res;
    }

    private boolean estGagnerDiagonaleInverse() {
        boolean estGagner = true;
        for (int i = taille - 1; i > 0; i--) {
            if (!grille[i][taille - i - 1].estCocher() || grille[i][taille - i - 1].getJoueur() != grille[i - 1][taille - i].getJoueur()) {
                estGagner = false;
            }
        }
        if (estGagner) {
            for (int i = taille - 1; i >= 0; i--) {
                grille[i][taille - i - 1].surbrillance();
            }
        }
        return estGagner;
    }

    private boolean estGagnerDiagonale() {
        boolean estGagner = true;
        for (int i = 1; i < taille; i++) {
            if (!grille[i][i].estCocher() || grille[i][i].getJoueur() != grille[i - 1][i - 1].getJoueur()) {
                estGagner = false;
            }
        }
        if (estGagner) {
            for (int i = 0; i < taille; i++) {
                grille[i][i].surbrillance();
            }
        }
        return estGagner;
    }

    private boolean estGagnerColonne() {
        boolean estGagner = false;
        for (int i = 0; i < taille; i++) {
            boolean colonneGagnee = true;
            for (int j = 1; j < taille; j++) {
                if (!grille[i][j].estCocher() || grille[i][j].getJoueur() != grille[i][j - 1].getJoueur()) {
                    colonneGagnee = false;
                }
            }
            if (colonneGagnee) {
                estGagner = true;
                for (int j = 0; j < taille; j++) {
                    grille[i][j].surbrillance();
                }
            }
        }
        return estGagner;
    }

    public void griser() {
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                grille[i][j].griser();
            }
        }
    }

    private boolean estGagnerLigne() {
        boolean estGagner = false;
        for (int i = 0; i < taille; i++) {
            boolean ligneGagnee = true;
            for (int j = 1; j < taille; j++) {
                if (!grille[j][i].estCocher() || grille[j][i].getJoueur() != grille[j - 1][i].getJoueur()) {
                    ligneGagnee = false;
                }
            }
            if (ligneGagnee) {
                estGagner = true;
                for (int j = 0; j < taille; j++) {
                    grille[j][i].surbrillance();
                }
            }
        }
        return estGagner;
    }

    public void appliquerModif() {
        for (Joueur joueur : joueurs) {
            joueur.appliquerModel();
        }
    }

    public void changerNomJoueur(int i, String nom) {
        joueurs.get(i).setNom(nom);
        VBox vBox = (VBox) flowPaneJoueur.getChildren().get(i);
        Label label = (Label) vBox.getChildren().get(0);
        label.setText(nom);
    }

    public int getTailleCase() {
        return tailleCase;
    }

    public void setTailleCase(int tailleCase) {
        this.tailleCase = tailleCase;
    }
}
