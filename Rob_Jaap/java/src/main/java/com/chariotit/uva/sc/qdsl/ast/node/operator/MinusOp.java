package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class MinusOp extends Operator implements BinaryOperator, UnaryOperator {

    public MinusOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMinusOp(this);
    }
}
