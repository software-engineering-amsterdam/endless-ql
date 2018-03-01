package ql.ast.expression;

import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Type;
import ql.visitors.interfaces.ExpressionVisitor;

public class Or extends BinaryOperator {

    public Or(Expression firstOperand, Expression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public <E> E accept(ExpressionVisitor<E> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String getOperator() {
        return "||";
    }
    
    @Override
    public Type getType() {
        return firstOperand.getType().parse(new UndefinedLiteral()).or(secondOperand.getType().parse(new UndefinedLiteral())).getType();
    }

    @Override
    public Literal<?> evaluate() {
        return firstOperand.evaluate().or(secondOperand.evaluate());
    }
}
