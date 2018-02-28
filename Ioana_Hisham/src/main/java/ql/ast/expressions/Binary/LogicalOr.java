package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class LogicalOr extends Binary {

    public LogicalOr(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
