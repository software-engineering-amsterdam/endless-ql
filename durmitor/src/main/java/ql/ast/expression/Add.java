package ql.ast.expression;

public class Add extends Binary {

    public Add(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " + " + rhs.toString();
	}
}
