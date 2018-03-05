package ast.model.expressions.binary.logical;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class LogicalOr extends BinaryExpression {
    public LogicalOr(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
