package com.chariotit.uva.sc.qdsl.qls.ast.visitor;

import com.chariotit.uva.sc.qdsl.qls.ast.node.AstNode;

public abstract class NodeVisitor {

    public abstract void visitStringType(AstNode astNode);
    public abstract void visitMoneyType(AstNode astNode);
    public abstract void visitIntegerType(AstNode astNode);
    public abstract void visitBooleanType(AstNode astNode);
}
