package ql.ast.expressions.Binary;

import ql.ast.expressions.Expression;

public class Division extends Binary {

    public Division(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }
}
