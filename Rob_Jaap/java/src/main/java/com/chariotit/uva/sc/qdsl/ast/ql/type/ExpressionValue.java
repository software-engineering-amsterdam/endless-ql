package com.chariotit.uva.sc.qdsl.ast.ql.type;

public abstract class ExpressionValue {

    public abstract BooleanExpressionValue equalTo(ExpressionValue expressionValue);
    public abstract BooleanExpressionValue notEqualTo(ExpressionValue expressionValue);
}
