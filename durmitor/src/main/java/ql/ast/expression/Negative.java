package ql.ast.expression;

public class Negative extends Unary {

    public Negative(Expression expr) { 
        super.expr = expr;
    }
    
    @Override
	public String toString() {
		return "-" + expr.toString();
	}
}
