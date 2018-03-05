package ast.model.expressions.binary.comparision;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class GreaterEqual extends BinaryExpression {
    public GreaterEqual(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
