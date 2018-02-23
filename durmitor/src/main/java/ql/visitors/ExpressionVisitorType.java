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
import ql.helpers.symboltable.SymbolTable;
import ql.visitors.interfaces.ExpressionVisitor;

public class ExpressionVisitorType implements ExpressionVisitor<Type> {

    private SymbolTable symbolTable;

    public ExpressionVisitorType(SymbolTable symbolTable) {

        this.symbolTable = symbolTable;
    }

    @Override
    public Type visit(Negation expr) {
        Type opType = expr.getOperand().accept(this);
        return opType.negation();
    }

    @Override
    public Type visit(Negative expr) {
        Type opType = expr.getOperand().accept(this);
        return opType.negative();
    }

    @Override
    public Type visit(Positive expr) {
        Type opType = expr.getOperand().accept(this);
        return opType.positive();
    }

    @Override
    public Type visit(And expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
       
        return firstOpType.and(secondOpType);
    }

    @Override
    public Type visit(Or expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return firstOpType.or(secondOpType);
    }

    @Override
    public Type visit(Greater expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return  firstOpType.greater(secondOpType);
    }

    @Override
    public Type visit(GreaterEqual expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return firstOpType.greaterEqual(secondOpType);
    }

    @Override
    public Type visit(Less expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return firstOpType.less(secondOpType);
    }

    @Override
    public Type visit(LessEqual expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return  firstOpType.lessEqual(secondOpType);
    }

    @Override
    public Type visit(Equal expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return firstOpType.equal(secondOpType);
    }

    @Override
    public Type visit(NotEqual expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return  firstOpType.notEqual(secondOpType);
    }

    @Override
    public Type visit(Add expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return  firstOpType.add(secondOpType);
    }

    @Override
    public Type visit(Subtract expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return firstOpType.subtract(secondOpType);
    }

    @Override
    public Type visit(Multiply expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return  firstOpType.multiplyBy(secondOpType);
    }

    @Override
    public Type visit(Divide expr) {
        
        Type firstOpType    = expr.getFirstOperand().accept(this);
        Type secondOpType   = expr.getSecondOperand().accept(this);
        
        return firstOpType.divideBy(secondOpType);
    }

    @Override
    public Type visit(Identifier expr) {
        return symbolTable.getType(expr.getName());
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
