package ql.ast.expression;

import ql.ast.expression.literal.Literal;
import ql.visitors.interfaces.ExpressionVisitor;

public class Greater extends BinaryOperator {

    public Greater(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return ">";
    }
    
    @Override
    public Literal<?> evaluate() {
        return firstOperand.evaluate().greater(secondOperand.evaluate());
    }
}
