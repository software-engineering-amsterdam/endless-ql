package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class NotEqual extends Binary {

    public NotEqual(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
