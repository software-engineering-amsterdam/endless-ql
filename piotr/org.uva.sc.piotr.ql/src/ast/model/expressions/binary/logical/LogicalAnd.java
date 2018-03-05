package ast.model.expressions.binary.logical;

import ast.model.expressions.Expression;
import ast.model.expressions.binary.BinaryExpression;

public class LogicalAnd extends BinaryExpression {
    public LogicalAnd(Expression leftSide, Expression rightSide) {
        super(leftSide, rightSide);
    }
}
