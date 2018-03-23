package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.BooleanExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.NumberExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

public class MinusOp extends Operator implements BinaryOperator, UnaryOperator, MoneyOperator, IntegerOperator {

    public MinusOp(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitMinusOp(this);
    }

    @Override
    public ExpressionValue evaluate(Expression leftExpression, Expression rightExpression) {
        if (!(leftExpression.getExpressionValue() instanceof NumberExpressionValue)) {
            throw new RuntimeException("Incompatible expression type");
        }

        return ((NumberExpressionValue) leftExpression.getExpressionValue())
                .subtract(
                        (NumberExpressionValue)rightExpression.getExpressionValue());
    }

    @Override
    public ExpressionValue evaluate(Expression expression) {
        if (!(expression.getExpressionValue() instanceof BooleanExpressionValue)) {
            throw new RuntimeException("Incompatible expression type");
        }

        return ((BooleanExpressionValue)expression.getExpressionValue()).not();
    }
}
