package ast.model.expressions.unary.logical;

import ast.model.expressions.Expression;
import ast.model.expressions.unary.UnaryExpression;
import ast.visitors.ASTNodeVisitor;

public class Negation extends UnaryExpression{

    public Negation(Expression expression, MetaInformation metaInformation) {
        super(expression, metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
