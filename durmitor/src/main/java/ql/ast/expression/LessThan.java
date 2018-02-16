package ql.ast.expression;

public class LessThan extends Binary {

    public LessThan(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }
    
    @Override
	public String toString() {
		return lhs.toString() + " < " + rhs.toString();
	}
}
