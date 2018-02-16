package ql.ast.expression;

public class Equal extends Binary {

    public Equal(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " == " + rhs.toString();
	}
}
