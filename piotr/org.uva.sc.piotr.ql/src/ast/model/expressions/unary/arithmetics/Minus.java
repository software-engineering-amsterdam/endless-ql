package ast.model.expressions.unary.arithmetics;

import ast.model.expressions.Expression;
import ast.model.expressions.unary.UnaryExpression;
import ast.visitors.ASTNodeVisitor;

public class Minus extends UnaryExpression{
    public Minus(Expression expression, Integer startLine, Integer endLine) {
        super(expression, startLine, endLine);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
