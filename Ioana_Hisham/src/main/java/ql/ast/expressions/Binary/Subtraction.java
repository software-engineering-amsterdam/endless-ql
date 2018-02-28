package ql.ast.expressions.Binary;

import ql.ast.expressions.Expression;

public class Subtraction extends Binary {

    public Subtraction(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
