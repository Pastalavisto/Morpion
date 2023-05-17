package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageSymbole implements Symbole{
    private Image image;

    public ImageSymbole(String path) {
        this.image = new Image(path);
    }

    @Override
    public void setSymbole(Button button) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        button.setGraphic(imageView);
    }
}
