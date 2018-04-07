package ql.ast.model.expressions.unary.arithmetics;

import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.unary.UnaryExpression;
import ql.ast.visitors.ASTNodeVisitor;

public class Minus extends UnaryExpression{
    public Minus(Expression expression, MetaInformation metaInformation) {
        super(expression, metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
