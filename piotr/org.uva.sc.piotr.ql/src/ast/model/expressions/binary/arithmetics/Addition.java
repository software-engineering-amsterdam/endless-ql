package ast.model.expressions.binary.arithmetics;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class Addition extends BinaryExpression {
    public Addition(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
