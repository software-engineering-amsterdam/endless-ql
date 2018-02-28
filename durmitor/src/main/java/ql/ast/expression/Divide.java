package ql.ast.expression;

import ql.evaluator.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class Divide extends BinaryOperator {

    public Divide(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "/";
    }

    @Override
    public Value<?> evaluate() {
        return firstOperand.evaluate().divide(secondOperand.evaluate());
    }
}
