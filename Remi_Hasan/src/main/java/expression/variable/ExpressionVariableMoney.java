package expression.variable;

import astvisitor.IASTVisitor;
import expression.ExpressionVariable;
import expression.ReturnType;

import java.math.BigDecimal;

public class ExpressionVariableMoney extends ExpressionVariable<BigDecimal> {

    public ExpressionVariableMoney(BigDecimal value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.MONEY;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
