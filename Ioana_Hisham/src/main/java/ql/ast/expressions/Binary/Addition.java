package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class Addition extends Binary {

    public Addition(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
