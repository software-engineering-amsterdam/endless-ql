package ql.ast.expression;

import ql.ast.type.Bool;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Negation extends UnaryBooleanLogic {

    public Negation(Expression expr) { 
        super.expr = expr;
    }
 
    @Override
    public Type getType() {
        return new Bool();
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "!";
    }
}
