package ql.ast.expressions.Binary;

import ql.ast.expressions.Expression;

public class GreaterThan extends Binary {

    public GreaterThan(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
