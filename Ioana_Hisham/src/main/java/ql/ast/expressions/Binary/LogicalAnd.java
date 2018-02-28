package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class LogicalAnd extends Binary {

    public LogicalAnd(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
