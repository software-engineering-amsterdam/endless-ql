package ql.ast.expressions.Binary;

import ql.ast.expressions.Expression;

public class NotEqual extends Binary {

    public NotEqual(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
