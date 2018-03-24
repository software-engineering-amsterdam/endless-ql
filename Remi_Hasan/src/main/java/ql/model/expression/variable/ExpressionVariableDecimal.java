package ql.model.expression.variable;

import ql.IQLVisitor;
import ql.model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

public class ExpressionVariableDecimal extends ExpressionVariable<Double> {

    public ExpressionVariableDecimal(Token start, Double value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
