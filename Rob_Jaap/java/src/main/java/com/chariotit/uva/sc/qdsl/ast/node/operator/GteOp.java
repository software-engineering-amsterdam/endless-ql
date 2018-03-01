package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class GteOp extends Operator implements BinaryOperator {
    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitGteOp(this);
    }
}
