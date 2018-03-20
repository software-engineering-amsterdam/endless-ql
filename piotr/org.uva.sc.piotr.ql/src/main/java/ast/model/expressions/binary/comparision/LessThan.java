package ast.model.expressions.binary.comparision;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;
import ast.visitors.ASTNodeVisitor;

public class LessThan extends BinaryExpression {
    public LessThan(Expression leftSide, Expression rightSide, MetaInformation metaInformation) {
        super(leftSide, rightSide, metaInformation);
    }

    public LessThan(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }

    @Override
    public <T> T accept(ASTNodeVisitor<T> visitor) {
        return visitor.visit(this);
    }}
