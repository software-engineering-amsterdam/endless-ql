package gui.widgets;

import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableInteger;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class SpinnerIntegerWidget extends SpinnerWidget<Integer>{

    public SpinnerIntegerWidget(Question question){
        super(question);
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableInteger(null, this.getValueFactory().getValue());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.DECIMAL);
        }
    }

    @Override
    public void setExpression(String value) {
        this.getValueFactory().setValue(Integer.parseInt(value));
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
            String text = value.isUndefined() ? "" : value.getIntValue().toString();
            this.setExpression(text);
        });
    }
}
