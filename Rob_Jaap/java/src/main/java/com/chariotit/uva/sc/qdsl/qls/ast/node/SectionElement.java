package com.chariotit.uva.sc.qdsl.qls.ast.node;

public abstract class SectionElement extends AstNode {

    public SectionElement(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
