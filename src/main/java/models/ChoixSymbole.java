package models;

import controllers.ChoixSymboleController;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ChoixSymbole {

    private String radioBouton;
    private String textePerso;
    private Color color;
    private int indexImage;

    public ChoixSymbole() {
        this("radioJoueur", "", Color.BLACK, 0);
    }

    public ChoixSymbole(String radioBouton, String textePerso, Color color, int indexImage) {
        this.radioBouton = radioBouton;
        this.textePerso = textePerso;
        this.color = color;
        this.indexImage = indexImage;
    }

    public ChoixSymbole(ChoixSymbole choixSymbole) {
        this(choixSymbole.radioBouton, choixSymbole.textePerso, choixSymbole.color, choixSymbole.indexImage);
    }

    public String getRadioBouton() {
        return radioBouton;
    }

    public void setRadioBouton(String radioBouton) {
        this.radioBouton = radioBouton;
    }

    public String getTextePerso() {
        return textePerso;
    }

    public void setTextePerso(String textePerso) {
        this.textePerso = textePerso;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getIndexImage() {
        return indexImage;
    }

    public void setIndexImage(int indexImage) {
        this.indexImage = indexImage;
    }

    public Image getImage() {
        return ChoixSymboleController.getImage(indexImage);
    }

    public void annuler() {
        this.radioBouton = "radioJoueur";
        this.textePerso = "";
        this.color = Color.BLACK;
        this.indexImage = 0;
    }

    public void copyTo(ChoixSymbole model) {
        model.setRadioBouton(radioBouton);
        model.setTextePerso(textePerso);
        model.setColor(color);
        model.setIndexImage(indexImage);
    }
}
