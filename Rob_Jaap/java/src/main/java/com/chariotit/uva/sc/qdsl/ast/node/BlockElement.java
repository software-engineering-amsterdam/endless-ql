package com.chariotit.uva.sc.qdsl.ast.node;

public abstract class BlockElement extends FormElement {

    BlockElement(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
