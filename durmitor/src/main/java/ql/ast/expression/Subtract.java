package ql.ast.expression;

public class Subtract extends Binary {

    public Subtract(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }
    
    @Override
	public String toString() {
		return lhs.toString() + " - " + rhs.toString();
	}    
}
