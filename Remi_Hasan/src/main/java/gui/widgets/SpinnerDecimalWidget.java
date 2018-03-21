package gui.widgets;

import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableDecimal;
import ql.model.expression.variable.ExpressionVariableUndefined;

public class SpinnerDecimalWidget extends SpinnerWidget<Double>{

    public SpinnerDecimalWidget(Question question){
        super(question);
        this.managedProperty().bind(this.visibleProperty());
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d*)?"));
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableDecimal(null, this.getValueFactory().getValue());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.DECIMAL);
        }
    }

    @Override
    public void setExpression(String value) {
        this.getValueFactory().setValue(Double.parseDouble(value));
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.name));
            String text = value.isUndefined() ? "" : value.getDecimalValue().toString();
            this.setExpression(text);
        });
    }
}
