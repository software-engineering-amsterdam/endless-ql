package ql.ast.expression;

import ql.ast.type.Bool;

public abstract class UnaryBooleanLogic extends UnaryOperator {

    @Override
    protected void initOperations() {
        legalOperations.add(new UnaryOperation(this, Bool.class));
    }
}
