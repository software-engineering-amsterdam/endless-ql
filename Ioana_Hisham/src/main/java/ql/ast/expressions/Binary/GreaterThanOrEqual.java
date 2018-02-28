package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class GreaterThanOrEqual extends Binary {

    public GreaterThanOrEqual(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
