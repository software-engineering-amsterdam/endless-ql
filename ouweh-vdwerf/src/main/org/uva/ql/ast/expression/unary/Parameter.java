package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;
import main.org.uva.ql.visitor.ExpressionVisitor;

public class Parameter extends Expression {
    private final String value;

    public Parameter(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
