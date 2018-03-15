package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

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
