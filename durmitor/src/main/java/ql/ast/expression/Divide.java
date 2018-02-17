package ql.ast.expression;

import ql.ast.type.Numeric;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Divide extends Binary {

    public Divide(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
    public Type getType() {
        return new Numeric();
    }

    @Override
	public String toString() {
		return lhs.toString() + " / " + rhs.toString();
	}

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
