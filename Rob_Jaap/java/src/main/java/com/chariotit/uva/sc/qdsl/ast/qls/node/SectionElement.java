package com.chariotit.uva.sc.qdsl.ast.qls.node;

public abstract class SectionElement extends AstNode {

    public SectionElement(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
