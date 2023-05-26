package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageSymbole implements Symbole{
    private Image image;

    public ImageSymbole(String path) {
        this.image = new Image(path);
    }

    public ImageSymbole(Image image) {
        this.image = image;
    }
    @Override
    public void setSymbole(Button button) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Case.TAILLE_CASE-20);
        imageView.setFitWidth(Case.TAILLE_CASE-20);
        button.setGraphic(imageView);
    }

    @Override
    public void setCouleur(Color value) {
        System.out.println("Impossible de changer la couleur d'une image");
    }

    @Override
    public void setSymbole(Button button, String val) {
        System.out.println("Impossible de changer le nom d'une image");
    }
}
