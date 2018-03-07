package ql.ast.expression;

import ql.ast.expression.literal.Literal;
import ql.visitors.interfaces.ExpressionVisitor;

public class Negation extends UnaryOperator {

    public Negation(Expression operand) { 
        super(operand);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "!";
    }
    
    @Override
    public Literal<?> evaluate() {
        return operand.evaluate().negation();
    }
}
