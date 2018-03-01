package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class GtOp extends Operator implements BinaryOperator {

    public GtOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitGtOp(this);
    }
}
