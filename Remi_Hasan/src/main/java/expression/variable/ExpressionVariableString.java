package expression.variable;

import astvisitor.StringValue;
import expression.ExpressionVariable;

public class ExpressionVariableString extends ExpressionVariable<String> {

    ExpressionVariableString(String value) {
        super(value);
    }


    @Override
    public StringValue accept(BaseASTVisitor visitor) {
        return null;
    }
}