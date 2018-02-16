package ql.ast.expression;

public class Or extends Binary {

    public Or(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }

    @Override
	public String toString() {
		return lhs.toString() + " || " + rhs.toString();
	}
}
