package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;

public class Multiplication extends Binary {

    public Multiplication(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
