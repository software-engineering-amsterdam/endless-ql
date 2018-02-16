package ql.ast.expression;

public class Greater extends Binary {

    public Greater(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " > " + rhs.toString();
	}
}
