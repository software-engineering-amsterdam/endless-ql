package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;

import java.util.Set;

public abstract class FormElement extends AstNode {

    public abstract Set<String> getPrerequisites();
    public abstract Set<String> getProducedLabels();

    FormElement(SourceFilePosition filePosition) {
        super(filePosition);
    }
}
