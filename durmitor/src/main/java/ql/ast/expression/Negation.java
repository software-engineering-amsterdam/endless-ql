package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Negation extends Unary {

    public Negation(Expression expr) { 
        super.expr = expr;
    }
 
    @Override
    public Type getType() {
        return new Bool();
    }

    @Override
	public String toString() {
		return "!" + expr.toString();
	}

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
