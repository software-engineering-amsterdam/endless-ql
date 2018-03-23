package gui.widgets.textbox;

import gui.widgets.GUIWidget;
import javafx.beans.InvalidationListener;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import ql.analysis.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableString;
import qls.model.StyleSheet;

public class TextboxWidget extends TextField implements GUIWidget {

    private final String identifier;
    private final boolean computed;

    public TextboxWidget(String identifier, boolean computed) {
        this.identifier = identifier;
        this.computed = computed;
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
    public void setChangeListener(InvalidationListener invalidationListener) {
        if (!computed)
            this.textProperty().addListener(invalidationListener);
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }
}
