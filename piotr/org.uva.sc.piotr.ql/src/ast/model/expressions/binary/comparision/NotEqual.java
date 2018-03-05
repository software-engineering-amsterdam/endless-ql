package ast.model.expressions.binary.comparision;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class NotEqual extends BinaryExpression {
    public NotEqual(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
