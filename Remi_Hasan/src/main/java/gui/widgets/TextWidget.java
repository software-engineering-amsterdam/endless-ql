package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableString;

public class TextWidget extends TextField implements GUIWidget {

    public TextWidget() {
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Expression getExpressionValue() {
        return new ExpressionVariableString(null, this.getText());
    }

    @Override
    public void setValue(Value value) {
        this.setText(value.isUndefined() ? "" : value.getStringValue());
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setColor(String color) {
        this.setStyle("-fx-text-inner-color: " + color + ";");
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

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        this.textProperty().addListener(invalidationListener);
    }
}
