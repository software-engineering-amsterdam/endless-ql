package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class NeqOp extends Operator implements BinaryOperator, BooleanOperator, MoneyOperator, IntegerOperator {

    public NeqOp(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitNeqOp(this);
    }


    @Override
    public ExpressionValue evaluate(Expression leftExpression, Expression rightExpression) {
        return leftExpression.getExpressionValue().notEqualTo(rightExpression.getExpressionValue());
    }
}
