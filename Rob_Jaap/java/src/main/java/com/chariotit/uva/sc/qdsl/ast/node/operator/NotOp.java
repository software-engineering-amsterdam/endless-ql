package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class NotOp extends Operator implements UnaryOperator {
    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitNotOp(this);
    }
}
