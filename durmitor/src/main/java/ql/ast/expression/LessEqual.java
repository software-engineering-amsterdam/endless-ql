package ql.ast.expression;

public class LessEqual extends Binary {

    public LessEqual(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " <= " + rhs.toString();
	}
}
