package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;

public abstract class Property extends AstNode {

    public Property(SourceFilePosition filePosition) {
        super(filePosition);
    }
}
