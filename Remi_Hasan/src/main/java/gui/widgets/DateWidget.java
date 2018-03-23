package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableDate;
import ql.model.expression.variable.ExpressionVariableUndefined;
import qls.model.StyleSheet;

public class DateWidget extends DatePicker implements GUIWidget {

    private final String identifier;private final boolean computed;

    public DateWidget(String identifier, boolean computed) {
        this.identifier = identifier; this.computed = computed;
        // Do not allow typing, only date selecting using UI element
        this.getEditor().setDisable(true);
    }

    @Override
    public Expression getExpressionValue() {
        if(this.getValue() == null) {
            return new ExpressionVariableUndefined(null, ReturnType.DATE);
        }

        return new ExpressionVariableDate(null, this.getValue());
    }

    @Override
    public void setValue(Value value) {
        this.setValue(value.getDateValue());
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        if(!computed)
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
        if(computed) setValue(symbolTable.getValue(this.identifier)); else symbolTable.setExpression(identifier, this.getExpressionValue());
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
