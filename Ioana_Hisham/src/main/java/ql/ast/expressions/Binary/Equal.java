package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class Equal extends Binary {

    public Equal(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
