package ql.ast.expressions;

import ql.ast.Node;

public abstract class Expression extends Node {

    public Expression(int lineNumber) {
        super(lineNumber);
    }
}
