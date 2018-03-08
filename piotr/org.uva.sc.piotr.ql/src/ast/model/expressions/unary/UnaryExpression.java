package ast.model.expressions.unary;

import ast.model.expressions.Expression;
import ast.visitors.ASTNodeVisitor;

abstract public class UnaryExpression extends Expression {

    private Expression expression;

    public UnaryExpression(Expression expression, MetaInformation metaInformation) {
        super(metaInformation);
        this.expression = expression;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

}
