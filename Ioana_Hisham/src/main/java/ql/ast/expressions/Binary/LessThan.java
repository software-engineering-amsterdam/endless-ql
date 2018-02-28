package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class LessThan extends Binary {

    public LessThan(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
