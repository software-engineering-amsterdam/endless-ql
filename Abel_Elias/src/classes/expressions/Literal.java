package classes.expressions;

import classes.CodeBlock;

public abstract class Literal extends Expression {

    protected final static int DUMMY_LINE = -1;

    public Literal(CodeBlock code) {
        super(code);
    }

    @Override
    public abstract <T> T accept(ExpressionVisitor<T> visitor);

}