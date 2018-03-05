package ast.model.expressions.binary.arithmetics;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class Subtraction extends BinaryExpression {
    public Subtraction(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
