package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;
import com.chariotit.uva.sc.qdsl.ast.ql.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeqOp extends Operator implements BinaryOperator {

    public NeqOp(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitNeqOp(this);
    }

    @Override
    public ExpressionValue evaluate(Expression leftExpression, Expression rightExpression) {
        return leftExpression.getExpressionValue().notEqualTo(rightExpression.getExpressionValue());
    }

    @Override
    protected List<ExpressionType> getValidExpressionTypes() {
        return new ArrayList<>(
                Arrays.asList(
                        ExpressionType.MONEY,
                        ExpressionType.INTEGER,
                        ExpressionType.BOOLEAN,
                        ExpressionType.STRING
                )
        );
    }

    @Override
    public ExpressionType getResultExpressionType(ExpressionType operandExpressionType) {
        return ExpressionType.BOOLEAN;
    }
}
