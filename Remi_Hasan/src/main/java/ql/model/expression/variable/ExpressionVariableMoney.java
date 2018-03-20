package ql.model.expression.variable;

import ql.evaluation.IExpressionVisitor;
import ql.model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

import java.math.BigDecimal;

public class ExpressionVariableMoney extends ExpressionVariable<BigDecimal> {

    public ExpressionVariableMoney(Token start, String value) {
        super(start, new BigDecimal(value));
    }

    @Override
    public <T> T accept(IExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
