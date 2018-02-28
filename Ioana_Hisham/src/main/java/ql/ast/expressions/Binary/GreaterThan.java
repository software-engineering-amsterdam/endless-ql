package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class GreaterThan extends Binary {

    public GreaterThan(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
