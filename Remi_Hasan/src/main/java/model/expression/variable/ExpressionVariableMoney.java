package model.expression.variable;

import evaluation.IASTVisitor;
import model.expression.ExpressionVariable;
import org.antlr.v4.runtime.Token;

import java.math.BigDecimal;

public class ExpressionVariableMoney extends ExpressionVariable<BigDecimal> {

    public ExpressionVariableMoney(Token start, String value) {
        super(start, new BigDecimal(value));
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExpressionVariableMoney;
    }
}
