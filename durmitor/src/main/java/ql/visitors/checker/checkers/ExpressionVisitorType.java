package ql.visitors.checker.checkers;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryOperator;
import ql.ast.expression.Divide;
import ql.ast.expression.Equal;
import ql.ast.expression.Greater;
import ql.ast.expression.GreaterEqual;
import ql.ast.expression.Identifier;
import ql.ast.expression.Less;
import ql.ast.expression.LessEqual;
import ql.ast.expression.Multiply;
import ql.ast.expression.Negation;
import ql.ast.expression.Negative;
import ql.ast.expression.NotEqual;
import ql.ast.expression.Or;
import ql.ast.expression.Positive;
import ql.ast.expression.Subtract;
import ql.ast.expression.UnaryOperator;
import ql.ast.expression.literal.BoolLiteral;
import ql.ast.expression.literal.DateLiteral;
import ql.ast.expression.literal.DecimalLiteral;
import ql.ast.expression.literal.IntLiteral;
import ql.ast.expression.literal.Literal;
import ql.ast.expression.literal.MoneyLiteral;
import ql.ast.expression.literal.StrLiteral;
import ql.ast.expression.literal.UndefinedLiteral;
import ql.ast.type.Type;
import ql.exceptions.IllegalOperation;
import ql.exceptions.QLException;
import ql.visitors.interfaces.ExpressionVisitor;

public class ExpressionVisitorType implements ExpressionVisitor<Type> {

    private List<QLException> errors;
    
    public ExpressionVisitorType() {
        errors = new ArrayList<QLException>();
    }
    
    public ExpressionVisitorType(List<QLException> errors) {
        this.errors = errors;
    }

    public Type check(UnaryOperator op, Type operandType, Type resultType) {
        
        if(resultType.isUndefined())
        {
            errors.add(new IllegalOperation(op,operandType));
        }
        
        return resultType;
    }
    
    public Type check(BinaryOperator op, Type firstOperandType, Type secondOperandType, Type resultType) {
        
        if(resultType.isUndefined())
        {
            errors.add(new IllegalOperation(op,firstOperandType,secondOperandType));
        }
        
        return resultType;
    }

    @Override
    public Type visit(Negation expr) {

        Type opType     = expr.getOperand().accept(this);
        Type resultType = Literal.create(opType).negation().getType();
        
        return check(expr,opType,resultType);
    }

    @Override
    public Type visit(Negative expr) {

        Type opType     = expr.getOperand().accept(this);
        Type resultType = Literal.create(opType).negative().getType();
        
        return check(expr,opType,resultType);
    }

    @Override
    public Type visit(Positive expr) {

        Type opType     = expr.getOperand().accept(this);
        Type resultType = Literal.create(opType).positive().getType();
        
        return check(expr,opType,resultType);
    }

    @Override
    public Type visit(And expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).and(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Or expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).or(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Greater expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).greater(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(GreaterEqual expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).greaterEqual(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Less expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).less(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(LessEqual expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).lessEqual(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Equal expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).equal(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(NotEqual expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).notEqual(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Add expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).add(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Subtract expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).subtract(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Multiply expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).multiply(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
    }

    @Override
    public Type visit(Divide expr) {

        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        Type resultType     = Literal.create(firstOpType).divide(Literal.create(secondOpType)).getType();
        
        return check(expr,firstOpType,secondOpType,resultType);
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

    @Override
    public Type visit(UndefinedLiteral expr) {
        return expr.getType();
    }
}
