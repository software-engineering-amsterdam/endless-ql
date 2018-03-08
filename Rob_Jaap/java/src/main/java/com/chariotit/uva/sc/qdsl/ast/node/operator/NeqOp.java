package com.chariotit.uva.sc.qdsl.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class NeqOp extends Operator implements BinaryOperator, BooleanOperator, MoneyOperator, IntegerOperator {

    public NeqOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitNeqOp(this);
    }
}
