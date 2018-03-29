package gui.elements.widgets.spinner;

import gui.elements.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public abstract class Spinner<T> extends javafx.scene.control.Spinner<T> implements GUIWidget {

    public Spinner() {
        this.setEditable(true);
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.valueProperty().addListener(invalidationListener);
//        this.getEditor().textProperty().addListener(invalidationListener);
//        final Spinner spinner = this;
//        this.getEditor().setOnAction(e->{
//            invalidationListener.invalidated(spinner.valueProperty());
//        });
    }

    @Override
    public void setColor(String color) {
        this.setStyle("-fx-text-inner-color: " + color + ";");
    }

    @Override
    public void setFont(String font) {
        Font currentFont = this.getEditor().getFont();
        this.getEditor().setFont(Font.font(font, FontWeight.NORMAL, currentFont.getSize()));
    }

    @Override
    public void setFontSize(int fontSize) {
        Font currentFont = this.getEditor().getFont();
        this.getEditor().setFont(Font.font(currentFont.getFamily(), FontWeight.NORMAL, fontSize));
    }

    @Override
    public void setWidth(int width) {
        this.setPrefWidth(width);
    }
}

