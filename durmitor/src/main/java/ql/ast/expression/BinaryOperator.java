package ql.ast.expression;

import ql.ast.type.Type;

public abstract class BinaryOperator extends Operator {
    
    protected Expression lhs;
    protected Expression rhs;
    
    public BinaryOperator(Expression lhs, Expression rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Expression getLhs() {
        return lhs;
    }
    
    public Expression getRhs() {
        return rhs;
    }
    
    @Override
    public String toString() {
        return lhs.toString() + " " + getOperator() + " " + rhs.toString();
    }

    @Override
    public boolean isBinary() {
        return true;
    }
    
    public boolean isLegalFor(Type lhs, Type rhs) {
        return legalOperations.contains(new BinaryOperation(this,lhs,rhs));
    }
}
