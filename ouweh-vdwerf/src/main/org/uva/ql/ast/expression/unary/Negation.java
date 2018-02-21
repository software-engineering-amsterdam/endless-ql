package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;

public class Negation extends Expression {
    private final Expression expression;

    public Negation(Expression expression){
        this.expression = expression    ;
    }

    @Override
    public String toString() {
        return String.format("!( %s )", expression);
    }
}
