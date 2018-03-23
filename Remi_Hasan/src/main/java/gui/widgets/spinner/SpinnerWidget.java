package gui.widgets.spinner;

import gui.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.control.Spinner;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.evaluation.SymbolTable;
import qls.model.StyleSheet;

public abstract class SpinnerWidget<T> extends Spinner<T> implements GUIWidget {

    private final String identifier;
    private final boolean computed;

    public SpinnerWidget(String identifier, boolean computed) {
        this.identifier = identifier;
        this.computed = computed;
        this.setEditable(true);
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        if (!computed)
            this.valueProperty().addListener(invalidationListener);
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


    @Override
    public void update(SymbolTable symbolTable) {
        if (computed) setValue(symbolTable.getValue(this.identifier));
        else symbolTable.setExpression(identifier, this.getExpressionValue());
    }

    @Override
    public void update(StyleSheet styleSheet) {

    }

    @Override
    public Parent render() {
        return this;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}

