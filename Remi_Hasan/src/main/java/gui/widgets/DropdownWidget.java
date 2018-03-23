package gui.widgets;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ql.analysis.SymbolTable;
import ql.evaluation.value.Value;
import ql.model.expression.Expression;
import ql.model.expression.variable.ExpressionVariableBoolean;
import qls.model.StyleSheet;

public class DropdownWidget extends ComboBox<String> implements GUIWidget {

    private final String identifier;private final boolean computed;
    private String color;
    private String font;
    private int fontSize;

    public DropdownWidget(String identifier, boolean computed, String falseLabel, String trueLabel) {
        this.identifier = identifier; this.computed = computed;
        this.setItems(FXCollections.observableArrayList(falseLabel, trueLabel));

        // Default to false
        this.getSelectionModel().select(0);
    }

    private void setCellFactory() {
        this.setCellFactory(param -> new ComboBoxListCell<>() {{
            setTextFill(Color.valueOf(color));
            setFont(Font.font(font));
            setFont(Font.font(fontSize));
        }});
    }

    @Override
    public void setChangeListener(InvalidationListener invalidationListener) {
        if(!computed)
            this.valueProperty().addListener(invalidationListener);
    }

    @Override
    public Expression getExpressionValue() {
        // Item at index 1 is always the true option, so check if that one is selected
        boolean isTrue = this.getSelectionModel().getSelectedIndex() == 1;
        return new ExpressionVariableBoolean(null, isTrue);
    }

    @Override
    public void setValue(Value value) {
        // Index 1 is true, index 0 is false
        this.getSelectionModel().select(value.getBooleanValue() ? 1 : 0);
    }

    @Override
    public void setColor(String color) {
        this.color = color;
        setCellFactory();
    }

    @Override
    public void setFont(String font) {
        this.font = font;
        setCellFactory();
    }

    @Override
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        setCellFactory();
    }

    @Override
    public void setWidth(int width) {
        this.setWidth((double) width);
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
