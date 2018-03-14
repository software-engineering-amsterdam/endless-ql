package ql.model.expression.variable;

import ql.evaluation.Binding;
import ql.evaluation.IExpressionVisitor;
import ql.model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

import java.util.List;

public class ExpressionVariableDecimal extends ExpressionVariable<Double> {

    public ExpressionVariableDecimal(Token start, Double value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IExpressionVisitor<T> visitor, List<Binding> bindings) {
        return visitor.visit(this, bindings);
    }
}
