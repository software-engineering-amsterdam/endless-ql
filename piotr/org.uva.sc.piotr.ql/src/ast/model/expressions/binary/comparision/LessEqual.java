package ast.model.expressions.binary.comparision;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class LessEqual extends BinaryExpression {
    public LessEqual(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
