package ql.ast.expression;

import ql.ast.type.Money;
import ql.ast.type.Numeric;
import ql.ast.type.Str;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Add extends BinaryOperator {

    public Add(Expression lhs, Expression rhs) {
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
        return "+";
    }

    @Override
    protected void initOperations() {
        legalOperations.add(new BinaryOperation(this, Money.class, Money.class));
        legalOperations.add(new BinaryOperation(this, Str.class, Str.class));
    }
}
