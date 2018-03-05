package ast.model.expressions.binary.comparision;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class LessThan extends BinaryExpression {
    public LessThan(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
