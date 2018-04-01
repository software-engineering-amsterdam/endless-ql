package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;

public abstract class SectionElement extends AstNode {

    public SectionElement(SourceFilePosition filePosition) {
        super(filePosition);
    }
}
