package ql.ast.expression;

public class GreaterEqual extends Binary {

    public GreaterEqual(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " >= " + rhs.toString();
	}
}
