package gui.widgets;

import ql.analysis.SymbolTable;
import ql.evaluation.ExpressionEvaluator;
import ql.evaluation.value.Value;
import ql.model.Question;
import ql.model.expression.Expression;
import ql.model.expression.ReturnType;
import ql.model.expression.variable.ExpressionVariableMoney;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.math.BigDecimal;

public class SpinnerMoneyWidget extends SpinnerWidget<Double>{

    public SpinnerMoneyWidget(Question question){
        super(question);
        this.managedProperty().bind(this.visibleProperty());
        this.getEditor().setTextFormatter(WidgetUtils.createTextFormatter("-?\\d*(\\.\\d{0,2})?"));
    }

    @Override
    public Expression getExpression() {
        try{
            return new ExpressionVariableMoney(null, this.getValueFactory().getValue().toString());
        } catch(IllegalArgumentException e){
            return new ExpressionVariableUndefined(null, ReturnType.DECIMAL);
        }
    }

    @Override
    public void setExpression(String value) {
        try{
            this.getValueFactory().setValue(new BigDecimal(value).doubleValue());
        } catch(NumberFormatException e){
            this.getValueFactory().setValue(new BigDecimal("0.0").doubleValue());
        }
    }

    @Override
    public void addComputedListener(SymbolTable symbolTable, ExpressionEvaluator expressionEvaluator) {
        symbolTable.addListener(e -> {
            Value value = expressionEvaluator.visit(symbolTable.getExpression(question.identifier));
            String text = value.isUndefined() ? "" : value.getMoneyValue().toString();
            this.setExpression(text);
        });
    }
}
