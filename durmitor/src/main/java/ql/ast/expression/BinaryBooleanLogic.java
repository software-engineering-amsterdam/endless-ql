package ql.ast.expression;

import ql.ast.type.Bool;

public abstract class BinaryBooleanLogic extends BinaryOperator {

    public BinaryBooleanLogic(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    protected void initOperations() {
        legalOperations.add(new BinaryOperation(this, Bool.class, Bool.class));
    }
}
