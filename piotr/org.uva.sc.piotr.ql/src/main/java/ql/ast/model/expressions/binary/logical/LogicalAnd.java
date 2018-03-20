package ql.ast.model.expressions.binary.logical;

import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.binary.BinaryExpression;
import ql.ast.visitors.ASTNodeVisitor;

public class LogicalAnd extends BinaryExpression {
    public LogicalAnd(Expression leftSide, Expression rightSide, MetaInformation metaInformation) {
        super(leftSide, rightSide, metaInformation);
    }

    public LogicalAnd(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
