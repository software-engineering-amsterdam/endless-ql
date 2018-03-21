package gui.widgets;

import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class TextWidget extends TextField implements WidgetInterface{

    @Override
    public void setColor(String color) {
        this.setStyle("-fx-label-inner-color: " + color + ";");
    }

    @Override
    public void setFont(String font) {
        Font currentFont = this.getFont();
        this.setFont(javafx.scene.text.Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
    }

    @Override
    public void setFontSize(int fontSize) {
        javafx.scene.text.Font currentFont = this.getFont();
        this.setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, fontSize));
    }

    @Override
    public void setWidth(int width) {
        this.setPrefWidth(width);
    }
}
