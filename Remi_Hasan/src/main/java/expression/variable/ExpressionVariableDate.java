package expression.variable;

import astvisitor.DateValue;
import expression.ExpressionVariable;

public class ExpressionVariableDate extends ExpressionVariable<String> {

    ExpressionVariableDate(String value) {
        super(value);
    }

    @Override
    public DateValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }
}