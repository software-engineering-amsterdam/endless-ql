package models.ast.elements.expressions.binary.comparision;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.binary.BinaryExpression;

public class GreaterEqual extends BinaryExpression {
    public GreaterEqual(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
