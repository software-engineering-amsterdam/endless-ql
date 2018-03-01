package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class MultiplyOp extends Operator implements BinaryOperator {
    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMultiplyOp(this);
    }
}
