package classes.expressions;

import classes.CodeBlock;

public class BooleanExpression extends Expression  {
    public BooleanExpression(CodeBlock fragment) {
        super(fragment);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return null;
    }
}
