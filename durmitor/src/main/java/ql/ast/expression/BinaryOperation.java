package ql.ast.expression;


public abstract class BinaryOperation extends Operator {
    
    protected Expression firstOperand;
    protected Expression secondOperand;
    
    public BinaryOperation(Expression firstOperand, Expression secondOperand) {
        this.firstOperand   = firstOperand;
        this.secondOperand  = secondOperand;
    }
    
    public Expression getFirstOperand() {
        return firstOperand;
    }
    
    public Expression getSecondOperand() {
        return secondOperand;
    }
    
    @Override
    public String toString() {
        return firstOperand.toString() + " "+ getOperator() + " " + secondOperand.toString();
    }
    
    @Override
    public boolean isBinaryOperator() {
        return true;
    }
}
