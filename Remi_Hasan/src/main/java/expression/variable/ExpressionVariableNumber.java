package expression.variable;

import astvisitor.BaseASTVisitor;
import expression.ExpressionVariable;

public class ExpressionVariableNumber extends ExpressionVariable<Number> {

    ExpressionVariableNumber(Number value) {
        super(value);
    }

    @Override
    public Number accept(BaseASTVisitor<Number> visitor) {
        return visitor.visit(this);
    }
}
