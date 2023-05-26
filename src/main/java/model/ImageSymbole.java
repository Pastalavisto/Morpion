package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageSymbole implements Symbole {
    private final Image image;

    public ImageSymbole(String path) {
        this.image = new Image(path);
    }

    public ImageSymbole(Image image) {
        this.image = image;
    }

    @Override
    public void setSymbole(Button button) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(Case.TAILLE_CASE - 20);
        imageView.setFitWidth(Case.TAILLE_CASE - 20);
        button.setGraphic(imageView);
    }

}
