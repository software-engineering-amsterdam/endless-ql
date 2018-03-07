package ast.model.expressions.unary.logical;

import ast.model.expressions.Expression;
import ast.model.expressions.unary.UnaryExpression;
import ast.visitors.ASTNodeVisitor;

public class Negation extends UnaryExpression{

    private Expression expression;

    public Negation(Expression expression, Integer startLine, Integer endLine) {
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
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
