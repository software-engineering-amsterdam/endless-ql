package gui.widgets;

import javafx.scene.Node;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableString;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class StringWidget extends TextWidget {

    private final Question question;

    public StringWidget(Question question) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableString(null, getText());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.STRING);
        }
    }

    @Override
    public void setExpression(String value) {
        this.setText(value);
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.identifier));
            String text = value.isUndefined() ? "" : value.getStringValue().toString();
            this.setExpression(text);
        });
    }@Override
    public void addNonComputedListener(SymbolTable symbolTable) {
        this.textProperty().addListener(e -> {
            symbolTable.setExpression(question.identifier, getExpression(this, question.type));
        });
    }

    @Override
    public Node getNode() {
        return this;
    }
}
