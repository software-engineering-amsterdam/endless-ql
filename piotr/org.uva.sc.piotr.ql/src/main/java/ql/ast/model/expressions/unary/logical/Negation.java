package ql.ast.model.expressions.unary.logical;

import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.unary.UnaryExpression;
import ql.ast.visitors.ASTNodeVisitor;

public class Negation extends UnaryExpression{

    public Negation(Expression expression, MetaInformation metaInformation) {
        super(expression, metaInformation);
    }

    public Negation(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
