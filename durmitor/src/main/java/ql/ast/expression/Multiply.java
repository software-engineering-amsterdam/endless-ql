package ql.ast.expression;

public class Multiply extends Binary {

    public Multiply(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " * " + rhs.toString();
	}
}
