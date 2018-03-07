package expression.variable;

import astvisitor.IASTVisitor;
import astvisitor.StringValue;
import expression.ExpressionVariable;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        super(value);
    }


    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return null;
    }
}