package main.org.uva.ql.ast.expression;

public class ParameterGroup extends Expression {

    private final Expression expression;

    public ParameterGroup(Expression expression){
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return this.expression.toString();
    }
}
