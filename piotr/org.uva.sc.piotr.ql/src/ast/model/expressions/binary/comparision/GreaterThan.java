package ast.model.expressions.binary.comparision;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class GreaterThan extends BinaryExpression {
    public GreaterThan(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
