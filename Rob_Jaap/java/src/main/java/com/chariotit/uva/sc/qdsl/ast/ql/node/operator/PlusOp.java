package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.NumberExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlusOp extends Operator implements BinaryOperator, UnaryOperator {

    public PlusOp(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitPlusOp(this);
    }

    @Override
    public ExpressionValue evaluate(Expression leftExpression, Expression rightExpression) {

        return ((NumberExpressionValue) leftExpression.getExpressionValue())
                .add(
                        (NumberExpressionValue)rightExpression.getExpressionValue());
    }

    @Override
    public ExpressionValue evaluate(Expression expression) {
        return expression.getExpressionValue();
    }

    @Override
    protected List<ExpressionType> getValidExpressionTypes() {
        return new ArrayList<>(
                Arrays.asList(
                        ExpressionType.MONEY,
                        ExpressionType.INTEGER
                )
        );
    }
}
