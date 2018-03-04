package models.ast.elements.expressions.binary.arithmetics;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.binary.BinaryExpression;

public class Addition extends BinaryExpression {
    public Addition(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
