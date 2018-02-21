package ql.ast.expression;

import ql.ast.type.Money;
import ql.ast.type.Numeric;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Subtract extends BinaryArithmetic {

    public Subtract(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Type getType() {
        return new Numeric();
    }
    
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "-";
    }

    @Override
    protected void initOperations() {
        legalOperations.add(new BinaryOperation(this, Money.class, Money.class));
    }
}
