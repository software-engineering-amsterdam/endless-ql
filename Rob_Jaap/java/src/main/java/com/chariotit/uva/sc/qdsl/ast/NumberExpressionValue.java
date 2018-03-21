package com.chariotit.uva.sc.qdsl.ast;

public abstract class NumberExpressionValue extends ExpressionValue {

    public abstract NumberExpressionValue divide(NumberExpressionValue expressionValue);
    public abstract NumberExpressionValue multiply(NumberExpressionValue expressionValue);
    public abstract NumberExpressionValue add(NumberExpressionValue expressionValue);
    public abstract NumberExpressionValue subtract(NumberExpressionValue expressionValue);

    public abstract BooleanExpressionValue greaterThan(NumberExpressionValue expressionValue);
    public abstract BooleanExpressionValue greaterThanEquals(NumberExpressionValue expressionValue);
    public abstract BooleanExpressionValue lessThan(NumberExpressionValue expressionValue);
    public abstract BooleanExpressionValue lessThanEquals(NumberExpressionValue expressionValue);
}
