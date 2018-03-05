package ast.model.expressions.binary.arithmetics;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class Division extends BinaryExpression {
    public Division(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
