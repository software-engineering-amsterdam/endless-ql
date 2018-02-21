package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;
import main.org.uva.ql.visitor.ExpressionVisitor;

public class BooleanLiteral extends Expression {
    private final Boolean value;

    public BooleanLiteral (String value){
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
