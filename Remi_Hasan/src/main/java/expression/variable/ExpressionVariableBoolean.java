package expression.variable;

import astvisitor.IASTVisitor;
import expression.Expression;

public class ExpressionVariableBoolean extends Expression {

    public Boolean value;

    public ExpressionVariableBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}