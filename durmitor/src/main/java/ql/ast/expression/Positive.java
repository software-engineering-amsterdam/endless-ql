package ql.ast.expression;

import ql.ast.type.Numeric;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Positive extends UnaryArithmetic {

    public Positive(Expression expr) { 
        super.expr = expr;
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
}
