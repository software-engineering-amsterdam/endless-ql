package gui.widgets;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableBoolean;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.List;

public class DropdownWidget extends ComboBox<String> implements WidgetInterface {

    private final List<String> options;
    private final Question question;

    public DropdownWidget(Question question, List<String> options) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
        this.options = options;
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableBoolean(null, Boolean.parseBoolean(this.valueProperty().getValue()));
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.BOOLEAN);
        }
    }

    @Override
    public void setExpression(String value) {
        this.setValue(value);
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.identifier));
            String text = value.isUndefined() ? "" : value.getBooleanValue().toString();
            this.setExpression(text);
        });
    }

    @Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.valueProperty().addListener(e -> {
            symbolTable.setExpression(question.identifier, getExpression(this, question.type));
        });
    }

    @Override
    public void setColor(String color) {

    }

    @Override
    public void setFont(String font) {

    }

    @Override
    public void setFontSize(int fontSize) {

    }

    @Override
    public void setWidth(int width) {

    }

    @Override
    public Node getNode() {
        return this;
    }
}
