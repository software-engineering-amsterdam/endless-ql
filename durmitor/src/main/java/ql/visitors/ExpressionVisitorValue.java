package ql.visitors;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BoolLiteral;
import ql.ast.expression.DateLiteral;
import ql.ast.expression.DecimalLiteral;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.IntLiteral;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.MoneyLiteral;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.StrLiteral;
import ql.ast.expression.Subtract;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class ExpressionVisitorValue implements ExpressionVisitor<Value<?>> {

    @Override
    public Value<?> visit(Negation expr) {
        Value<?> opValue = expr.getOperand().accept(this);
        return opValue.negation();
    }

    @Override
    public Value<?> visit(Negative expr) {
        Value<?> opValue = expr.getOperand().accept(this);
        return opValue.negative();
    }

    @Override
    public Value<?> visit(Positive expr) {
        Value<?> opValue = expr.getOperand().accept(this);
        return opValue.positive();
    }

    @Override
    public Value<?> visit(And expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
       
        return firstOpValue.and(secondOpValue);
    }

    @Override
    public Value<?> visit(Or expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.or(secondOpValue);
    }

    @Override
    public Value<?> visit(Greater expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.greater(secondOpValue);
    }

    @Override
    public Value<?> visit(GreaterEqual expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.greaterEqual(secondOpValue);
    }

    @Override
    public Value<?> visit(Less expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.less(secondOpValue);
    }

    @Override
    public Value<?> visit(LessEqual expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.lessEqual(secondOpValue);
    }

    @Override
    public Value<?> visit(Equal expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.equal(secondOpValue);
    }

    @Override
    public Value<?> visit(NotEqual expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.notEqual(secondOpValue);
    }

    @Override
    public Value<?> visit(Add expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.add(secondOpValue);
    }

    @Override
    public Value<?> visit(Subtract expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.subtract(secondOpValue);
    }

    @Override
    public Value<?> visit(Multiply expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.multiplyBy(secondOpValue);
    }

    @Override
    public Value<?> visit(Divide expr) {
        
        Value<?> firstOpValue    = expr.getFirstOperand().accept(this);
        Value<?> secondOpValue   = expr.getSecondOperand().accept(this);
        
        return firstOpValue.divideBy(secondOpValue);
    }

    @Override
    public Value<?> visit(Identifier expr) {
        return expr.getType().toValue();
    }

    @Override
    public Value<?> visit(BoolLiteral expr) {
        return expr.getValue();
    }

    @Override
    public Value<?> visit(IntLiteral expr) {
        return expr.getValue();
    }

    @Override
    public Value<?> visit(StrLiteral expr) {
        return expr.getValue();
    }

    @Override
    public Value<?> visit(DateLiteral expr) {
        return expr.getValue();
    }

    @Override
    public Value<?> visit(DecimalLiteral expr) {
        return expr.getValue();
    }

    @Override
    public Value<?> visit(MoneyLiteral expr) {
        return expr.getValue();
    }
}
