package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class EqOp extends Operator implements BinaryOperator {
    public EqOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitEqOp(this);
    }
}
