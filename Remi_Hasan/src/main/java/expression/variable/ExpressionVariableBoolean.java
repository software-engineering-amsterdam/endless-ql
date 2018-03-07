package expression.variable;

import astvisitor.BoolValue;
import expression.Expression;

public class ExpressionVariableBoolean extends Expression<Boolean> {

    public Boolean value;

    ExpressionVariableBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public BoolValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }
}