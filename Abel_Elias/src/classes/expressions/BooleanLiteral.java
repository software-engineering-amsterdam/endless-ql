package classes.expressions;

import classes.CodeBlock;

public class BooleanLiteral extends Literal {

    private final Boolean value;

    public BooleanLiteral(CodeBlock code, Boolean value) {
        super(code);
        this.value = value;
    }

    public BooleanLiteral(Boolean value) {
        super(new CodeBlock(DUMMY_LINE, DUMMY_LINE));
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }


    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
