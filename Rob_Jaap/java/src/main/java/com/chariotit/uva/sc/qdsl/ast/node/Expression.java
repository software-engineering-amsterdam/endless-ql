package com.chariotit.uva.sc.qdsl.ast.node;

public abstract class Expression extends AstNode {

    Expression(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
