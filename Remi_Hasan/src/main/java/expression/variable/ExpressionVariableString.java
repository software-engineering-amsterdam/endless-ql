package expression.variable;

import astvisitor.IASTVisitor;
import astvisitor.StringValue;
import expression.ExpressionVariable;
import expression.ReturnType;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.STRING;
    }


    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return null;
    }
}