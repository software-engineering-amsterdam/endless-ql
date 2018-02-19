package ql.ast.expression;

import ql.ast.type.Numeric;

public abstract class BinaryArithmetic extends BinaryOperator {

    public BinaryArithmetic(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }
    
    @Override
    protected void initOperations() {
        legalOperations.add(new BinaryOperation(this, Numeric.class, Numeric.class));
    }
}
