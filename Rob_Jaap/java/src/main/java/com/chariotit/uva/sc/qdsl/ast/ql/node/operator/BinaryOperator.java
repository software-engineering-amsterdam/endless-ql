package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;

public interface BinaryOperator {

    ExpressionValue evaluate(Expression leftExpression, Expression rightExpression);
}
