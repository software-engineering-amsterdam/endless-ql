package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.node.AstNode;

public abstract class Operator extends AstNode {

    public Operator(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
