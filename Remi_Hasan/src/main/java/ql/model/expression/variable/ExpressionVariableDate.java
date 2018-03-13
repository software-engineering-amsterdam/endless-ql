package ql.model.expression.variable;

import ql.evaluation.IExpressionVisitor;
import ql.model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

public class ExpressionVariableDate extends ExpressionVariable<String> {

    public ExpressionVariableDate(Token start, String value) {
        super(start, value);
    }

    @Override
    public <T> T accept(IExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}