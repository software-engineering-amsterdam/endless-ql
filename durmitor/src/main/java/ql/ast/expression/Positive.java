package ql.ast.expression;

public class Positive extends Unary {

    public Positive(Expression expr) { 
        super.expr = expr;
    }

    @Override
	public String toString() {
		return "+" + expr.toString();
	}
}
