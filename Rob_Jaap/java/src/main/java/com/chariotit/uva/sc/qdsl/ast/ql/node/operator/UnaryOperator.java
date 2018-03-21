package com.chariotit.uva.sc.qdsl.ast.ql.node.operator;

import com.chariotit.uva.sc.qdsl.ast.ExpressionValue;
import com.chariotit.uva.sc.qdsl.ast.ql.node.Expression;

public interface UnaryOperator {

    ExpressionValue evaluate(Expression expression);
}
