package models.ast.elements.expressions.binary.arithmetics;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.binary.BinaryExpression;

public class Division extends BinaryExpression {
    public Division(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
