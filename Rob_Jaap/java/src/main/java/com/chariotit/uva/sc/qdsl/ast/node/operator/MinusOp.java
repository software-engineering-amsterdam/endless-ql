package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class MinusOp extends Operator implements BinaryOperator, UnaryOperator {
    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMinusOp(this);
    }
}
