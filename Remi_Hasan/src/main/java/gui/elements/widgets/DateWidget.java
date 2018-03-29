package gui.elements.widgets;

import javafx.beans.InvalidationListener;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableDate;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class DateWidget extends DatePicker implements GUIWidget {

    public DateWidget() {
        // Do not allow typing, only date selecting using UI element
        this.getEditor().setDisable(true);
    }

    @Override
    public Expression getExpressionValue() {
        if(this.getValue() == null) {
            return new ExpressionVariableUndefined(ReturnType.DATE);
        }

        return new ExpressionVariableDate(this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.setValue(value.getDateValue());
    }

    @Override
    public Node getNode() {
        return this;
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
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
}
