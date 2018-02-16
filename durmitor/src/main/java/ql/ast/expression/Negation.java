package ql.ast.expression;

public class Negation extends Unary {

    public Negation(Expression expr) { 
        super.expr = expr;
    }
 
    @Override
	public String toString() {
		return "!" + expr.toString();
	}
}
