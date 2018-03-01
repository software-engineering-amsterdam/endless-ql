package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public abstract class AstNode {

    public abstract void acceptVisitor(NodeVisitor visitor);
}

