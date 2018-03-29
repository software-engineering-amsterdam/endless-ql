package com.chariotit.uva.sc.qdsl.ast.ql.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public abstract class AstNode {

    private SourceFilePosition sourceFilePosition;

    public AstNode(SourceFilePosition sourceFilePosition) {
        this.sourceFilePosition = sourceFilePosition;
    }

    public abstract void acceptVisitor(NodeVisitor visitor);

    public SourceFilePosition getSourceFilePosition() {
        return sourceFilePosition;
    }
}

