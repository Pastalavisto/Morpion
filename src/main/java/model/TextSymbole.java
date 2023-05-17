package model;

import javafx.scene.control.Button;

public class TextSymbole implements Symbole{
    private String text;

    public TextSymbole(String text) {
        this.text = text;
    }
    @Override
    public void setSymbole(Button button) {
        button.setText(text);
    }
}
