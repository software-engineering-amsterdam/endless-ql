package ql.ast.expression;

import ql.ast.expression.literal.Literal;
import ql.visitors.interfaces.ExpressionVisitor;

public class Less extends BinaryOperator {

    public Less(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "<";
    }
    
    @Override
    public Literal<?> evaluate() {
        return firstOperand.evaluate().less(secondOperand.evaluate());
    }
}
