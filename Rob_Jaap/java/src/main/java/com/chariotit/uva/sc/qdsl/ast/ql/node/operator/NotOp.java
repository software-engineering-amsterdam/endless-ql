package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.BooleanExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class NotOp extends Operator implements UnaryOperator, BooleanOperator {

    public NotOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitNotOp(this);
    }

    @Override
    public ExpressionValue evaluate(Expression expression) {
        if (!(expression.getExpressionValue() instanceof BooleanExpressionValue)) {
            throw new RuntimeException("Incompatible expression type");
        }

        return ((BooleanExpressionValue)expression.getExpressionValue()).not();
    }
}
