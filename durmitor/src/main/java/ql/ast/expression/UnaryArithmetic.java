package ql.ast.expression;

import ql.ast.type.Money;
import ql.ast.type.Numeric;

public abstract class UnaryArithmetic extends UnaryOperator {

    @Override
    protected void initOperations() {
        legalOperations.add(new UnaryOperation(this, Numeric.class));
        legalOperations.add(new UnaryOperation(this, Money.class));
    }
}
