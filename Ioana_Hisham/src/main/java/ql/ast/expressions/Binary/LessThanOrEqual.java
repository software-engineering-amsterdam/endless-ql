package ql.ast.expressions.Binary;

import ql.ast.expressions.Expression;

public class LessThanOrEqual extends Binary {

    public LessThanOrEqual(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
