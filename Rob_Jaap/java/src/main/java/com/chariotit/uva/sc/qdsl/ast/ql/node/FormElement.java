package com.chariotit.uva.sc.qdsl.ast.ql.node;

import java.util.Set;

public abstract class FormElement extends AstNode {

    public abstract Set<String> getPrerequisites();
    public abstract Set<String> getProducedLabels();

    FormElement(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }
}
