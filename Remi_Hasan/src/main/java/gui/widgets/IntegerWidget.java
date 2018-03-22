package gui.widgets;

import javafx.scene.Node;
import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableInteger;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class IntegerWidget extends TextWidget {

    private final Question question;

    public IntegerWidget(Question question) {
        this.question = question;
        this.managedProperty().bind(this.visibleProperty());
        this.setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*"));
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableInteger(null, Integer.parseInt(getText()));
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.INTEGER);
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
            String text = value.isUndefined() ? "" : value.getIntValue().toString();
            this.setExpression(text);
        });
    }

    @Override
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
