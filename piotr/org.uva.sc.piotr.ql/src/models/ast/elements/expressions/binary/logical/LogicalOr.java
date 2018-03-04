package models.ast.elements.expressions.binary.logical;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.binary.BinaryExpression;

public class LogicalOr extends BinaryExpression {
    public LogicalOr(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
