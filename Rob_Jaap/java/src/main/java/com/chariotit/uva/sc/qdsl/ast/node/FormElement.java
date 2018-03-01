package com.chariotit.uva.sc.qdsl.ast.node;

public abstract class FormElement extends AstNode {

    FormElement(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
