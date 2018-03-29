package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public abstract class AstNode {

    private SourceFilePosition sourceFilePosition;

    public AstNode(SourceFilePosition sourceFilePosition) {
        this.sourceFilePosition = sourceFilePosition;
    }

    public SourceFilePosition getSourceFilePosition() {
        return sourceFilePosition;
    }

    public abstract void acceptVisitor(NodeVisitor visitor);
}



