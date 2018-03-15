package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class NotOp extends Operator implements UnaryOperator, BooleanOperator {

    public NotOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitNotOp(this);
    }
}
