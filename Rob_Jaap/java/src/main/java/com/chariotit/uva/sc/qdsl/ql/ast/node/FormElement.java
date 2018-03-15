package com.chariotit.uva.sc.qdsl.ql.ast.node;

public abstract class FormElement extends AstNode {

    FormElement(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
