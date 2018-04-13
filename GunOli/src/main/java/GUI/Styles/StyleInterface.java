package GUI.Styles;

import javafx.scene.Node;

public interface StyleInterface {

    Node node();
    void setColor(String color);
    void setFont(String font);
    void setFontSize(int size);
    void setWidth(int width);
}
