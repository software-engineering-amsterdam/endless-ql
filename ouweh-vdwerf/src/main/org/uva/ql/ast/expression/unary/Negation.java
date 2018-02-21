package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;
import main.org.uva.ql.visitor.ExpressionVisitor;

public class Negation extends Expression {
    private final Expression expression;

    public Negation(Expression expression){
        this.expression = expression    ;
    }

    @Override
    public String toString() {
        return String.format("!( %s )", expression);
    }

    @Override
    public <T, C> T accept(ExpressionVisitor<T, C> visitor, C context) {
        return visitor.visit(this, context);
    }
}
