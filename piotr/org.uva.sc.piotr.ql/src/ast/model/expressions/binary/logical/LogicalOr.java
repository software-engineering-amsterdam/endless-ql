package ast.model.expressions.binary.logical;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;
import ast.visitors.ASTNodeVisitor;

public class LogicalOr extends BinaryExpression {
    public LogicalOr(Expression leftSide, Expression rightSide, MetaInformation metaInformation) {
        super(leftSide, rightSide, metaInformation);
    }

    @Override
    public void accept(ASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}
