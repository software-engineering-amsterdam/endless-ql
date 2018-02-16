package ql.ast.expression;

public class NotEqual extends Binary {

    public NotEqual(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " != " + rhs.toString();
	}
}
