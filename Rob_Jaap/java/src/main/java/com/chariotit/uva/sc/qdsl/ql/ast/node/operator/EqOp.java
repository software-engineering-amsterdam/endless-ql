package com.chariotit.uva.sc.qdsl.ql.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class EqOp extends Operator implements BinaryOperator, BooleanOperator, MoneyOperator,
        IntegerOperator, StringOperator, BooleanResultOperator {

    public EqOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitEqOp(this);
    }
}
