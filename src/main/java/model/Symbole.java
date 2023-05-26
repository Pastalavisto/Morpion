package model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public interface Symbole {
    void setSymbole(Button button);
    void setCouleur(Color value);

    void setSymbole(Button button, String val);
}
