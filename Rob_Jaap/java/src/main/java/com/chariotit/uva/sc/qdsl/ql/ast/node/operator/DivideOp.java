package com.chariotit.uva.sc.qdsl.ql.ast.node.operator;

import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

public class DivideOp extends Operator implements BinaryOperator, MoneyOperator, IntegerOperator
{

    public DivideOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitDivideOp(this);
    }
}
