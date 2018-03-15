package com.chariotit.uva.sc.qdsl.ql.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class GtOp extends Operator implements BinaryOperator, MoneyOperator, IntegerOperator,
        BooleanResultOperator {

    public GtOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitGtOp(this);
    }
}
