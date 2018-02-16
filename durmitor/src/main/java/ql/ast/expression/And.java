package ql.ast.expression;

public class And extends Binary {

    public And(Expression lhs, Expression rhs) {
        super(lhs, rhs);
    }
    
    @Override
	public String toString() {
		return lhs.toString() + " && " + rhs.toString();
	}
}
