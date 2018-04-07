package ql.ast.model.expressions.binary.comparision;

import ql.ast.model.expressions.Expression;
import ql.ast.model.expressions.binary.BinaryExpression;
import ql.ast.visitors.ASTNodeVisitor;

public class GreaterThan extends BinaryExpression {
    public GreaterThan(Expression leftSide, Expression rightSide, MetaInformation metaInformation) {
        super(leftSide, rightSide, metaInformation);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }}
