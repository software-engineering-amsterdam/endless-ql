package ast.model.expressions.unary;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

abstract public class UnaryExpression extends Expression {

    private Expression expression;

    public UnaryExpression(Expression expression, Integer startLine, Integer endLine) {
        super(startLine, endLine);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {}
}
