package expression.variable;

import astvisitor.IASTVisitor;
import expression.ExpressionVariable;
import expression.ReturnType;

import java.math.BigDecimal;

public class ExpressionVariableMoney extends ExpressionVariable<BigDecimal> {

    public ExpressionVariableMoney(String value) {
        super(new BigDecimal(value));
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
