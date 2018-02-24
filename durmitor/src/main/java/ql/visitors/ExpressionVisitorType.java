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
import ql.ast.type.Type;
import ql.value.Value;
import ql.visitors.interfaces.ExpressionVisitor;

public class ExpressionVisitorType implements ExpressionVisitor<Type> {

    private ExpressionVisitorValue evv = new ExpressionVisitorValue();

    @Override
    public Type visit(Negation expr) {
        Value<?> opType = expr.getOperand().accept(evv);
        return opType.negation().getType();
    }

    @Override
    public Type visit(Negative expr) {
        Value<?> opType = expr.getOperand().accept(evv);
        return opType.negative().getType();
    }

    @Override
    public Type visit(Positive expr) {
        Value<?> opType = expr.getOperand().accept(evv);
        return opType.positive().getType();
    }

    @Override
    public Type visit(And expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
       
        return firstOpValue.and(secondOpValue).getType();
    }

    @Override
    public Type visit(Or expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return firstOpValue.or(secondOpValue).getType();
    }

    @Override
    public Type visit(Greater expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return  firstOpValue.greater(secondOpValue).getType();
    }

    @Override
    public Type visit(GreaterEqual expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return firstOpValue.greaterEqual(secondOpValue).getType();
    }

    @Override
    public Type visit(Less expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return firstOpValue.less(secondOpValue).getType();
    }

    @Override
    public Type visit(LessEqual expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return  firstOpValue.lessEqual(secondOpValue).getType();
    }

    @Override
    public Type visit(Equal expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return firstOpValue.equal(secondOpValue).getType();
    }

    @Override
    public Type visit(NotEqual expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return  firstOpValue.notEqual(secondOpValue).getType();
    }

    @Override
    public Type visit(Add expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return  firstOpValue.add(secondOpValue).getType();
    }

    @Override
    public Type visit(Subtract expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return firstOpValue.subtract(secondOpValue).getType();
    }

    @Override
    public Type visit(Multiply expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return  firstOpValue.multiplyBy(secondOpValue).getType();
    }

    @Override
    public Type visit(Divide expr) {
        
        Value<?> firstOpValue   = expr.getFirstOperand().accept(evv);
        Value<?> secondOpValue  = expr.getSecondOperand().accept(evv);
        
        return firstOpValue.divideBy(secondOpValue).getType();
    }

    @Override
    public Type visit(Identifier expr) {
        return expr.getType();
    }

    @Override
    public Type visit(BoolLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(IntLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(StrLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(DateLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(DecimalLiteral expr) {
        return expr.getType();
    }

    @Override
    public Type visit(MoneyLiteral expr) {
        return expr.getType();
    }
}
