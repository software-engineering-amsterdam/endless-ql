package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class PlusOp extends Operator implements BinaryOperator, UnaryOperator {
    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitPlusOp(this);
    }
}
