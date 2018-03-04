package models.ast.elements.expressions.binary.arithmetics;

import models.ast.elements.expressions.Expression;
import models.ast.elements.expressions.binary.BinaryExpression;

public class Multiplication extends BinaryExpression {
    public Multiplication(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
