package com.chariotit.uva.sc.qdsl.ql.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class MultiplyOp extends Operator implements BinaryOperator, MoneyOperator, IntegerOperator {

    public MultiplyOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMultiplyOp(this);
    }
}
