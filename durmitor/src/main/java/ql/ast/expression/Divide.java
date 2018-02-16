package ql.ast.expression;

public class Divide extends Binary {

    public Divide(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " / " + rhs.toString();
	}
}
