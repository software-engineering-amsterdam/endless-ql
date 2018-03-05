package ast.model.expressions.binary.arithmetics;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class Multiplication extends BinaryExpression {
    public Multiplication(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
