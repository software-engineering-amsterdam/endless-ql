package com.chariotit.uva.sc.qdsl.ast.ql.node;

public abstract class FormElement extends AstNode {

    FormElement(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
