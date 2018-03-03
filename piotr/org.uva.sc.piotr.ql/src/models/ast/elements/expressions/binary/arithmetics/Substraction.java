package models.ast.elements.expressions.binary.arithmetics;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.binary.BinaryExpression;

public class Substraction extends BinaryExpression {
    public Substraction(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
