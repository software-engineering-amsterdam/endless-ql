package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Date;
import ql.ast.type.Money;
import ql.ast.type.Numeric;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.ast.type.Undefined;

public abstract class BinaryEquality extends BinaryOperator {

    public BinaryEquality(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    protected void initOperations() {
        legalOperations.add(new BinaryOperation(this, Bool.class, Bool.class));
        legalOperations.add(new BinaryOperation(this, Numeric.class, Numeric.class));
        legalOperations.add(new BinaryOperation(this, Str.class, Str.class));
        legalOperations.add(new BinaryOperation(this, Date.class, Date.class));
        legalOperations.add(new BinaryOperation(this, Money.class, Money.class));
        legalOperations.add(new BinaryOperation(this, Undefined.class, Type.class));
        legalOperations.add(new BinaryOperation(this, Type.class, Undefined.class));
    }
}
