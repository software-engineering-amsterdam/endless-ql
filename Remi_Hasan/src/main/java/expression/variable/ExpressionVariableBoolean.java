package expression.variable;

import astvisitor.BaseASTVisitor;
import astvisitor.BoolValue;
import com.sun.jdi.BooleanValue;
import expression.Expression;
import expression.ExpressionVariable;

public class ExpressionVariableBoolean extends Expression<BoolValue> {

    Boolean value;

    ExpressionVariableBoolean(Boolean value) {
        this.value = value;
    }

    @Override
    public BoolValue accept(BaseASTVisitor<BoolValue> visitor) {
        return visitor.visit(this);
    }
}