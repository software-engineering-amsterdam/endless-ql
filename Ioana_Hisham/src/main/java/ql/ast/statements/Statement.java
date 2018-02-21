package ql.ast.statements;

import ql.ast.Node;

public abstract class Statement extends Node {

    public Statement(int lineNumber) {
        super(lineNumber);
    }
}
